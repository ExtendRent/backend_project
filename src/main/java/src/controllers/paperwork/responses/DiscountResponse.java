package src.controllers.paperwork.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DiscountResponse {

    int id;

    String discountCode;

    int discountPercentage;

    boolean isActive;

}
