package source_files.services.BusinessRules;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.dataAccess.vehicleRepositories.CarRepository;
@AllArgsConstructor
@Service
public class CarBusinessRules {
    private final CarRepository carRepository;
    public String plateUniqueness (String plate){

        String licensePlate = plate.replace(" ", "").toUpperCase();
        boolean result = this.carRepository.existsByLicensePlate(licensePlate);
        if (result) {
            throw new IllegalArgumentException("Car Plate already exists! : " + plate);
        }

        return licensePlate;
    }

}
