package source_files.services.vehicleService.abstracts;

import source_files.data.DTO.itemDTOs.BrandDTO;
import source_files.data.DTO.itemDTOs.CarBodyTypeDTO;
import source_files.data.DTO.itemDTOs.CarModelDTO;
import source_files.data.DTO.itemDTOs.ColorDTO;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.BrandEntity;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.CarBodyTypeEntity;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.CarModelEntity;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.ColorEntity;

public interface VehicleFeaturesService {
    //--------------------ADD METHODS-----------------------------------------------------------------
    BrandDTO addBrand();

    ColorDTO addColor();

    CarModelDTO addCarModel();

    CarBodyTypeDTO addCarBodyType();

    //--------------------UPDATE METHODS-----------------------------------------------------------------
    BrandDTO updateBrand();

    CarBodyTypeDTO updateCarBodyType();

    CarModelDTO updateCarModel();

    ColorDTO updateColor();

    //--------------------GETBYID METHODS-----------------------------------------------------------------
    BrandDTO getBrandById(int id);

    CarBodyTypeDTO getCarBodyTypeById(int id);

    CarModelDTO getCarModelById(int id);

    ColorDTO getColorById(int id);

    //--------------------DELETE METHODS-----------------------------------------------------------------
    void deleteBrand();

    void deleteCarBodyType();

    void deleteCarModel();

    void deleteColor();


}
