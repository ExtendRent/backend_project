package src.controller.discount.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class DiscountResponse {

    int id;

    String discountCode;

    int discountPercentage;

    boolean isActive;

}
