package source_files.services.vehicleService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.vehicleDTOs.CarDTO;
import source_files.data.models.vehicleEntities.CarEntity;
import source_files.data.requests.vehicleRequests.CarRequests.AddCarRequest;
import source_files.data.requests.vehicleRequests.CarRequests.UpdateCarRequest;
import source_files.dataAccess.vehicleRepositories.CarRepository;
import source_files.services.BusinessRules.CarBusinessRules;
import source_files.services.entityServices.CarEntityManager;
import source_files.services.vehicleService.abstracts.CarService;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarManager implements CarService {

    private final ModelMapperService modelMapperService;
    private final CarEntityManager carEntityManager;

    private final CarRepository carRepository;
    private CarBusinessRules businessRules;

    @Override
    public CarDTO add(AddCarRequest addCarRequest) {
        //TODO:DTO DAN ENTİTYLER NULL GELİYOR TEKRAR KONTROL ET
        //addCarRequest.setLicensePlate(addCarRequest.getLicensePlate().toUpperCase().replaceAll("\s", ""));
        String editPlate = this.businessRules.licensePlateUnique(addCarRequest.getLicensePlate());
        addCarRequest.setLicensePlate(editPlate);
        CarEntity carEntity = modelMapperService.forRequest().map(addCarRequest, CarEntity.class);
        CarDTO carDTO = modelMapperService.forResponse().map(carEntityManager.add(carEntity),CarDTO.class);
        return carDTO;
    }

    @Override
    public CarDTO getById(int id) {
        return this.modelMapperService.forResponse().map(carEntityManager.getById(id), CarDTO.class);
    }

    @Override
    public CarDTO update(UpdateCarRequest updateCarRequest) {

        //CarEntity carEntity = carEntityManager.getById(updateCarRequest.getId());

        CarEntity carEntity = modelMapperService.forRequest().map(updateCarRequest, CarEntity.class);
        carEntity = carEntityManager.update(carEntity);

        return modelMapperService.forResponse().map(carEntity, CarDTO.class);

    }

    @Override
    public List<CarDTO> getAll() {
        List<CarEntity> carEntityList = carEntityManager.getAll();
        List<CarDTO> carDTOList=carEntityList.stream()
                .map(carEntity -> this.modelMapperService.forResponse()
                .map(carEntity,CarDTO.class)).collect(Collectors.toList());
        return carDTOList;

    }

    @Override
    public void delete(int id) {
        this.carEntityManager.delete(this.carEntityManager.getById(id));
    }
}
