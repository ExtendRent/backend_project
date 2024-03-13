package source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarSegmentRequests;

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
public class CreateCarSegmentRequest {
    @NotNull(message = "Segment null olamaz")
    @NotBlank(message = "Segment adı boş geçilemez")
    @Size(min = 2, message = "Segment en az 2 karakter olmalıdır.")
    String name;
}
