package source_files.data.DTO.itemDTOs;

import lombok.*;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.BrandEntity;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarModelDTO{
        String name;
        String brandName;
}
