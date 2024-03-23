package src.controller.vehicle.features.car.segment.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarSegmentRequest {

    @NotBlank(message = "Segment adı boş geçilemez")
    @Size(min = 2, message = "Segment en az 2 karakter olmalıdır.")
    String name;


}
