package source_files.data.DTO.userDTOs;

import lombok.Builder;

@Builder
public record EmployeeDTO(String name,
                          String surname,
                          String email,
                          double salary) {
}
