package source_files.data.DTO.paperWorkDTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiscountCodeDTO {

    int id;

    String discountCode;

    int discountPercentage;

    boolean isActive;

}
