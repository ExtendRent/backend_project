package source_files.data.requests.itemRequests.discountRequests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDiscountCodeRequest {
    String discountCode;

    int discountPercentage;

    boolean isActive;
}
