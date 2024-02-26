package source_files.data.DTO.itemDTOs;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BrandDTO {
    int id;
    int brandImageEntityId;
    String name;
    String brandImageEntityUrl;
    boolean isDeleted;
}
