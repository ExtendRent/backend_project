package source_files.data.DTO.paperWorkDTOs;

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
