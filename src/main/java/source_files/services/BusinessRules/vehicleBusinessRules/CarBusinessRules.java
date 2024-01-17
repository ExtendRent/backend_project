package source_files.services.BusinessRules.vehicleBusinessRules;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.userDTOs.CustomerDTO;
import source_files.data.models.vehicleEntities.CarEntity;
import source_files.data.requests.vehicleRequests.CarRequests.AddCarRequest;
import source_files.data.requests.vehicleRequests.CarRequests.UpdateCarRequest;
import source_files.data.types.itemTypes.DrivingLicenseType;
import source_files.dataAccess.vehicleRepositories.CarRepository;
import source_files.exception.AlreadyExistsException;
import source_files.exception.DataNotFoundException;
import source_files.exception.ValidationException;
import source_files.services.BusinessRules.abstractsBusinessRules.BaseBusinessRulesService;
import source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts.BrandEntityService;
import source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts.CarBodyTypeEntityService;
import source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts.CarModelEntityService;
import source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts.ColorEntityService;
import source_files.services.userServices.abstracts.CustomerService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static source_files.exception.exceptionTypes.AlreadyExistsExceptionType.LICENSE_PLATE_ALREADY_EXISTS;
import static source_files.exception.exceptionTypes.NotFoundExceptionType.CAR_LIST_NOT_FOUND;
import static source_files.exception.exceptionTypes.ValidationExceptionType.VALIDATION_EXCEPTION;

@AllArgsConstructor
@Service
public class CarBusinessRules implements BaseBusinessRulesService {
    private final CarRepository carRepository;
    private final CarModelEntityService carModelEntityService;
    private final ColorEntityService colorEntityService;

    private final CarBodyTypeEntityService carBodyTypeEntityService;

    private final BrandEntityService brandEntityService;

    private final CustomerService customerService;

    //--------------------- AUTO FIX METHODS ---------------------

    public AddCarRequest fixAddCarRequest(AddCarRequest addCarRequest) {
        addCarRequest.setLicensePlate(this.fixName(addCarRequest.getLicensePlate()));
        return addCarRequest;
    }

    public UpdateCarRequest fixUpdateCarRequest(UpdateCarRequest updateCarRequest) {
        updateCarRequest.setLicensePlate(this.fixName(updateCarRequest.getLicensePlate()));

        return updateCarRequest;
    }

    //--------------------- AUTO CHECK METHODS ---------------------
    public AddCarRequest checkAddCarRequest(AddCarRequest addCarRequest) {
        this.checkLicensePlate(addCarRequest.getLicensePlate());
        this.checkModel(addCarRequest.getCarModelEntityId());
        this.checkColor(addCarRequest.getColorEntityId());
        this.checkBodyType(addCarRequest.getCarBodyTypeEntityId());
        this.checkBrand(addCarRequest.getBrandEntityId());
        return addCarRequest;
    }

    public UpdateCarRequest checkUpdateCarRequest(UpdateCarRequest updateCarRequest) {
        this.checkLicensePlateAndIdNot(updateCarRequest.getLicensePlate(), updateCarRequest.getId());
        this.checkModel(updateCarRequest.getCarModelEntityId());
        this.checkColor(updateCarRequest.getColorEntityId());
        this.checkBodyType(updateCarRequest.getCarBodyTypeEntityId());
        this.checkBrand(updateCarRequest.getBrandEntityId());
        this.checkLicensePlate(updateCarRequest.getLicensePlate());
        return updateCarRequest;
    }

    public void checkDates(LocalDate startDate, LocalDate endDate) {
        this.checkEndDate(startDate, endDate);
        this.checkTotalRentalDays(startDate, endDate);
    }

    //----------------------------METHODS--------------------------------

    @Override
    public List<?> checkDataList(List<?> list) {
        if (list.isEmpty()) {
            throw new DataNotFoundException(CAR_LIST_NOT_FOUND, "Aradığınız kriterlere uygun araç bulunamadı");
        }
        return list;
    }

    public List<CarEntity> getCarEntityListByMatchedDrivingLicense(List<CarEntity> carEntityList, Integer customerId) {
        //Müşteri giriş yapmış ise müşterinin ehliyet tipi ile araçlar karşılaştırılıp filtreleniyor.
        return carEntityList.stream().filter(car -> this.isDrivingLicenseTypeSuitable(car, customerId)).toList();
    }

    private boolean isDrivingLicenseTypeSuitable(CarEntity car, Integer customerId) {
        if (customerId != null) {
            CustomerDTO customer = customerService.getById(customerId);
            List<DrivingLicenseType> customerLicenseTypes = customer.getDrivingLicenseTypes();
            List<DrivingLicenseType> expectedLicenseTypes = car.getExpectedDrivingLicenseTypes();
            return customerLicenseTypes.stream().anyMatch(expectedLicenseTypes::contains);
        }
        return true; // Giriş yapmadan araç listeleyebilmek için customerId null verilebilmelidir.
    }

    @Override
    public String fixName(String licensePLate) {
        return licensePLate.replace(" ", "").toLowerCase();
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

    private void checkEndDate(LocalDate startDate, LocalDate endDate) {

        if (endDate.isBefore(startDate) && endDate.isEqual(startDate)) {
            throw new ValidationException(
                    VALIDATION_EXCEPTION, "Başlangıç tarihi ile biriş tarihi arasında en az bir gün olmalıdır.");
        }
    }

    private void checkTotalRentalDays(LocalDate startDate, LocalDate endDate) {

        if ((int) ChronoUnit.DAYS.between(startDate, endDate) > 25) {
            throw new ValidationException(VALIDATION_EXCEPTION, "Kiralama tarihi maksimum 25 gün olabilir. Lütfen tarih aralığınızı buna göre düzenleyiniz.");
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
