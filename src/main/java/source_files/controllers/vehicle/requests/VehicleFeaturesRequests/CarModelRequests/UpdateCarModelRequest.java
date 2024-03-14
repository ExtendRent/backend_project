package source_files.controllers.vehicle.requests.VehicleFeaturesRequests.CarModelRequests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCarModelRequest {
    @NotNull(message = "id null olamaz")
    int carModelEntityId;

    @NotNull(message = "Model null olamaz")
    @NotBlank(message = "Model adı boş geçilemez")
    @Size(min = 2, message = "Model en az 2 karakter olmalıdır.")
    String carModelEntityName;

    @NotNull(message = "Marka null olamaz")
    int brandEntityId;
}
