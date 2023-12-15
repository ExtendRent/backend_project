package source_files.data.DTO.userDTOs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class EmployeeDTO {
    String name;
    String surname;
    String email;
    double salary;
}
