package source_files.services.vehicleService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.vehicleDTOs.CarDTO;
import source_files.data.enums.defaultDataEnums.Status.DefaultVehicleStatus;
import source_files.data.models.imageEntities.CarImageEntity;
import source_files.data.models.paperWorkEntities.rentalEntities.RentalEntity;
import source_files.data.models.vehicleEntities.CarEntity;
import source_files.data.requests.vehicleRequests.CarRequests.CreateCarRequest;
import source_files.data.requests.vehicleRequests.CarRequests.UpdateCarRequest;
import source_files.exception.DataNotFoundException;
import source_files.services.BusinessRules.vehicleBusinessRules.CarBusinessRules;
import source_files.services.entityServices.abstracts.vehicleAbstracts.CarEntityService;
import source_files.services.entityServices.vehicleEntityManagers.vehicleFeaturesEntityManagers.VehicleStatusEntityManager;
import source_files.services.systemServices.ImageServices.CarImageService;
import source_files.services.vehicleService.abstracts.CarService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static source_files.data.enums.defaultDataEnums.Status.DefaultVehicleStatus.*;
import static source_files.data.enums.types.itemTypes.VehicleType.CAR;
import static source_files.exception.exceptionTypes.NotFoundExceptionType.VEHICLE_STATUS_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CarManager implements CarService {

    private final ModelMapperService mapper;
    private final CarEntityService entityService;
    private final CarBusinessRules rules;
    private final VehicleStatusEntityManager vehicleStatusManager;
    private final CarImageService carImageService;

    @Override
    public void create(CreateCarRequest createCarRequest) throws IOException {
        try {
            CarEntity carEntity = mapper.forRequest().map(rules
                    .checkCreateCarRequest(rules.fixCreateCarRequest(createCarRequest)), CarEntity.class);

            carEntity.setVehicleType(CAR);

            entityService.create(carEntity);
        } catch (Exception e) {
            carImageService.delete(createCarRequest.getCarImageEntityId());
            throw e;
        }
    }

    @Override
    public CarDTO getById(int id) {
        return mapToDTO(entityService.getById(id));
    }

    @Override
    public CarDTO update(UpdateCarRequest updateCarRequest) throws IOException {
        CarEntity carEntity = entityService.getById(updateCarRequest.getId());
        CarImageEntity carImage = carEntity.getCarImageEntity();

        if (carImage.getId() != updateCarRequest.getCarImageEntityId()) {
            carImageService.delete(carImage.getId());
        }

        carEntity = mapper.forRequest().map(
                rules.checkUpdateCarRequest(
                        rules.fixUpdateCarRequest(updateCarRequest)
                ), CarEntity.class
        );
        carEntity.setVehicleType(CAR);
        return this.mapToDTO(entityService.update(carEntity));
    }

    @Override
    public List<CarDTO> getAll() {
        //bilgi: önce gelen listenin boş olup olmadığını kontrol ediyoruz. boş değilse listeyi dönüyor.
        return rules.checkDataList(entityService.getAll())
                .stream()
                .map(car -> mapper.forResponse().map(car, CarDTO.class))
                .collect(Collectors.toList());
    }


    @Override
    public List<CarDTO> getAllByDeletedState(boolean isDeleted) {
        return rules.checkDataList(entityService.getAllByDeletedState(isDeleted))
                .stream().map(carEntity -> mapper.forResponse().map(carEntity, CarDTO.class)).toList();
    }

    @Override
    public List<CarDTO> getAllByStatus(Integer statusId) {
        return rules.checkDataList(
                        entityService.getAllByStatus(statusId)).stream()
                .map(car -> mapper.forResponse().map(car, CarDTO.class)).toList();
    }

    @Override
    public List<CarDTO> getAllByColorId(int id) {
        return rules.checkDataList(entityService.getAllByColorId(id))
                .stream().map(carEntity -> mapper.forResponse().map(carEntity, CarDTO.class)).toList();
    }

    @Override
    public List<CarDTO> getAllByModelId(int id) {
        return rules.checkDataList(entityService.getAllByModelId(id))
                .stream().map(carEntity -> mapper.forResponse().map(carEntity, CarDTO.class)).toList();
    }

    @Override
    public List<CarDTO> getAllByBrandId(int brandId) {
        return rules.checkDataList(entityService.getAllByBrandId(brandId))
                .stream().map(carEntity -> mapper.forResponse().map(carEntity, CarDTO.class)).toList();
    }

    @Override
    public List<CarDTO> getAllByYearBetween(int startYear, int endYear) {
        return rules.checkDataList(entityService.getAllByYearBetween(startYear, endYear))
                .stream().map(carEntity -> mapper.forResponse().map(carEntity, CarDTO.class)).toList();
    }

    @Override
    public List<CarDTO> getAllByRentalPriceBetween(double startPrice, double endPrice) {
        return rules.checkDataList(entityService.getAllByRentalPriceBetween(startPrice, endPrice))
                .stream().map(carEntity -> mapper.forResponse().map(carEntity, CarDTO.class)).toList();
    }

    @Override
    public List<CarDTO> getAllByAvailabilityBetween(LocalDate startDate, LocalDate endDate) {

        List<CarEntity> allCars = entityService.getAll();
        List<CarEntity> availableCars = filterAvailableCars(allCars, startDate, endDate);

        rules.checkDataList(availableCars);
        return mapToDTOList(availableCars);
    }

    @Override
    public List<CarDTO> getAllByIsDrivingLicenseSuitable(Integer customerId) {
        return rules.checkDataList(
                getCarEntityListByDrivingLicenseSuitable(entityService.getAll(), customerId)).stream().map(
                carEntity -> mapper.forResponse().map(carEntity, CarDTO.class)).toList();
    }

    @Override
    public List<CarDTO> getAllFiltered(Integer customerId, Boolean licenseSuitable,
                                       LocalDate startDate, LocalDate endDate,
                                       Integer startPrice, Integer endPrice,
                                       Boolean isDeleted, Integer statusId,
                                       Integer colorId,
                                       Integer seat, Integer luggage, Integer modelId,
                                       Integer startYear, Integer endYear, Integer brandId,
                                       Integer fuelTypeId, Integer shiftTypeId, Integer segmentId) {

        List<CarEntity> filteredCars = entityService.getAllFiltered(
                startPrice, endPrice,
                statusId,
                colorId, seat, luggage,
                modelId, startYear,
                endYear, brandId,
                fuelTypeId, shiftTypeId, segmentId);

        filteredCars = filterAvailableCars(filteredCars, startDate, endDate);
        filteredCars = filterDeletedCars(filteredCars, isDeleted);

        if (licenseSuitable != null && licenseSuitable) {
            filteredCars = getCarEntityListByDrivingLicenseSuitable(filteredCars, customerId);
        }

        return rules.checkDataList(markAllForDrivingLicenseSuitable(filteredCars, customerId))
                .stream().map(carEntity -> mapper.forResponse().map(carEntity, CarDTO.class)).toList();
    }

    @Override
    public void delete(int id, boolean hardDelete) throws IOException {

        if (hardDelete) {
            CarEntity carEntity = entityService.getById(id);
            entityService.delete(carEntity);
            carImageService.delete(carEntity.getCarImageEntity().getId());
        } else {
            softDelete(id);
        }
    }

    @Override
    public void softDelete(int id) {
        this.changeStatus(entityService.getById(id), DELETED);
    }

    @Override
    public void addRental(int carId, RentalEntity rentalEntity) {
        CarEntity carEntity = entityService.getById(carId);
        carEntity.getRentalList().add(rentalEntity);
        entityService.update(carEntity);
        this.changeStatus(carEntity, BOOKED);
    }

    @Override
    public void removeRental(int carId, RentalEntity rentalEntity) {
        CarEntity carEntity = entityService.getById(carId);
        carEntity.getRentalList().remove(rentalEntity);
        if (carEntity.getRentalList().isEmpty()) {
            this.changeStatus(carEntity, AVAILABLE);
        }
        entityService.update(carEntity);
    }

    //---------------------------------Local Methods------------------------------------------------------

    public List<CarDTO> markAllForDrivingLicenseSuitable(List<CarEntity> cars, Integer customerId) {
        List<CarDTO> carDTOList = mapToDTOList(cars);
        carDTOList.forEach(carDTO -> {
            boolean isMatched = rules.isDrivingLicenseTypeSuitable(carDTO.getId(), customerId);
            // Eğer araç kullanıcının lisans tipine uygun değilse
            // isLicenseTypeSuitable'ı false yap
            // Buradaki amaç, bütün araçları getirmek fakat ehliyet uyuşmayan araçları işaretlemek.
            carDTO.setIsLicenseTypeSuitable(isMatched);
        });
        return carDTOList;
    }

    private List<CarEntity> filterDeletedCars(List<CarEntity> filteredCars, Boolean isDeleted) {
        if (isDeleted == null) {
            return filteredCars;
        }
        return filteredCars.stream().filter(car -> car.getIsDeleted() == isDeleted).toList();
    }

    public List<CarEntity> getCarEntityListByDrivingLicenseSuitable(List<CarEntity> carEntityList, Integer customerId) {
        //Müşteri giriş yapmış ise ve sadece ehliyetinin yettiği araçları isterse müşterinin ehliyet tipi ile araçlar karşılaştırılıp filtreleniyor.
        return carEntityList.stream().filter(car -> rules.isDrivingLicenseTypeSuitable(car.getId(), customerId)).toList();
    }

    private List<CarEntity> filterAvailableCars(List<CarEntity> cars, LocalDate startDate, LocalDate endDate) {
        return cars.stream()
                .filter(car -> isCarAvailableBetween(car.getId(), startDate, endDate))
                .collect(Collectors.toList());
    }

    public boolean isCarAvailableBetween(int carId, LocalDate startDate, LocalDate endDate) {
        startDate = rules.fixStartDate(startDate);
        endDate = rules.fixEndDate(startDate, endDate);
        rules.checkDates(startDate, endDate);

        CarEntity car = entityService.getById(carId);
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

    @Override
    public void changeStatus(CarEntity carEntity, DefaultVehicleStatus status) {

        boolean available;
        switch (status) {
            case AVAILABLE, BOOKED -> {
                available = true;
            }
            case DELETED -> {
                available = false;
                carEntity.setIsDeleted(true);
                carEntity.setDeletedAt(LocalDateTime.now());
            }
            case IN_USE, MAINTENANCE, UNAVAILABLE -> {
                available = false;
            }
            default -> {
                throw new DataNotFoundException(VEHICLE_STATUS_NOT_FOUND);
            }
        }
        carEntity.setVehicleStatusEntity(vehicleStatusManager.getByStatus(status));
        carEntity.setAvailable(available);
        entityService.update(carEntity);
    }

    private List<CarDTO> mapToDTOList(List<CarEntity> cars) {
        return cars.stream()
                .map(car -> mapper.forResponse().map(car, CarDTO.class))
                .toList();
    }

    private CarDTO mapToDTO(CarEntity car) {
        return mapper.forResponse().map(car, CarDTO.class);
    }


}
