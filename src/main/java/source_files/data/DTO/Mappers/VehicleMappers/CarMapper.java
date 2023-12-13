package source_files.data.DTO.Mappers.VehicleMappers;


import source_files.data.DTO.vehicleDTOs.CarDTO;
import source_files.data.models.vehicleEntities.CarEntity;


public interface CarMapper {
    CarDTO carEntityToCarDTO(CarEntity carEntity);
}
