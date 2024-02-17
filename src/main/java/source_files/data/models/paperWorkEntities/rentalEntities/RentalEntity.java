package source_files.data.models.paperWorkEntities.rentalEntities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import source_files.data.models.baseEntities.BaseEntity;
import source_files.data.models.paperWorkEntities.paymentEntities.DiscountEntity;
import source_files.data.models.paperWorkEntities.paymentEntities.PaymentDetailsEntity;
import source_files.data.models.userEntities.CustomerEntity;
import source_files.data.models.vehicleEntities.CarEntity;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
//@SuperBuilder
@Table(name = "rental")
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
    private LocalDate returnDate = null;

    @Column(name = "is_active")
    private boolean isActive = true;

    @PreRemove
    private void preRemove() {
        if (carEntity != null) {
            carEntity.getRentalList().remove(this);
        }
    }

}
