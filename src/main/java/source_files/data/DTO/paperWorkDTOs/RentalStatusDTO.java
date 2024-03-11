package source_files.data.DTO.paperWorkDTOs;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentalStatusDTO {
    int id;
    String name;
    boolean isDeleted;
}
