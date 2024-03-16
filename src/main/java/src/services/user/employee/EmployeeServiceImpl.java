package src.services.user.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import src.controllers.user.requests.employee.CreateEmployeeRequest;
import src.controllers.user.requests.employee.UpdateEmployeeRequest;
import src.controllers.user.responses.EmployeeResponse;
import src.data.models.image_entities.UserImageEntity;
import src.data.models.user_entities.EmployeeEntity;
import src.services.image.user.UserImageService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeEntityService entityService;
    private final EmployeeRules rules;
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
    public EmployeeResponse update(UpdateEmployeeRequest updateEmployeeRequest) {
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
    public EmployeeResponse getById(int id) {
        return entityService.getById(id).toModel();
    }

    @Override
    public List<EmployeeResponse> getAll() {
        return mapToDTOList(entityService.getAll());
    }

    @Override
    public List<EmployeeResponse> getAllBySalaryBetween(double salary1, double salary2) {
        return mapToDTOList(entityService.getAllBySalaryBetween(salary1, salary2));
    }

    @Override
    public List<EmployeeResponse> getAllByDeletedState(boolean isDeleted) {
        return mapToDTOList(entityService.getAllByDeletedState(isDeleted));
    }


    @Override
    public EmployeeResponse getByPhoneNumber(String phoneNumber) {
        return entityService.getByPhoneNumber(phoneNumber).toModel();
    }

    @Override
    public EmployeeResponse getByEmailAddress(String emailAddress) {
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

    private List<EmployeeResponse> mapToDTOList(List<EmployeeEntity> employeeEntities) {
        rules.checkDataList(employeeEntities);
        return employeeEntities.stream().map(EmployeeEntity::toModel).toList();
    }
}
