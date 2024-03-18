package src.controller.discount.responses;

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

    @Override
    public String toString() {
        return "DiscountResponse{" +
                "id=" + id +
                ", discountCode='" + discountCode + '\'' +
                ", discountPercentage=" + discountPercentage +
                ", isActive=" + isActive +
                '}';
    }
}
