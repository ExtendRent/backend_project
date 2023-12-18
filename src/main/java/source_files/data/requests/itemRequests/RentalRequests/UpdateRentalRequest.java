package source_files.data.requests.itemRequests.RentalRequests;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UpdateRentalRequest {
    int rentalEntityId;
    boolean isActive;
    LocalDateTime returnDate;
}
