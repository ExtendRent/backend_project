package source_files.services.entityServices.abstracts.userAbstract;


import source_files.data.models.userEntities.EmployeeEntity;

import java.util.List;

public interface EmployeeEntityService {

    EmployeeEntity add(EmployeeEntity employeeEntity);

    EmployeeEntity update(EmployeeEntity employeeEntity);

    EmployeeEntity getById(int id);

    EmployeeEntity getByPhoneNumber(String phoneNumber);

    EmployeeEntity getByEmailAddress(String emailAddress);

    List<EmployeeEntity> getAllBySalaryBetween(double salary1, double salary2);

    List<EmployeeEntity> getAll();

    List<EmployeeEntity> getAllByDeletedState(boolean isDeleted);

    void delete(EmployeeEntity employeeEntity);


}
