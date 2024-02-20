package source_files.data.DTO.paperWorkDTOs;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RentalDTO {
    int id;
    int customerEntityId;
    int discountEntityId;
    int rentalStatusEntityId;
    String customerEntityName;
    String customerEntitySurname;

    int CarEntityId;
    String carEntityBrandEntityName;
    String carEntityModelEntityName;
    String carEntityColorEntityName;
    String carBodyTypeEntityName;
    int carEntityYear;
    double carEntityRentalPrice;
    String carEntityLicensePlate;

    LocalDate startDate;
    LocalDate endDate;
    LocalDate returnDate;

    double paymentDetailsEntityAmount;
    String paymentDetailsEntityPaymentTypeEntityPaymentTypeName;
    String rentalStatusEntityName;
    String discountEntityDiscountCode;
    boolean isActive;
    boolean isDeleted;
}
