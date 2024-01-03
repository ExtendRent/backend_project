package source_files.data.requests.itemRequests.VehicleFeaturesRequests.CarBodyTypeRequests;

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
public class AddCarBodyTypeRequest implements BaseRequest {
    @Size(min = 2, message = "Body Type en az 2 karakter olmalıdır.")
    String carBodyTypeEntityName;
}
