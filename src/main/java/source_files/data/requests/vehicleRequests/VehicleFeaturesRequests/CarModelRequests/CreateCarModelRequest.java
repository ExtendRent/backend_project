package source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarModelRequests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import source_files.data.requests.BaseRequest;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarModelRequest implements BaseRequest {
    @NotNull(message = "Marka null olamaz")
    int brandEntityId;

    @NotNull(message = "Model null olamaz")
    @NotBlank(message = "Model adı boş geçilemez")
    @Size(min = 2, message = "Model en az 2 karakter olmalıdır.")
    String carModelEntityName;

}
