package source_files.core.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import source_files.data.DTO.itemDTOs.BrandDTO;
import source_files.data.Status.UserStatus;
import source_files.data.requests.paperworkRequests.discountRequests.CreateDiscountRequest;
import source_files.data.requests.paperworkRequests.paymentRequests.CreatePaymentTypeRequest;
import source_files.data.requests.userRequests.CreateAdminRequest;
import source_files.data.requests.userRequests.CreateCustomerRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.BrandRequests.CreateBrandRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarBodyTypeRequests.CreateCarBodyTypeRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarModelRequests.CreateCarModelRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.ColorRequests.CreateColorRequest;
import source_files.data.types.itemTypes.DrivingLicenseType;
import source_files.data.types.itemTypes.PaymentType;
import source_files.exception.DataNotFoundException;
import source_files.services.entityServices.abstracts.userAbstract.UserEntityService;
import source_files.services.paperWorkServices.abstracts.DiscountService;
import source_files.services.paperWorkServices.abstracts.PaymentTypeService;
import source_files.services.userServices.abstracts.AdminService;
import source_files.services.userServices.abstracts.CustomerService;
import source_files.services.vehicleFeaturesServices.abstracts.BrandService;
import source_files.services.vehicleFeaturesServices.abstracts.CarBodyTypeService;
import source_files.services.vehicleFeaturesServices.abstracts.CarModelService;
import source_files.services.vehicleFeaturesServices.abstracts.ColorService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static source_files.data.types.itemTypes.DrivingLicenseType.*;
import static source_files.data.types.itemTypes.PaymentType.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class SeedDataConfig implements CommandLineRunner {

    private final UserEntityService userEntityService;
    private final AdminService adminServices;

    private final CustomerService customerService;

    private final PasswordEncoder passwordEncoder;

    private final DiscountService discountService;
    private final ColorService colorService;

    private final BrandService brandService;
    private final CarModelService carModelService;

    private final CarBodyTypeService carBodyTypeService;

    private final PaymentTypeService paymentTypeService;

    @Override
    public void run(String... args) {
        try {
            paymentTypeService.getAll();
        } catch (DataNotFoundException e) {
            HashMap<PaymentType, String> paymentTypes = new HashMap<>();
            paymentTypes.put(CREDIT_CARD, "Kredi Kartı");
            paymentTypes.put(CASH, "Ofiste Ödeme");
            paymentTypes.put(BANK_MONEY_TRANSFER, "Havale");

            for (PaymentType paymentType : paymentTypes.keySet()) {
                paymentTypeService.create(new CreatePaymentTypeRequest(paymentTypes.get(paymentType), paymentType, true));
            }
        }

        try {
            carBodyTypeService.getAll();
        } catch (DataNotFoundException e) {
            String[] carBodyTypes = {"Sedan", "Hatchback", "Coupe", "SUV", "Pickup"};
            for (String carBodyType : carBodyTypes) {
                carBodyTypeService.create(new CreateCarBodyTypeRequest(carBodyType));
            }
        }

        try {
            brandService.getAll();
        } catch (DataNotFoundException e) {
            String[] brands = {"Audi", "BMW", "Mercedes", "Honda", "Toyota"};
            for (String brand : brands) {
                brandService.create(new CreateBrandRequest(brand));
            }
        }

        try {
            carModelService.getAll();
        } catch (DataNotFoundException e) {
            List<BrandDTO> brands = this.brandService.getAll();
            String[] carModels = {"A4", "M3", "c180", "Civic", "Corolla"};
            for (BrandDTO brand : brands) {
                carModelService.create(new CreateCarModelRequest(brand.getId(), carModels[brands.indexOf(brand)]));
            }
        }

        try {
            colorService.getAll();
        } catch (DataNotFoundException e) {
            String[] colors = {"White", "Black", "Red", "Green", "Blue"};
            for (String color : colors) {
                colorService.create(new CreateColorRequest(color));
            }
        }


        try {
            discountService.getAll();
        } catch (DataNotFoundException e) {
            discountService.create(new CreateDiscountRequest("PAIR5", 20));
            discountService.create(new CreateDiscountRequest("HOSGELDIN", 10));
        }

        //--------------------SEED USERS--------------------

        if (userEntityService.getAll().toArray().length == 0) {

            customerService.create(CreateCustomerRequest.builder()
                    .name("customer")
                    .surname("customer")
                    .phoneNumber("11111111111")
                    .emailAddress("customer@gmail.com")
                    .password(passwordEncoder.encode("pass"))
                    .drivingLicenseNumber("123456")
                    .drivingLicenseTypes(new ArrayList<DrivingLicenseType>() {{
                        add(A);
                        add(B);
                        add(BE);
                        add(C1);
                        add(E);
                    }})
                    .imagePath("https://img.memurlar.net/galeri/4599/2cc5bb86-a578-e311-a7bb-14feb5cc13c9.jpg?width=800")
                    .status(UserStatus.VERIFIED)
                    .build());


            adminServices.create(CreateAdminRequest.builder()
                    .name("admin")
                    .surname("admin")
                    .phoneNumber("22222222222")
                    .emailAddress("admin@gmail.com")
                    .password(passwordEncoder.encode("pass"))
                    .imagePath("https://avatars.githubusercontent.com/u/92371744?v=4")
                    .salary(10000.00)
                    .status(UserStatus.VERIFIED)
                    .build());
        }
    }
}
