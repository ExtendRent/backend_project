package source_files.data.DTO.itemDTOs;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarModelDTO {
    int id;
    int brandEntityId;
    String name;
    String brandEntityName;
    boolean isDeleted;
}
