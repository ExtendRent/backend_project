package source_files.data.DTO.paperWorkDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RentalStatusDTO {
    int id;
    String name;
    boolean isDeleted;
}
