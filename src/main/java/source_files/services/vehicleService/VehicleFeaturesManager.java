package source_files.services.vehicleService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.itemDTOs.BrandDTO;
import source_files.data.DTO.itemDTOs.CarBodyTypeDTO;
import source_files.data.DTO.itemDTOs.CarModelDTO;
import source_files.data.DTO.itemDTOs.ColorDTO;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.BrandEntity;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.CarBodyTypeEntity;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.CarModelEntity;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.ColorEntity;
import source_files.services.entityServices.abstracts.vehicleFeaturesAbstracts.BrandEntityService;
import source_files.services.entityServices.abstracts.vehicleFeaturesAbstracts.CarBodyTypeEntityService;
import source_files.services.entityServices.abstracts.vehicleFeaturesAbstracts.CarModelEntityService;
import source_files.services.entityServices.abstracts.vehicleFeaturesAbstracts.ColorEntityService;
import source_files.services.vehicleService.abstracts.VehicleFeaturesService;

@Service
@AllArgsConstructor
public class VehicleFeaturesManager implements VehicleFeaturesService {

    private final BrandEntityService brandEntityService;
    private final ColorEntityService colorEntityService;
    private final CarModelEntityService carModelEntityService;
    private final CarBodyTypeEntityService carBodyTypeEntityService;


    //--------------------ADD METHODS-----------------------------------------------------------------
    @Override
    public BrandDTO addBrand() {
        return null;
    }

    @Override
    public ColorDTO addColor() {
        return null;
    }

    @Override
    public CarModelDTO addCarModel() {
        return null;
    }

    @Override
    public CarBodyTypeDTO addCarBodyType() {
        return null;
    }

    //--------------------UPDATE METHODS-----------------------------------------------------------------

    @Override
    public BrandDTO updateBrand() {
        return null;
    }

    @Override
    public CarBodyTypeDTO updateCarBodyType() {
        return null;
    }

    @Override
    public CarModelDTO updateCarModel() {
        return null;
    }

    @Override
    public ColorDTO updateColor() {
        return null;
    }

    //--------------------GETBYID METHODS-----------------------------------------------------------------

    @Override
    public BrandDTO getBrandById(int id) {
        return null;
    }

    @Override
    public CarBodyTypeDTO getCarBodyTypeById(int id) {
        return null;
    }

    @Override
    public CarModelDTO getCarModelById(int id) {
        return null;
    }

    @Override
    public ColorDTO getColorById(int id) {
        return null;
    }

    //--------------------DELETE METHODS-----------------------------------------------------------------

    @Override
    public void deleteBrand() {

    }

    @Override
    public void deleteCarBodyType() {

    }

    @Override
    public void deleteCarModel() {

    }

    @Override
    public void deleteColor() {

    }
}
