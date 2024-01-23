package source_files.data.requests.paperworkRequests.discountRequests;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateDiscount {
    @NotBlank(message = "Discount code cannot be blank")
    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Discount code must consist of letters and numbers only")
    String discountCode;

    @Min(value = 5, message = "Discount percentage must be greater than or equal to 5")
    @Max(value = 90, message = "Discount percentage must be less than or equal to 90")
    @Pattern(regexp = "^[0-9]+$", message = "Discount percentage must consist of numbers only")
    @NotNull
    @NotBlank(message = "Discount percentage cannot be blank")
    int discountPercentage;
}
