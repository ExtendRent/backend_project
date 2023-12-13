package source_files.data.models.paperWorkEntities.rentalEntities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import source_files.data.models.baseEntities.Item;
import source_files.data.models.paperWorkEntities.paymentEntities.PaymentDetailsEntity;
import source_files.data.models.userEntities.CustomerEntity;
<<<<<<< HEAD
import source_files.data.models.vehicleEntities.Car;
=======
import source_files.data.models.vehicleEntities.CarEntity;
>>>>>>> 6f0e49bc3f15e414726904e2f7d2a8fcc79c707b

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
@Table(name = "rental_details")
public class RentalDetailsEntity extends Item {

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customerEntity;

    @ManyToOne
    @JoinColumn(name = "car_id")
<<<<<<< HEAD
    private Car car;
=======
    private CarEntity carEntity;
>>>>>>> 6f0e49bc3f15e414726904e2f7d2a8fcc79c707b

    @ManyToOne
    @JoinColumn(name = "payment_detail_id")
    private PaymentDetailsEntity paymentDetailsEntity;

    @Column(name = "start_date")
    @Builder.Default
    private LocalDateTime startDate = LocalDateTime.now();

    @Column(name = "end_date")
    private LocalDateTime endDate;

}
