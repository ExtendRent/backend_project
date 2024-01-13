package source_files.data.models.paperWorkEntities.rentalEntities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import source_files.data.models.paperWorkEntities.paymentEntities.DiscountCodeEntity;
import source_files.data.models.userEntities.CustomerEntity;
import source_files.data.models.vehicleEntities.CarEntity;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShowRental {

    private CustomerEntity customerEntity;

    private CarEntity carEntity;

    private LocalDate startDate;

    private LocalDate endDate;

    private DiscountCodeEntity discountCodeEntity;

    private double amount;

}
