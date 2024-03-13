package source_files.services.BusinessRules.vehicleBusinessRules;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.userDTOs.CustomerDTO;
import source_files.data.models.vehicleEntities.CarEntity;
import source_files.data.requests.vehicleRequests.CarRequests.CreateCarRequest;
import source_files.data.requests.vehicleRequests.CarRequests.UpdateCarRequest;
import source_files.dataAccess.vehicleRepositories.CarRepository;
import source_files.exception.AlreadyExistsException;
import source_files.exception.DataNotFoundException;
import source_files.exception.ValidationException;
import source_files.services.BusinessRules.abstractsBusinessRules.BaseRules;
import source_files.services.DrivingLicenseTypeService;
import source_files.services.entityServices.abstracts.vehicleAbstracts.CarEntityService;
import source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts.BrandEntityService;
import source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts.CarBodyTypeEntityService;
import source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts.CarModelEntityService;
import source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts.ColorEntityService;
import source_files.services.userServices.abstracts.CustomerService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static source_files.exception.exceptionTypes.AlreadyExistsExceptionType.LICENSE_PLATE_ALREADY_EXISTS;
import static source_files.exception.exceptionTypes.NotFoundExceptionType.CAR_LIST_NOT_FOUND;
import static source_files.exception.exceptionTypes.ValidationExceptionType.VALIDATION_EXCEPTION;

@RequiredArgsConstructor
@Service
public class CarRules implements BaseRules {
    private final CarRepository carRepository;

    private final CarEntityService carEntityService;
    private final CarModelEntityService carModelEntityService;
    private final ColorEntityService colorEntityService;

    private final CarBodyTypeEntityService carBodyTypeEntityService;

    private final BrandEntityService brandEntityService;

    private final CustomerService customerService;

    private final DrivingLicenseTypeService drivingLicenseTypeService;

    //--------------------- AUTO FIX METHODS ---------------------

    public CreateCarRequest fix(CreateCarRequest createCarRequest) {
        createCarRequest.setLicensePlate(this.fixName(createCarRequest.getLicensePlate()));
        return createCarRequest;
    }

    public UpdateCarRequest fix(UpdateCarRequest updateCarRequest) {
        updateCarRequest.setLicensePlate(this.fixName(updateCarRequest.getLicensePlate()));

        return updateCarRequest;
    }

    //--------------------- AUTO CHECK METHODS ---------------------
    public void check(CreateCarRequest createCarRequest) {
        this.checkLicensePlate(createCarRequest.getLicensePlate());
        this.checkModel(createCarRequest.getCarModelEntityId());
        this.checkColor(createCarRequest.getColorEntityId());
        this.checkBodyType(createCarRequest.getCarBodyTypeEntityId());
        this.checkBrand(createCarRequest.getBrandEntityId());
    }

    public void check(UpdateCarRequest updateCarRequest) {
        if (!updateCarRequest.getLicensePlate().equals(
                carEntityService.getById(updateCarRequest.getId()).getLicensePlate())) {
            this.checkLicensePlate(updateCarRequest.getLicensePlate());
        }
        this.checkModel(updateCarRequest.getCarModelEntityId());
        this.checkColor(updateCarRequest.getColorEntityId());
        this.checkBodyType(updateCarRequest.getCarBodyTypeEntityId());
    }

    public void checkDates(LocalDate startDate, LocalDate endDate) {
        this.checkTotalRentalDays(startDate, endDate);
    }

    public LocalDate fixStartDate(LocalDate startDate) {
        return this.setStartDate(startDate);
    }

    public LocalDate fixEndDate(LocalDate startDate, LocalDate endDate) {
        return this.setEndDate(startDate, endDate);
    }

    //----------------------------METHODS--------------------------------

    @Override
    public void checkDataList(List<?> list) {
        if (list.isEmpty()) {
            throw new DataNotFoundException(CAR_LIST_NOT_FOUND);
        }

    }

    public boolean isDrivingLicenseTypeSuitable(int carId, Integer customerId) {
        if (customerId != null) {
            CarEntity car = carEntityService.getById(carId);
            CustomerDTO customerDto = customerService.getById(customerId);
            int customerLicenseLevel =
                    drivingLicenseTypeService.getById(customerDto.getDrivingLicenseTypeId()).getLicenseLevel();
            int expectedLicenseLevel = car.getExpectedMinDrivingLicenseType().getLicenseLevel();

            return customerLicenseLevel >= expectedLicenseLevel;
        }
        return true; // Giriş yapmadan araç listeleyebilmek için customerId null verilebilmelidir.
    }

    @Override
    public String fixName(String licensePLate) {
        return licensePLate.replace(" ", "").toLowerCase();
    }


    private void checkLicensePlate(String licensePlate) {

        if (this.carRepository.existsByLicensePlate(licensePlate)) {
            throw new AlreadyExistsException(LICENSE_PLATE_ALREADY_EXISTS);
        }


    }

    private void checkLicensePlateAndIdNot(String licensePlate, int id) {
        if (this.carRepository.existsByLicensePlateAndIdNot(licensePlate, id)) {
            throw new AlreadyExistsException(LICENSE_PLATE_ALREADY_EXISTS);
        }
    }


    private LocalDate setStartDate(LocalDate startDate) {
        if (startDate == null) {
            startDate = LocalDate.now().plusDays(1);
            if ((LocalTime.now().isAfter(LocalTime.of(17, 0)))) {
                startDate = startDate.plusDays(1);
            }
        }
        return startDate;
    }

    private LocalDate setEndDate(LocalDate startDate, LocalDate endDate) {
        if (endDate == null) {
            endDate = startDate.plusDays(1);
        }
        return endDate;
    }

    private void checkTotalRentalDays(LocalDate startDate, LocalDate endDate) {
        if (endDate != null) {
            if (startDate.isAfter(endDate)) {
                throw new ValidationException(VALIDATION_EXCEPTION, "Başlangıç tarihi teslim tarihinden sonra olamaz");
            } else if ((int) ChronoUnit.DAYS.between(startDate, endDate) > 25) {
                throw new ValidationException(VALIDATION_EXCEPTION,
                        "Kiralama aralığı maksimum 25 gün olabilir. Lütfen tarih aralığınızı buna göre düzenleyiniz.");
            }
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
