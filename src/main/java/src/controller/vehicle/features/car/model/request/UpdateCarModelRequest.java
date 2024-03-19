package src.controller.vehicle.features.car.model.request;

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

    @NotBlank(message = "Model adı boş geçilemez")
    @Size(min = 2, message = "Model en az 2 karakter olmalıdır.")
    String carModelEntityName;

    @NotNull(message = "Marka null olamaz")
    int brandEntityId;

    @Override
    public String toString() {
        return "UpdateCarModelRequest{" +
                "carModelEntityId=" + carModelEntityId +
                ", carModelEntityName='" + carModelEntityName + '\'' +
                ", brandEntityId=" + brandEntityId +
                '}';
    }
}
