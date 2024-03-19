package src.controller.vehicle.features.car.body.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarBodyTypeRequest {
    @NotNull(message = "id null olamaz")
    int id;
    @NotBlank(message = "Body Type adı boş geçilemez")
    @Size(min = 2, message = "Body Type en az 2 karakter olmalıdır.")
    @Pattern(regexp = "^[a-zA-ZğüşıöçĞÜŞİÖÇ]+$", message = "Body Type sadece harflerden oluşmalıdır.")
    String name;

    @Override
    public String toString() {
        return "UpdateCarBodyTypeRequest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
