package source_files.data.models.paperWorkEntities.rentalEntities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import source_files.data.models.baseEntities.Item;
import source_files.data.models.paperWorkEntities.paymentEntities.DiscountCodeEntity;
import source_files.data.models.paperWorkEntities.paymentEntities.PaymentDetailsEntity;
import source_files.data.models.userEntities.CustomerEntity;
import source_files.data.models.vehicleEntities.CarEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
//@SuperBuilder
@Table(name = "rental")
public class RentalEntity extends Item {

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customerEntity;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private CarEntity carEntity;

    @Column(name = "start_kilometer") //EndKilometer ve ReturnDate null bırakılmalıdır.
    private Integer startKilometer = null;

    @Column(name = "end_kilometer")
    private Integer endKilometer = null;

    @Column(name = "start_date")
    //@Builder.Default
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @ManyToOne
    @JoinColumn(name = "discount_id")
    private DiscountCodeEntity discountCode;

    @ManyToOne
    @JoinColumn(name = "payment_detail_id")
    private PaymentDetailsEntity paymentDetailsEntity;

    @Column(name = "return_date")
    private LocalDateTime returnDate;

    @Column(name = "is_active")
    private boolean isActive = true;

}
