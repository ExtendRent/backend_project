package src.services.vehicle.car;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import src.controllers.vehicle.requests.car.CreateCarRequest;
import src.controllers.vehicle.requests.car.UpdateCarRequest;
import src.core.exception.DataNotFoundException;
import src.data.models.image_entities.CarImageEntity;
import src.data.models.vehicle_entities.CarEntity;
import src.data.models.vehicle_entities.vehicle_features.ColorEntity;
import src.data.models.vehicle_entities.vehicle_features.FuelTypeEntity;
import src.data.models.vehicle_entities.vehicle_features.ShiftTypeEntity;
import src.data.models.vehicle_entities.vehicle_features.VehicleStatusEntity;
import src.data.models.vehicle_entities.vehicle_features.car_features.CarBodyTypeEntity;
import src.data.models.vehicle_entities.vehicle_features.car_features.CarModelEntity;
import src.data.models.vehicle_entities.vehicle_features.car_features.CarSegmentEntity;
import src.repositories.vehicle.CarRepository;
import src.services.image.car.CarImageService;
import src.services.item.driving_license.DrivingLicenseTypeEntityService;
import src.services.vehicle_features.car_features.car_body_type.CarBodyTypeEntityService;
import src.services.vehicle_features.car_features.car_model.CarModelEntityService;
import src.services.vehicle_features.car_features.car_segment.CarSegmentEntityService;
import src.services.vehicle_features.common_features.color.ColorEntityService;
import src.services.vehicle_features.common_features.fuel_type.FuelTypeEntityService;
import src.services.vehicle_features.common_features.shift_type.ShiftTypeEntityService;
import src.services.vehicle_features.common_features.vehicle_status.VehicleStatusEntityService;

import java.util.List;

import static src.core.exception.exception_types.NotFoundExceptionType.CAR_DATA_NOT_FOUND;
import static src.data.enums.types.item_types.VehicleType.CAR;

@RequiredArgsConstructor
@Service
public class CarEntityServiceImpl implements CarEntityService {

    private final CarRepository repository;
    private final CarModelEntityService carModelEntityService;
    private final FuelTypeEntityService fuelTypeEntityService;
    private final ColorEntityService colorEntityService;
    private final CarBodyTypeEntityService carBodyTypeEntityService;
    private final CarSegmentEntityService carSegmentEntityService;
    private final ShiftTypeEntityService gearTypeEntityService;
    private final VehicleStatusEntityService vehicleStatusEntityService;
    private final CarImageService carImageService;
    private final DrivingLicenseTypeEntityService drivingLicenseTypeService;

    @Override
    public CarEntity create(CreateCarRequest createCarRequest) {
        CarModelEntity carModelEntity = carModelEntityService.getById(createCarRequest.getCarModelEntityId());
        FuelTypeEntity fuelTypeEntity = fuelTypeEntityService.getById(createCarRequest.getFuelTypeEntityId());
        ColorEntity colorEntity = colorEntityService.getById(createCarRequest.getColorEntityId());
        CarBodyTypeEntity carBodyTypeEntity = carBodyTypeEntityService.getById(createCarRequest.getCarBodyTypeEntityId());
        CarSegmentEntity carSegmentEntity = carSegmentEntityService.getById(createCarRequest.getCarSegmentEntityId());
        ShiftTypeEntity shiftTypeEntity = gearTypeEntityService.getById(createCarRequest.getShiftTypeEntityId());
        VehicleStatusEntity vehicleStatusEntity = vehicleStatusEntityService.getById(createCarRequest.getVehicleStatusEntityId());
        CarImageEntity carImageEntity = carImageService.getById(createCarRequest.getCarImageEntityId());

        CarEntity carEntity = CarEntity.carBuilder()
                .carModelEntity(carModelEntity)
                .fuelTypeEntity(fuelTypeEntity)
                .colorEntity(colorEntity)
                .carBodyTypeEntity(carBodyTypeEntity)
                .carSegmentEntity(carSegmentEntity)
                .shiftTypeEntity(shiftTypeEntity)
                .vehicleStatusEntity(vehicleStatusEntity)
                .carImageEntity(carImageEntity)
                .year(createCarRequest.getYear())
                .rentalPrice(createCarRequest.getRentalPrice())
                .licensePlate(createCarRequest.getLicensePlate())
                .kilometer(createCarRequest.getKilometer())
                .seat(createCarRequest.getSeat())
                .luggage(createCarRequest.getLuggage())
                .isAvailable(createCarRequest.isAvailable())
                .details(createCarRequest.getDetails())
                .expectedMinDrivingLicenseType(
                        drivingLicenseTypeService.getById(createCarRequest.getExpectedMinDrivingLicenseTypeId()))
                .vehicleType(CAR)
                .build();
        return repository.save(carEntity);
    }

