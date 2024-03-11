package source_files.data.DTO.itemDTOs;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarSegmentDTO {
    int id;
    String name;
    boolean isDeleted;
}
