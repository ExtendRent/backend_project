package source_files;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import source_files.services.paperWorkServices.payment.PaymentMain;


@SpringBootApplication
public class TobetoRentAcarCrewProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(TobetoRentAcarCrewProjectApplication.class, args);
    }
    @Bean
    public PaymentMain getPaymentMain() {
        return new PaymentMain();
    }

}