    @Override
    public CarEntity update(UpdateCarRequest updateCarRequest) {
        CarModelEntity carModelEntity = carModelEntityService.getById(updateCarRequest.getCarModelEntityId());
        FuelTypeEntity fuelTypeEntity = fuelTypeEntityService.getById(updateCarRequest.getFuelTypeEntityId());
        ColorEntity colorEntity = colorEntityService.getById(updateCarRequest.getColorEntityId());
        CarBodyTypeEntity carBodyTypeEntity = carBodyTypeEntityService.getById(updateCarRequest.getCarBodyTypeEntityId());
        CarSegmentEntity carSegmentEntity = carSegmentEntityService.getById(updateCarRequest.getCarSegmentEntityId());
        ShiftTypeEntity shiftTypeEntity = gearTypeEntityService.getById(updateCarRequest.getShiftTypeEntityId());
        VehicleStatusEntity vehicleStatusEntity = vehicleStatusEntityService.getById(updateCarRequest.getVehicleStatusEntityId());
        CarImageEntity carImageEntity = carImageService.getById(updateCarRequest.getCarImageEntityId());

        CarEntity carEntity = CarEntity.carBuilder()
                .id(updateCarRequest.getId())
                .carModelEntity(carModelEntity)
                .fuelTypeEntity(fuelTypeEntity)
                .colorEntity(colorEntity)
                .carBodyTypeEntity(carBodyTypeEntity)
                .carSegmentEntity(carSegmentEntity)
                .shiftTypeEntity(shiftTypeEntity)
                .vehicleStatusEntity(vehicleStatusEntity)
                .carImageEntity(carImageEntity)
                .year(updateCarRequest.getYear())
                .rentalPrice(updateCarRequest.getRentalPrice())
                .licensePlate(updateCarRequest.getLicensePlate())
                .kilometer(updateCarRequest.getKilometer())
                .seat(updateCarRequest.getSeat())
                .luggage(updateCarRequest.getLuggage())
                .isAvailable(updateCarRequest.isAvailable())
                .details(updateCarRequest.getDetails())
                .vehicleType(CAR)
                .build();
        return repository.save(carEntity);
    }

    @Override
    public CarEntity update(CarEntity carEntity) {
        return repository.save(carEntity);
    }

    @Override
    public CarEntity getById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(CAR_DATA_NOT_FOUND));
    }

    @Override
    public List<CarEntity> getAll() {
        return repository.findAll();
    }

    @Override
    public List<CarEntity> getAllByDeletedState(boolean isDeleted) {
        return repository.findAllByIsDeleted(isDeleted);
    }

    @Override
    public List<CarEntity> getAllByStatus(Integer statusId) {
        return repository.findAllByVehicleStatusEntityId(statusId);
    }

    @Override
    public List<CarEntity> getAllByColorId(int id) {
        return repository.findAllByColorEntity_Id(id);
    }

    @Override
    public List<CarEntity> getAllByRentalPriceBetween(double startPrice, double endPrice) {
        return repository.findAllByRentalPriceBetween(startPrice, endPrice);
    }


    @Override
    public List<CarEntity> getAllByModelId(int id) {
        return repository.findAllByCarModelEntity_Id(id);
    }

    @Override
    public List<CarEntity> getAllByYearBetween(int year1, int year2) {
        return repository.findAllByYearBetween(year1, year2);
    }

    @Override
    public List<CarEntity> getAllByBrandId(int brandId) {
        return repository.findAllByCarModelEntity_BrandEntity_Id(brandId);
    }

    @Override
    public List<CarEntity> getAllFiltered(
            Integer startPrice, Integer endPrice,
            Integer statusId,
            Integer colorId, Integer seat,
            Integer luggage, Integer modelId,
            Integer startYear, Integer endYear, Integer brandId,
            Integer fuelTypeId, Integer shiftTypeId, Integer segmentId) {

        return repository.findAllFiltered(
                startPrice, endPrice,
                statusId, colorId,
                seat, luggage,
                modelId, startYear,
                endYear, brandId, fuelTypeId,
                shiftTypeId, segmentId);
    }

    @Override
    public void delete(CarEntity carEntity) {
        repository.delete(carEntity);
    }

    @Override
    public int getCountByDeletedState(boolean isDeleted) {
        return repository.countByIsDeleted(isDeleted);
    }

    @Override
    public int getCountByStatusId(int statusId) {
        return repository.countByVehicleStatusEntity_Id(statusId);
    }
}
