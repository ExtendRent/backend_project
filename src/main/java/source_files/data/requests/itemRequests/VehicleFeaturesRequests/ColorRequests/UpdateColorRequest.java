package source_files.data.requests.itemRequests.VehicleFeaturesRequests.ColorRequests;

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
public class UpdateColorRequest implements BaseRequest {
    int id;
    @Size(min = 2, message = "Renk en az 2 karakter olmalıdır.")
    String name;
}
