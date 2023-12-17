package source_files.data.requests.itemRequests.VehicleFeaturesRequests.ColorRequests;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddColorRequest {
    @Size(min = 2, message = "Renk en az 2 karakter olmalıdır.")
    String name;
}
