package src.repository.paperwork.rental;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import src.controller.paperwork.rental.responses.RentalResponse;
import src.repository.BaseEntity;
import src.repository.paperwork.discount.DiscountEntity;
import src.repository.paperwork.payment.detail.PaymentDetailsEntity;
import src.repository.paperwork.rental.status.RentalStatusEntity;
import src.repository.user.customer.CustomerEntity;
import src.repository.vehicle.car.CarEntity;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder(builderMethodName = "rentalBuilder")
//@Inheritance(strategy = InheritanceType.JOINED)
//@SuperBuilder
@Table(name = "rentals")
public class RentalEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customerEntity;
    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private CarEntity carEntity;

    @Column(name = "start_kilometer") //EndKilometer ve ReturnDate null bırakılmalıdır.
    private Integer startKilometer = null;

    @Column(name = "end_kilometer")
    private Integer endKilometer = null;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @ManyToOne
    @JoinColumn(name = "discount_id")
    private DiscountEntity discountEntity;

    @OneToOne(mappedBy = "rentalEntity")
    private PaymentDetailsEntity paymentDetailsEntity;

    @Column(name = "return_date")
    private LocalDate returnDate;

    @ManyToOne
    @JoinColumn(name = "rental_status")
    private RentalStatusEntity rentalStatusEntity;

    @Column(name = "is_active")
    private boolean isActive = true;

    @PreRemove
    private void preRemove() {
        if (carEntity != null) {
            carEntity.getRentalList().remove(this);
        }
    }

    public RentalResponse toModel() {
        return RentalResponse.builder()
                .id(getId())
                .customerEntityId(getCustomerEntity().getId())
                .carBodyTypeEntityName(getCarEntity().getCarBodyTypeEntity().getName())
                .carEntityBrandEntityName(getCarEntity().getCarModelEntity().getBrandEntity().getName())
                .carEntityColorEntityName(getCarEntity().getColorEntity().getName())
                .carEntityId(getCarEntity().getId())
                .carEntityLicensePlate(getCarEntity().getLicensePlate())
                .carEntityModelEntityName(getCarEntity().getCarModelEntity().getName())
                .carEntityRentalPrice(getCarEntity().getRentalPrice())
                .carEntityYear(getCarEntity().getYear())
                .customerEntityName(getCustomerEntity().getName())
                .customerEntitySurname(getCustomerEntity().getSurname())
                .discountEntityId(getDiscountEntity().getId())
                .discountEntityDiscountCode(getDiscountEntity().getDiscountCode())
                .endDate(getEndDate())
                .paymentDetailsEntityAmount(getPaymentDetailsEntity().getAmount())
                .paymentDetailsEntityPaymentTypeEntityPaymentTypeName(
                        getPaymentDetailsEntity().getPaymentTypeEntity().getName())
                .rentalStatusEntityId(getRentalStatusEntity().getId())
                .rentalStatusEntityName(getRentalStatusEntity().getName())
                .startDate(getStartDate())
                .build();
    }

}
