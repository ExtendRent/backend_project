package source_files.data.DTO.Mappers.VehicleMappers;

import org.mapstruct.Mapper;
import source_files.data.DTO.vehicleDTOs.CarDTO;
import source_files.data.models.vehicleEntities.CarEntity;

@Mapper
public interface CarMapper {
    CarDTO carEntityToCarDTO(CarEntity carEntity);
}
