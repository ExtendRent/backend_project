package source_files.data.models.paperWorkEntities.rentalEntities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import source_files.data.models.baseEntities.Item;
import source_files.data.models.paperWorkEntities.paymentEntities.PaymentDetailsEntity;
import source_files.data.models.userEntities.CustomerEntity;
import source_files.data.models.vehicleEntities.CarEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@SuperBuilder
@Table(name = "rental_details")
public class RentalDetailsEntity extends Item {

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customerEntity;

    @ManyToOne
    @JoinColumn(name = "car_id")

    private CarEntity carEntity;

    @ManyToOne
    @JoinColumn(name = "payment_detail_id")
    private PaymentDetailsEntity paymentDetailsEntity;

    @Column(name = "start_date")
    //@Builder.Default
    private LocalDateTime startDate = LocalDateTime.now();

    @Column(name = "end_date")
    private LocalDateTime endDate;

}
