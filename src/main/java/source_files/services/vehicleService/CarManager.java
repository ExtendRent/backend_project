package source_files.services.vehicleService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.vehicleDTOs.CarDTO;
import source_files.data.models.paperWorkEntities.rentalEntities.RentalEntity;
import source_files.data.models.vehicleEntities.CarEntity;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.ImagesEntity;
import source_files.data.requests.vehicleRequests.CarRequests.CreateCarRequest;
import source_files.data.requests.vehicleRequests.CarRequests.UpdateCarRequest;
import source_files.services.BusinessRules.vehicleBusinessRules.CarBusinessRules;
import source_files.services.entityServices.abstracts.vehicleAbstracts.CarEntityService;
import source_files.services.entityServices.vehicleEntityManagers.vehicleFeaturesEntityManagers.VehicleStatusEntityManager;
import source_files.services.vehicleService.abstracts.CarService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static source_files.data.Status.DefaultVehicleStatus.*;
import static source_files.data.types.itemTypes.VehicleType.CAR;

@Service
@RequiredArgsConstructor
public class CarManager implements CarService {

    private final ModelMapperService mapper;
    private final CarEntityService carEntityService;
    private final CarBusinessRules rules;
    private final VehicleStatusEntityManager vehicleStatusManager;

    @Override
    public void create(CreateCarRequest createCarRequest) {

        //CarEntity carEntity = mapper.forRequest().map(createCarRequest, CarEntity.class); //ESKİ KOD !

        CarEntity carEntity = mapper.forRequest().map(rules                 //YENİ KOD
                .checkCreateCarRequest(rules.fixCreateCarRequest(createCarRequest)), CarEntity.class);

        carEntity.setVehicleType(CAR);

        //ImagesEntity içerisinde kullandığımız oneToOne bağlantısındaki cascade sayesinde, CarEntity üzerinden
        //yapılan ImagesEntity değişiklikleri ana klastaki(ImagesEntity) de de değişecek.
        //Yani carEntity ye ImagesEntity set ettiğimizde, database e de eklenecek ve içindeki CarEntity otomatik oluşacak.
        carEntity.setImagesEntity(new ImagesEntity(carEntity, createCarRequest.getImagePaths()));

        carEntityService.create(carEntity);
    }

    @Override
    public CarDTO getById(int id) {
        return mapToDTO(carEntityService.getById(id));
    }

    @Override
    public CarDTO update(UpdateCarRequest updateCarRequest) {
        CarEntity carEntity = mapper.forRequest().map(
                rules.checkUpdateCarRequest(
                        rules.fixUpdateCarRequest(updateCarRequest)
                ), CarEntity.class
        );
        carEntity.setVehicleType(CAR);
        return
                this.mapToDTO(carEntityService.update(carEntity));

    }

