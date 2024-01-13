package source_files.data.DTO.userDTOs;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    int id;
    String name;
    String surname;
    String email;
    double salary;
}
