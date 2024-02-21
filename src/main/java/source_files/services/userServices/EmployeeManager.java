package source_files.services.userServices;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
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
import java.util.stream.Collectors;

import static source_files.data.enums.defaultDataEnums.Status.DefaultUserStatus.PENDING_VERIFYING;

@Service
@RequiredArgsConstructor
public class EmployeeManager implements EmployeeService {

    private final EmployeeEntityService entityService;
    private final ModelMapperService modelMapperService;
    private final EmployeeBusinessRules employeeBusinessRules;
    private final PasswordEncoder passwordEncoder;
    private final UserImageService userImageService;

    @Override
    public void create(CreateEmployeeRequest createEmployeeRequest) {
        try {
            EmployeeEntity employeeEntity = modelMapperService.forRequest()
                    .map(employeeBusinessRules.checkCreateEmployeeRequest
                            (employeeBusinessRules.fixCreateEmployeeRequest(createEmployeeRequest)), EmployeeEntity.class);
            employeeEntity.setPassword(passwordEncoder.encode(createEmployeeRequest.getPassword()));
            employeeEntity.setStatus(PENDING_VERIFYING);
            entityService.create(employeeEntity);
        } catch (Exception e) {
            userImageService.delete(createEmployeeRequest.getUserImageEntityId());
            throw e;
        }
    }

    @Override
    public EmployeeDTO update(UpdateEmployeeRequest updateEmployeeRequest) {
        EmployeeEntity employeeEntity = entityService.getById(updateEmployeeRequest.getId());
        UserImageEntity userImage = employeeEntity.getUserImageEntity();
        if (userImage.getId() != updateEmployeeRequest.getUserImageEntityId()) {
            userImageService.delete(userImage.getId());
        }
        employeeEntity = modelMapperService.forRequest()
                .map(employeeBusinessRules.checkUpdateEmployeeRequest
                        (employeeBusinessRules.fixUpdateEmployeeRequest(updateEmployeeRequest)), EmployeeEntity.class);

        return modelMapperService.forResponse().map(entityService.create(employeeEntity), EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO getById(int id) {
        return modelMapperService.forResponse().map(entityService.getById(id), EmployeeDTO.class);
    }

    @Override
    public List<EmployeeDTO> getAll() {
        return employeeBusinessRules.checkDataList(entityService.getAll())
                .stream().map(employeeEntity -> modelMapperService.forResponse()
                        .map(employeeEntity, EmployeeDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> getAllBySalaryBetween(double salary1, double salary2) {
        return employeeBusinessRules.checkDataList(entityService.getAllBySalaryBetween(salary1, salary2))
                .stream().map(employeeEntity -> modelMapperService.forResponse().map(employeeEntity, EmployeeDTO.class)).toList();
    }

    @Override
    public List<EmployeeDTO> getAllByDeletedState(boolean isDeleted) {
        return employeeBusinessRules.checkDataList(entityService.getAllByDeletedState(isDeleted))
                .stream().map(employeeEntity -> modelMapperService.forResponse()
                        .map(employeeEntity, EmployeeDTO.class)).toList();
    }


    @Override
    public EmployeeDTO getByPhoneNumber(String phoneNumber) {
        return modelMapperService.forResponse()
                .map(entityService.getByPhoneNumber(phoneNumber), EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO getByEmailAddress(String emailAddress) {
        return modelMapperService.forResponse()
                .map(entityService.getByEmailAddress(emailAddress), EmployeeDTO.class);
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
}
