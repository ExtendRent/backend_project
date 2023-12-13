package source_files.data.DTO.vehicleDTOs;

import lombok.Builder;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.BrandEntity;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.CarBodyTypeEntity;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.CarModelEntity;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.ColorEntity;

@Builder
public record CarDTO(BrandEntity brandEntity,
                     ColorEntity Color,
                     int year,
                     CarBodyTypeEntity bodyType,
                     CarModelEntity model,
                     String licensePlate,
                     String details) {


}
