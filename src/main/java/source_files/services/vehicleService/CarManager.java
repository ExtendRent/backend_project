package source_files.services.vehicleService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.vehicleDTOs.CarDTO;
import source_files.data.models.vehicleEntities.CarEntity;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.ImagesEntity;
import source_files.data.requests.vehicleRequests.CarRequests.AddCarRequest;
import source_files.data.requests.vehicleRequests.CarRequests.UpdateCarRequest;
import source_files.services.BusinessRules.CarBusinessRules;
import source_files.services.entityServices.abstracts.CarEntityService;
import source_files.services.vehicleService.abstracts.CarService;

import java.util.List;
import java.util.stream.Collectors;

import static source_files.data.types.VehicleType.CAR;

@Service
@AllArgsConstructor
public class CarManager implements CarService {

    private final ModelMapperService modelMapperService;
    private final CarEntityService carEntityService;
    private CarBusinessRules businessRules;

    @Override
    public CarDTO add(AddCarRequest addCarRequest) {
        //TODO:DTO DAN ENTİTYLER NULL GELİYOR TEKRAR KONTROL ET

        //CarEntity carEntity = modelMapperService.forRequest().map(addCarRequest, CarEntity.class); //ESKİ KOD !

        CarEntity carEntity = modelMapperService.forRequest().map(this.businessRules                 //YENİ KOD
                .checkAddCarRequest(businessRules.fixAddCarRequest(addCarRequest)), CarEntity.class);

        carEntity.setVehicleType(CAR);

        //ImagesEntity içerisinde kullandığımız oneToOne bağlantısındaki cascade sayesinde, CarEntity üzerinden
        //yapılan ImagesEntity değişiklikleri ana klastaki(ImagesEntity) de de değişecek.
        //Yani carEntity ye ImagesEntity set ettiğimizde, database e de eklenecek ve içindeki CarEntity otomatik oluşacak.
        carEntity.setImagesEntity(new ImagesEntity(carEntity, addCarRequest.getImagePaths()));

        return modelMapperService.forResponse().map(carEntityService.add(carEntity), CarDTO.class);
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
    public List<CarDTO> getAll() throws Exception {
        //bilgi: önce gelen listenin boş olup olmadığını kontrol ediyoruz. boş değilse listeyi dönüyor.
        return businessRules.checkDataList(carEntityService.getAll())
                .stream()
                .map(car -> modelMapperService.forResponse().map(car, CarDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CarDTO> getAllByIsDeletedFalse() {
        return this.businessRules.checkDataList(this.carEntityService.getAllByIsDeletedFalse())
                .stream().map(carEntity -> modelMapperService.forResponse().map(carEntity, CarDTO.class)).toList();
    }

    @Override
    public List<CarDTO> getAllByIsDeletedTrue() {
        return this.businessRules.checkDataList(this.carEntityService.getAllByIsDeletedTrue())
                .stream().map(carEntity -> modelMapperService.forResponse().map(carEntity, CarDTO.class)).toList();
    }

    @Override
    public List<CarDTO> getAllByIsAvailableTrue() {
        return this.businessRules.checkDataList(this.carEntityService.getAllByIsAvailableTrue())
                .stream().map(carEntity -> modelMapperService.forResponse().map(carEntity, CarDTO.class)).toList();
    }

    @Override
    public List<CarDTO> getAllByIsAvailableFalse() {
        return this.businessRules.checkDataList(this.carEntityService.getAllByIsAvailableFalse())
                .stream().map(carEntity -> modelMapperService.forResponse().map(carEntity, CarDTO.class)).toList();
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
    public List<CarDTO> getAllByYearBetween(int year1, int year2) {
        return this.businessRules.checkDataList(this.carEntityService.getAllByYearBetween(year1, year2))
                .stream().map(carEntity -> modelMapperService.forResponse().map(carEntity, CarDTO.class)).toList();
    }

    @Override
    public void delete(int id, boolean hardDelete) {

        if (hardDelete) {
            this.hardDelete(id);
        } else {
            this.softDelete(id);
        }
    }


    @Override
    public void hardDelete(int id) {
        this.carEntityService.delete(this.carEntityService.getById(id));
    }

    @Override
    public void softDelete(int id) {
        CarEntity carEntity = this.carEntityService.getById(id);
        carEntity.setIsDeleted(true);
        carEntity.setIsAvailable(false);
        this.carEntityService.update(carEntity);
    }
}
