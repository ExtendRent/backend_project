package source_files.data.DTO.itemDTOs;

import lombok.Builder;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.BrandEntity;

@Builder
public record CarModelDTO(String name,
                          BrandEntity brand) {
}
