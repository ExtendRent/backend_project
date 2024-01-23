package source_files.services.vehicleService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.vehicleDTOs.CarDTO;
import source_files.data.models.vehicleEntities.CarEntity;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.ImagesEntity;
import source_files.data.requests.vehicleRequests.CarRequests.CreateCarRequest;
import source_files.data.requests.vehicleRequests.CarRequests.UpdateCarRequest;
import source_files.services.BusinessRules.vehicleBusinessRules.CarBusinessRules;
import source_files.services.entityServices.abstracts.vehicleAbstracts.CarEntityService;
import source_files.services.vehicleService.abstracts.CarService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static source_files.data.types.itemTypes.VehicleType.CAR;

@Service
@AllArgsConstructor
public class CarManager implements CarService {

    private final ModelMapperService modelMapperService;
    private final CarEntityService carEntityService;
    private CarBusinessRules businessRules;

    @Override
    public void create(CreateCarRequest createCarRequest) {
        //TODO:DTO DAN ENTİTYLER NULL GELİYOR TEKRAR KONTROL ET

        //CarEntity carEntity = modelMapperService.forRequest().map(createCarRequest, CarEntity.class); //ESKİ KOD !

        CarEntity carEntity = modelMapperService.forRequest().map(this.businessRules                 //YENİ KOD
                .checkCreateCarRequest(businessRules.fixCreateCarRequest(createCarRequest)), CarEntity.class);

        carEntity.setVehicleType(CAR);

        //ImagesEntity içerisinde kullandığımız oneToOne bağlantısındaki cascade sayesinde, CarEntity üzerinden
        //yapılan ImagesEntity değişiklikleri ana klastaki(ImagesEntity) de de değişecek.
        //Yani carEntity ye ImagesEntity set ettiğimizde, database e de eklenecek ve içindeki CarEntity otomatik oluşacak.
        carEntity.setImagesEntity(new ImagesEntity(carEntity, createCarRequest.getImagePaths()));

        this.carEntityService.create(carEntity);
    }

    @Override
    public CarDTO getById(int id) {
        return this.modelMapperService.forResponse().map(carEntityService.getById(id), CarDTO.class);
    }

    @Override
    public CarDTO update(UpdateCarRequest updateCarRequest) {

        return modelMapperService.forResponse().map(
                carEntityService.update(modelMapperService.forRequest().map(
                        this.businessRules.checkUpdateCarRequest(
                                this.businessRules.fixUpdateCarRequest(updateCarRequest)
                        ), CarEntity.class
                )), CarDTO.class
        );

    }

    @Override
    public List<CarDTO> getAll() {
        //bilgi: önce gelen listenin boş olup olmadığını kontrol ediyoruz. boş değilse listeyi dönüyor.
        return businessRules.checkDataList(carEntityService.getAll())
                .stream()
                .map(car -> modelMapperService.forResponse().map(car, CarDTO.class))
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
        return this.businessRules.checkDataList(
                this.businessRules.getCarEntityListByMatchedDrivingLicense(this.carEntityService.getAll(), customerId)).stream().map(
                carEntity -> modelMapperService.forResponse().map(carEntity, CarDTO.class)).toList();
    }

    @Override
    public List<CarDTO> getAllByDeletedState(boolean isDeleted) {
        return this.businessRules.checkDataList(this.carEntityService.getAllByDeletedState(isDeleted))
                .stream().map(carEntity -> modelMapperService.forResponse().map(carEntity, CarDTO.class)).toList();
    }

    @Override
    public List<CarDTO> getAllByAvailableState(boolean isAvailable) {
        return this.businessRules.checkDataList(
                        this.carEntityService.getAllByAvailability(isAvailable)).stream()
                .map(o -> modelMapperService.forResponse().map(o, CarDTO.class)).toList();
    }

    @Override
    public List<CarDTO> getAllByColorId(int id) {
        return this.businessRules.checkDataList(this.carEntityService.getAllByColorId(id))
                .stream().map(carEntity -> modelMapperService.forResponse().map(carEntity, CarDTO.class)).toList();
    }

    @Override
    public List<CarDTO> getAllByModelId(int id) {
        return this.businessRules.checkDataList(this.carEntityService.getAllByModelId(id))
                .stream().map(carEntity -> modelMapperService.forResponse().map(carEntity, CarDTO.class)).toList();
    }

    @Override
    public List<CarDTO> getAllByBrandId(int brandId) {
        return this.businessRules.checkDataList(this.carEntityService.getAllByBrandId(brandId))
                .stream().map(carEntity -> modelMapperService.forResponse().map(carEntity, CarDTO.class)).toList();
    }

    @Override
    public List<CarDTO> getAllByYearBetween(int startYear, int endYear) {
        return this.businessRules.checkDataList(this.carEntityService.getAllByYearBetween(startYear, endYear))
                .stream().map(carEntity -> modelMapperService.forResponse().map(carEntity, CarDTO.class)).toList();
    }

    @Override
    public List<CarDTO> getAllByRentalPriceBetween(double startPrice, double endPrice) {
        return this.businessRules.checkDataList(this.carEntityService.getAllByRentalPriceBetween(startPrice, endPrice))
                .stream().map(carEntity -> modelMapperService.forResponse().map(carEntity, CarDTO.class)).toList();
    }

    @Override
    public List<CarDTO> getAllByAvailabilityBetween(LocalDate startDate, LocalDate endDate) {
        LocalDate effectiveStartDate = startDate != null ? startDate : LocalDate.now();
        LocalDate effectiveEndDate = endDate != null ? endDate : effectiveStartDate.plusDays(25);
        this.businessRules.checkDates(effectiveStartDate, effectiveEndDate);

        return this.businessRules.checkDataList(
                        this.carEntityService.getAllByAvailabilityBetween(startDate, endDate))
                .stream().map(carEntity -> modelMapperService.forResponse().map(carEntity, CarDTO.class)).toList();
    }


    public List<CarDTO> getAllFiltered(Integer customerId,
                                       LocalDate startDate, LocalDate endDate,
                                       Integer startPrice, Integer endPrice,
                                       Boolean isDeleted, Boolean isAvailable,
                                       Integer colorId, String fuelType, String shiftType,
                                       Integer seat, Integer luggage, Integer modelId,
                                       Integer startYear, Integer endYear, Integer brandId) {

        Date startDateSql = startDate != null ? Date.valueOf(startDate) : null;
        Date endDateSql = endDate != null ? Date.valueOf(endDate) : null;

        List<CarEntity> filteredCars = carEntityService.getAllFiltered(
                startDateSql, endDateSql,
                startPrice, endPrice,
                isDeleted, isAvailable,
                colorId, fuelType,
                shiftType, seat, luggage,
                modelId, startYear,
                endYear, brandId);


        return this.businessRules.checkDataList(filteredCars).stream()
                .map(carEntity -> modelMapperService.forResponse()
                        .map(carEntity, CarDTO.class)).toList();
    }

    @Override
    public void delete(int id, boolean hardDelete) {

        if (hardDelete) {
            this.carEntityService.delete(this.carEntityService.getById(id));
        } else {
            this.softDelete(id);
        }
    }

    @Override
    public void softDelete(int id) {
        CarEntity carEntity = this.carEntityService.getById(id);
        carEntity.setIsDeleted(true);
        carEntity.setIsAvailable(false);
        this.carEntityService.update(carEntity);
    }


}
