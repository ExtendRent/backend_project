package source_files.data.requests.itemRequests.RentalRequests;

import lombok.Getter;
import lombok.Setter;
import source_files.data.requests.BaseRequest;

import java.time.LocalDate;

@Getter
@Setter
public class UpdateRentalRequest extends BaseRequest {
    int rentalEntityId;
    LocalDate returnDate;
    boolean isActive;
}
