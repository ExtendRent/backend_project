package source_files.services.userServices.abstracts;

import source_files.data.DTO.userDTOs.AdminDTO;
import source_files.data.requests.userRequests.AddAdminRequest;
import source_files.data.requests.userRequests.UpdateAdminRequest;

import java.util.List;

public interface AdminService {

    AdminDTO add(AddAdminRequest addAdminRequest);

    AdminDTO update(UpdateAdminRequest updateAdminRequest);

    AdminDTO getById(int id);

    List<AdminDTO> getAll();

    List<AdminDTO> getAllBySalaryGreaterThanEqual(Double salary);

    AdminDTO getByPhoneNumber(String phoneNumber);

    AdminDTO getByEmailAddress(String emailAddress);

    List<AdminDTO> getAllByDeletedState(boolean isDeleted);

    void delete(int id, boolean hardDelete);

    void hardDelete(int id);

    void softDelete(int id);


}
