package source_files.services.userServices;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import source_files.data.DTO.userDTOs.EmployeeDTO;
import source_files.data.models.imageEntities.UserImageEntity;
import source_files.data.models.userEntities.EmployeeEntity;
import source_files.data.requests.userRequests.CreateEmployeeRequest;
import source_files.data.requests.userRequests.UpdateEmployeeRequest;
import source_files.services.BusinessRules.userBusinessRuless.EmployeeBusinessRules;
import source_files.services.entityServices.abstracts.userAbstract.EmployeeEntityService;
import source_files.services.systemServices.ImageServices.UserImageService;
import source_files.services.userServices.abstracts.EmployeeService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeEntityService entityService;
    private final EmployeeBusinessRules rules;
    private final PasswordEncoder passwordEncoder;
    private final UserImageService userImageService;

    @Override
    public void create(CreateEmployeeRequest createEmployeeRequest) {
        try {
            createEmployeeRequest = rules.fix(createEmployeeRequest);
            rules.check(createEmployeeRequest);
            createEmployeeRequest.setPassword(passwordEncoder.encode(createEmployeeRequest.getPassword()));
            entityService.create(createEmployeeRequest);
        } catch (Exception e) {
            userImageService.delete(createEmployeeRequest.getUserImageEntityId());
            throw e;
        }
    }

    @Override
    public EmployeeDTO update(UpdateEmployeeRequest updateEmployeeRequest) {
        updateEmployeeRequest = rules.fix(updateEmployeeRequest);
        rules.check(updateEmployeeRequest);
        updateEmployeeRequest.setPassword(passwordEncoder.encode(updateEmployeeRequest.getPassword()));
        UserImageEntity userImage = entityService.getById(updateEmployeeRequest.getId()).getUserImageEntity();
        if (userImage.getId() != updateEmployeeRequest.getUserImageEntityId()) {
            userImageService.delete(userImage.getId());
        }
        return entityService.update(updateEmployeeRequest).toModel();
    }

    @Override
    public EmployeeDTO getById(int id) {
        return entityService.getById(id).toModel();
    }

    @Override
    public List<EmployeeDTO> getAll() {
        return mapToDTOList(entityService.getAll());
    }

    @Override
    public List<EmployeeDTO> getAllBySalaryBetween(double salary1, double salary2) {
        return mapToDTOList(entityService.getAllBySalaryBetween(salary1, salary2));
    }

    @Override
    public List<EmployeeDTO> getAllByDeletedState(boolean isDeleted) {
        return mapToDTOList(entityService.getAllByDeletedState(isDeleted));
    }


    @Override
    public EmployeeDTO getByPhoneNumber(String phoneNumber) {
        return entityService.getByPhoneNumber(phoneNumber).toModel();
    }

    @Override
    public EmployeeDTO getByEmailAddress(String emailAddress) {
        return entityService.getByEmailAddress(emailAddress).toModel();
    }

    @Override
    public void delete(int id, boolean hardDelete) {
        if (hardDelete) {
            EmployeeEntity employeeEntity = entityService.getById(id);
            entityService.delete(employeeEntity);
            userImageService.delete(employeeEntity.getUserImageEntity().getId());
        } else {
            softDelete(id);
        }
    }

    @Override
    public void softDelete(int id) {
        EmployeeEntity employeeEntity = entityService.getById(id);
        employeeEntity.setIsDeleted(true);
        employeeEntity.setDeletedAt(LocalDateTime.now());
        entityService.update(employeeEntity);
    }

    @Override
    public int getCountByDeletedState(boolean isDeleted) {
        return entityService.getCountByDeletedState(isDeleted);
    }

    private List<EmployeeDTO> mapToDTOList(List<EmployeeEntity> employeeEntities) {
        rules.checkDataList(employeeEntities);
        return employeeEntities.stream().map(EmployeeEntity::toModel).toList();
    }
}
