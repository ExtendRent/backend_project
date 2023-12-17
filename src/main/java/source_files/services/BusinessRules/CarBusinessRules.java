package source_files.services.BusinessRules;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import source_files.dataAccess.vehicleFeaturesRespositories.CarModelRepository;
import source_files.dataAccess.vehicleFeaturesRespositories.ColorRepository;
import source_files.dataAccess.vehicleRepositories.CarRepository;

@AllArgsConstructor
@Service
public class CarBusinessRules{
    private final CarRepository carRepository;
    private final CarModelRepository carModelRepository;
    private final ColorRepository colorRepository;

    public String licensePlateUnique(String plate) {

        String licensePlate = plate.replace(" ", "").toUpperCase();
        return licensePlate;
    }
    public void existByModelIdAndColorId(int modelId,int colorId) {
        if (!(carModelRepository.existsById(modelId) && colorRepository.existsById(colorId))){
            throw new IllegalStateException("Model or color does not exist");
        }
    }
}
