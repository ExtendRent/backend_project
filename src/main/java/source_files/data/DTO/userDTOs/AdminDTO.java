package source_files.data.DTO.userDTOs;

import lombok.Builder;

@Builder
public record AdminDTO(String name,
                       String surname,
                       String email,
                       double salary) {
}
