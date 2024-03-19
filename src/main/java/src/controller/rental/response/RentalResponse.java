package src.controller.rental.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RentalResponse {
    int id;
    int customerEntityId;
    int discountEntityId;
    int rentalStatusEntityId;
    String customerEntityName;
    String customerEntitySurname;

    int carEntityId;
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

    @Override
    public String toString() {
        return "RentalResponse{" +
                "id=" + id +
                ", customerEntityId=" + customerEntityId +
                ", discountEntityId=" + discountEntityId +
                ", rentalStatusEntityId=" + rentalStatusEntityId +
                ", customerEntityName='" + customerEntityName + '\'' +
                ", customerEntitySurname='" + customerEntitySurname + '\'' +
                ", carEntityId=" + carEntityId +
                ", carEntityBrandEntityName='" + carEntityBrandEntityName + '\'' +
                ", carEntityModelEntityName='" + carEntityModelEntityName + '\'' +
                ", carEntityColorEntityName='" + carEntityColorEntityName + '\'' +
                ", carBodyTypeEntityName='" + carBodyTypeEntityName + '\'' +
                ", carEntityYear=" + carEntityYear +
                ", carEntityRentalPrice=" + carEntityRentalPrice +
                ", carEntityLicensePlate='" + carEntityLicensePlate + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", returnDate=" + returnDate +
                ", paymentDetailsEntityAmount=" + paymentDetailsEntityAmount +
                ", paymentDetailsEntityPaymentTypeEntityPaymentTypeName='" + paymentDetailsEntityPaymentTypeEntityPaymentTypeName + '\'' +
                ", rentalStatusEntityName='" + rentalStatusEntityName + '\'' +
                ", discountEntityDiscountCode='" + discountEntityDiscountCode + '\'' +
                ", isActive=" + isActive +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
