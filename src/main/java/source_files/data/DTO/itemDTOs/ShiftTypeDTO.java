package source_files.data.DTO.itemDTOs;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ShiftTypeDTO {
    int id;
    String name;
    boolean isDeleted;
}
