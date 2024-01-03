package source_files.services.BusinessRules;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.requests.vehicleRequests.CarRequests.AddCarRequest;
import source_files.data.requests.vehicleRequests.CarRequests.UpdateCarRequest;
import source_files.dataAccess.vehicleRepositories.CarRepository;
import source_files.exception.AlreadyExistsException;
import source_files.exception.DataNotFoundException;
import source_files.services.entityServices.abstracts.vehicleFeaturesAbstracts.BrandEntityService;
import source_files.services.entityServices.abstracts.vehicleFeaturesAbstracts.CarBodyTypeEntityService;
import source_files.services.entityServices.abstracts.vehicleFeaturesAbstracts.CarModelEntityService;
import source_files.services.entityServices.abstracts.vehicleFeaturesAbstracts.ColorEntityService;

import java.util.List;

import static source_files.exception.AlreadyExistsExceptionType.LICENSE_PLATE_ALREADY_EXISTS;
import static source_files.exception.NotFoundExceptionType.CAR_DATA_NOT_FOUND;

@AllArgsConstructor
@Service
public class CarBusinessRules implements BaseBusinessRulesService {
    private final CarRepository carRepository;
    private final CarModelEntityService carModelEntityService;
    private final ColorEntityService colorEntityService;

    private final CarBodyTypeEntityService carBodyTypeEntityService;

    private final BrandEntityService brandEntityService;

    //--------------------- AUTO FIX METHODS ---------------------

    public AddCarRequest fixAddCarRequest(AddCarRequest addCarRequest) {
        addCarRequest.setLicensePlate(this.fixLicensePlate(addCarRequest.getLicensePlate()));
        return addCarRequest;
    }

    public UpdateCarRequest fixUpdateCarRequest(UpdateCarRequest updateCarRequest) {
        updateCarRequest.setLicensePlate(this.fixLicensePlate(updateCarRequest.getLicensePlate()));

        return updateCarRequest;
    }

    //--------------------- AUTO CHECK METHODS ---------------------
    public AddCarRequest checkAddCarRequest(AddCarRequest addCarRequest) {
        this.checkLicensePlate(addCarRequest.getLicensePlate());
        this.checkModel(addCarRequest.getModelId());
        this.checkColor(addCarRequest.getColorId());
        this.checkBodyType(addCarRequest.getBodyTypeId());
        this.checkBrand(addCarRequest.getBrandId());
        return addCarRequest;
    }

    public UpdateCarRequest checkUpdateCarRequest(UpdateCarRequest updateCarRequest) {
        this.checkLicensePlateAndIdNot(updateCarRequest.getLicensePlate(), updateCarRequest.getId());
        this.checkModel(updateCarRequest.getModelId());
        this.checkColor(updateCarRequest.getColorId());
        this.checkBodyType(updateCarRequest.getBodyTypeId());
        this.checkBrand(updateCarRequest.getBrandId());
        this.checkLicensePlate(updateCarRequest.getLicensePlate());
        return updateCarRequest;
    }

    //----------------------------METHODS--------------------------------
    @Override
    public List<?> checkDataList(List<?> list) {
        if (list.isEmpty()) {
            throw new DataNotFoundException(CAR_DATA_NOT_FOUND, "Aradığınız kriterlere uygun araç bulunamadı");
        }
        return list;
    }

    @Override
    public String fixName(String name) {
        return name.replace(" ", "").toLowerCase();
    }

    private String fixLicensePlate(String plate) {
        return plate.replace(" ", "").toUpperCase();
    }


    private void checkLicensePlate(String licensePlate) {
        if (this.carRepository.existsByLicensePlate(licensePlate)) {
            throw new AlreadyExistsException(LICENSE_PLATE_ALREADY_EXISTS, "Bu plakaya tanımlı bir araç zaten bulunmaktadır");
        }
    }

    private void checkLicensePlateAndIdNot(String licensePlate, int id) {
        if (this.carRepository.existsByLicensePlateAndIdNot(licensePlate, id)) {
            throw new AlreadyExistsException(LICENSE_PLATE_ALREADY_EXISTS, "Bu plakaya tanımlı bir araç zaten bulunmaktadır");
        }
    }


    private void checkBrand(int brandId) {
        brandEntityService.getById(brandId);
    }

    private void checkModel(int modelId) {
        carModelEntityService.getById(modelId);
    }

    private void checkColor(int colorId) {
        colorEntityService.getById(colorId);
    }

    private void checkBodyType(int bodyTypeId) {
        carBodyTypeEntityService.getById(bodyTypeId);
    }


    //----------------------------------------------------------------------


}
