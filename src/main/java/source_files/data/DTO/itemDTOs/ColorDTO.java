package source_files.data.DTO.itemDTOs;

import lombok.*;
import source_files.data.DTO.BaseDTO;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ColorDTO extends BaseDTO {
    int id;
    String name;
    boolean isDeleted;
}