    @Override
    public List<CarDTO> getAll() {
        //bilgi: önce gelen listenin boş olup olmadığını kontrol ediyoruz. boş değilse listeyi dönüyor.
        return rules.checkDataList(carEntityService.getAll())
                .stream()
                .map(car -> mapper.forResponse().map(car, CarDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CarDTO> getAllWithLogin(int customerId) {

        List<CarDTO> carDTOList = getAll();
        List<CarDTO> matchedCarDTOList = getAllByIsDrivingLicenseSuitable(customerId);

        carDTOList.forEach(carDTO -> {
            boolean isMatched = matchedCarDTOList.stream()
                    .anyMatch(matchedCar -> matchedCar.getId() == carDTO.getId());

            // Eğer araç kullanıcının kullanabileceği araçlar listesinde değilse
            // isLicenseTypeSuitable'ı false yap
            // Buradaki amaç, bütün araçları getirmek fakat ehliyet uyuşmayan araçları işaretlemek.
            carDTO.setIsLicenseTypeSuitable(isMatched);
        });

        return carDTOList;
    }


    @Override
    public List<CarDTO> getAllByIsDrivingLicenseSuitable(Integer customerId) {
        return rules.checkDataList(
                rules.getCarEntityListByMatchedDrivingLicense(carEntityService.getAll(), customerId)).stream().map(
                carEntity -> mapper.forResponse().map(carEntity, CarDTO.class)).toList();
    }

    @Override
    public List<CarDTO> getAllByDeletedState(boolean isDeleted) {
        return rules.checkDataList(carEntityService.getAllByDeletedState(isDeleted))
                .stream().map(carEntity -> mapper.forResponse().map(carEntity, CarDTO.class)).toList();
    }

    @Override
    public List<CarDTO> getAllByStatus(Integer statusId) {
        return rules.checkDataList(
                        carEntityService.getAllByStatus(statusId)).stream()
                .map(car -> mapper.forResponse().map(car, CarDTO.class)).toList();
    }

    @Override
    public List<CarDTO> getAllByColorId(int id) {
        return rules.checkDataList(carEntityService.getAllByColorId(id))
                .stream().map(carEntity -> mapper.forResponse().map(carEntity, CarDTO.class)).toList();
    }

    @Override
    public List<CarDTO> getAllByModelId(int id) {
        return rules.checkDataList(carEntityService.getAllByModelId(id))
                .stream().map(carEntity -> mapper.forResponse().map(carEntity, CarDTO.class)).toList();
    }

    @Override
    public List<CarDTO> getAllByBrandId(int brandId) {
        return rules.checkDataList(carEntityService.getAllByBrandId(brandId))
                .stream().map(carEntity -> mapper.forResponse().map(carEntity, CarDTO.class)).toList();
    }

    @Override
    public List<CarDTO> getAllByYearBetween(int startYear, int endYear) {
        return rules.checkDataList(carEntityService.getAllByYearBetween(startYear, endYear))
                .stream().map(carEntity -> mapper.forResponse().map(carEntity, CarDTO.class)).toList();
    }

    @Override
    public List<CarDTO> getAllByRentalPriceBetween(double startPrice, double endPrice) {
        return rules.checkDataList(carEntityService.getAllByRentalPriceBetween(startPrice, endPrice))
                .stream().map(carEntity -> mapper.forResponse().map(carEntity, CarDTO.class)).toList();
    }

    @Override
    public List<CarDTO> getAllByAvailabilityBetween(LocalDate startDate, LocalDate endDate) {

        List<CarEntity> allCars = carEntityService.getAll();
        List<CarEntity> availableCars = filterAvailableCars(allCars, startDate, endDate);

        rules.checkDataList(availableCars);
        return mapToDTOList(availableCars);
    }


    @Override
    public List<CarDTO> getAllFiltered(Integer customerId,
                                       LocalDate startDate, LocalDate endDate,
                                       Integer startPrice, Integer endPrice,
                                       Boolean isDeleted, Integer statusId,
                                       Integer colorId,
                                       Integer seat, Integer luggage, Integer modelId,
                                       Integer startYear, Integer endYear, Integer brandId,
                                       Integer fuelTypeId, Integer shiftTypeId) {

        List<CarEntity> filteredCars = carEntityService.getAllFiltered(
                startPrice, endPrice,
                isDeleted, statusId,
                colorId, seat, luggage,
                modelId, startYear,
                endYear, brandId,
                fuelTypeId, shiftTypeId);

        List<CarDTO> filteredCarsDTO = filteredCars.stream().map(this::mapToDTO).toList();

        List<CarDTO> result = filteredCarsDTO.stream()
                .filter(carDTO -> isCarAvailableBetween(carDTO.getId(), endDate, startDate))
                .toList();

        return rules.checkDataList(result)
                .stream().map(carEntity -> mapper.forResponse().map(carEntity, CarDTO.class)).toList();
    }

    @Override
    public void delete(int id, boolean hardDelete) {

        if (hardDelete) {
            carEntityService.delete(carEntityService.getById(id));
        } else {
            softDelete(id);
        }
    }

    @Override
    public void softDelete(int id) {
        CarEntity carEntity = carEntityService.getById(id);
        carEntity.setIsDeleted(true);
        carEntity.setAvailable(false);
        carEntity.setVehicleStatusEntity(vehicleStatusManager.getByStatus(DELETED));
        carEntity.setDeletedAt(LocalDateTime.now());
        carEntityService.update(carEntity);
    }

    @Override
    public void addRental(int carId, RentalEntity rentalEntity) {
        CarEntity carEntity = carEntityService.getById(carId);
        carEntity.getRentalList().add(rentalEntity);
        carEntity.setVehicleStatusEntity(vehicleStatusManager.getByStatus(BOOKED));
        carEntityService.update(carEntity);
    }

    @Override
    public void removeRental(int carId, RentalEntity rentalEntity) {
        CarEntity carEntity = carEntityService.getById(carId);
        carEntity.getRentalList().remove(rentalEntity);
        if (carEntity.getRentalList().isEmpty()) {
            carEntity.setVehicleStatusEntity(vehicleStatusManager.getByStatus(AVAILABLE));
        }
        carEntityService.update(carEntity);
    }

    //---------------------------------Local Methods------------------------------------------------------

    private List<CarEntity> filterAvailableCars(List<CarEntity> cars, LocalDate startDate, LocalDate endDate) {
        return cars.stream()
                .filter(car -> isCarAvailableBetween(car.getId(), startDate, endDate))
                .collect(Collectors.toList());
    }


    public boolean isCarAvailableBetween(int carId, LocalDate startDate, LocalDate endDate) {
        startDate = rules.fixStartDate(startDate);
        rules.checkDates(startDate, endDate);

        CarEntity car = carEntityService.getById(carId);
        List<RentalEntity> rentalList = car.getRentalList();
        if (car.isAvailable()) {
            for (RentalEntity rental : rentalList) {
                LocalDate rentalStartDate = rental.getStartDate();
                LocalDate rentalEndDate = rental.getEndDate();
                if (!(startDate.isAfter(rentalEndDate)
                        || endDate.isBefore(rentalStartDate))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }


    private List<CarDTO> mapToDTOList(List<CarEntity> cars) {
        return cars.stream()
                .map(car -> mapper.forResponse().map(car, CarDTO.class))
                .toList();
    }

    private CarDTO mapToDTO(CarEntity car) {
        return mapper.forResponse().map(car, CarDTO.class);
    }

    public UpdateCarRequest convertToUpdateRequest(int id) {
        CarEntity carEntity = carEntityService.getById(id);
        return UpdateCarRequest.builder()
                .id(carEntity.getId())
                .brandEntityId(carEntity.getCarModelEntity().getBrandEntity().getId())
                .carModelEntityId(carEntity.getCarModelEntity().getId())
                .carBodyTypeEntityId(carEntity.getCarBodyTypeEntity().getId())
                .licensePlate(carEntity.getLicensePlate())
                .kilometer(carEntity.getKilometer())
                .imagePaths(carEntity.getImagesEntity().getImagePaths())
                .year(carEntity.getYear())
                .seat(carEntity.getSeat())
                .rentalPrice(carEntity.getRentalPrice())
                .details(carEntity.getDetails())
                .luggage(carEntity.getLuggage())
                .expectedDefaultDrivingLicenseTypes(carEntity.getExpectedDefaultDrivingLicenseTypes())
                .colorEntityId(carEntity.getColorEntity().getId())
                .fuelTypeEntityId(carEntity.getFuelTypeEntity().getId())
                .shiftTypeEntityId(carEntity.getShiftTypeEntity().getId())
                .vehicleStatusEntityId(carEntity.getVehicleStatusEntity().getId())
                .build();
    }

}
