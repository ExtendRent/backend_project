package source_files.data.requests.itemRequests.VehicleFeaturesRequests.ColorRequests;

import jakarta.validation.constraints.Size;
import lombok.*;
import source_files.data.requests.BaseRequest;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateColorRequest extends BaseRequest {
    int id;
    @Size(min = 2, message = "Renk en az 2 karakter olmalıdır.")
    String name;
}
