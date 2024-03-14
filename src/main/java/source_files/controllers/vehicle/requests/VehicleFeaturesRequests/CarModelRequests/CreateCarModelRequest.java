package source_files.controllers.vehicle.requests.VehicleFeaturesRequests.CarModelRequests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarModelRequest {
    @NotNull(message = "Marka null olamaz")
    int brandEntityId;

    @NotNull(message = "Model null olamaz")
    @NotBlank(message = "Model adı boş geçilemez")
    @Size(min = 2, message = "Model en az 2 karakter olmalıdır.")
    String carModelEntityName;

}
