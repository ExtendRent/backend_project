package source_files.data.DTO.itemDTOs;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarModelDTO {
    int id;
    String name;
    String brandName;
}
