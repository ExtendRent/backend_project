package source_files.data.requests.paperworkRequests.discountRequests;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDiscountCodeRequest {
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Discount code must consist of letters and numbers only")
    String discountCode;

    @Min(value = 0, message = "Discount percentage must be greater than or equal to 0")
    @Max(value = 100, message = "Discount percentage must be less than or equal to 100")
    int discountPercentage;

    boolean isActive;
}
