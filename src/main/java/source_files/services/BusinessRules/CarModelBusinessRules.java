package source_files.services.BusinessRules;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.requests.itemRequests.VehicleFeaturesRequests.CarModelRequests.AddCarModelRequest;
import source_files.data.requests.itemRequests.VehicleFeaturesRequests.CarModelRequests.UpdateCarModelRequest;
import source_files.dataAccess.vehicleFeaturesRespositories.CarModelRepository;
import source_files.exception.DataNotFoundException;
import source_files.services.entityServices.abstracts.vehicleFeaturesAbstracts.BrandEntityService;

import java.util.List;

import static source_files.exception.NotFoundExceptionType.CAR_LIST_NOT_FOUND;

@AllArgsConstructor
@Service
public class CarModelBusinessRules implements BaseBusinessRulesService {
    private final CarModelRepository carModelRepository;
    private final BrandEntityService brandEntityService;

    @Override
    public List<?> checkDataList(List<?> list) {
        if (list.isEmpty()) {
            throw new DataNotFoundException(CAR_LIST_NOT_FOUND, "Aradığınız kriterlere uygun araba modeli bulunamadı");
        }
        return list;
    }

    public AddCarModelRequest fixAddCarModelRequest(AddCarModelRequest addCarModelRequest) {
        addCarModelRequest.setCarModelEntityName(this.fixName(addCarModelRequest.getCarModelEntityName()));
        return addCarModelRequest;
    }

    public AddCarModelRequest checkAddCarModelRequest(AddCarModelRequest addCarModelRequest) {
        this.existsByName(addCarModelRequest.getCarModelEntityName());
        this.checkBrand(addCarModelRequest.getBrandEntityId());
        return addCarModelRequest;
    }

    public UpdateCarModelRequest fixUpdateCarModelRequest(UpdateCarModelRequest updateCarModelRequest) {
        updateCarModelRequest.setCarModelEntityName(this.fixName(updateCarModelRequest.getCarModelEntityName()));
        return updateCarModelRequest;
    }

    public UpdateCarModelRequest checkUpdateCarModelRequest(UpdateCarModelRequest updateCarModelRequest) {
        this.existsByName(updateCarModelRequest.getCarModelEntityName());
        this.checkBrand(updateCarModelRequest.getBrandEntityId());
        return updateCarModelRequest;
    }

    @Override
    public String fixName(String name) {
        return name.trim().toLowerCase();
    }

    //---------------AUTO CHECKING METHODS--------------------------------
    public void existsByName(String name) {
        if (carModelRepository.existsByName(name)) {
            throw new IllegalStateException("This model already exist");
        }
    }

    public void checkBrand(int brandId) {
        this.brandEntityService.getById(brandId); //Eğer db de böyle bir brand yok ise brandEntityManager ile hatayı fırlatacak.
    }

}
