package source_files.data.DTO.itemDTOs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.BrandEntity;

@Builder
@Getter
@Setter
public class CarModelDTO{
        String name;
        String brandName;
}
