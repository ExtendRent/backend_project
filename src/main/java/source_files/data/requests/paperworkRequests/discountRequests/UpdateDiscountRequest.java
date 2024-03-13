package source_files.data.requests.paperworkRequests.discountRequests;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDiscountRequest {


    @NotNull(message = "id null olamaz")
    int id;

    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Discount code must consist of letters and numbers only")
    String discountCode;

    @Min(value = 5, message = "Discount percentage must be greater than or equal to 5")
    @Max(value = 90, message = "Discount percentage must be less than or equal to 90")
    @NotNull(message = "Discount percentage cannot be blank")
    int discountPercentage;
    @NotNull
    boolean isActive;


}
