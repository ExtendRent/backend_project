package source_files.controllers.paperWork.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DiscountDTO {

    int id;

    String discountCode;

    int discountPercentage;

    boolean isActive;

}
