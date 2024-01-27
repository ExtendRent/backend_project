package source_files.core.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import source_files.data.DTO.itemDTOs.BrandDTO;
import source_files.data.Status.DefaultUserStatus;
import source_files.data.requests.paperworkRequests.discountRequests.CreateDiscountRequest;
import source_files.data.requests.paperworkRequests.paymentRequests.CreatePaymentTypeRequest;
import source_files.data.requests.userRequests.CreateAdminRequest;
import source_files.data.requests.userRequests.CreateCustomerRequest;
import source_files.data.requests.vehicleRequests.CarRequests.CreateCarRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.BrandRequests.CreateBrandRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarBodyTypeRequests.CreateCarBodyTypeRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarModelRequests.CreateCarModelRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.ColorRequests.CreateColorRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.FuelTypeRequests.CreateFuelTypeRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.ShiftTypeRequests.CreateShiftTypeRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.VehicleStatusRequests.CreateVehicleStatusRequest;
import source_files.data.types.itemTypes.PaymentType;
import source_files.exception.DataNotFoundException;
import source_files.services.entityServices.abstracts.userAbstract.UserEntityService;
import source_files.services.paperWorkServices.abstracts.DiscountService;
import source_files.services.paperWorkServices.abstracts.PaymentTypeService;
import source_files.services.userServices.abstracts.AdminService;
import source_files.services.userServices.abstracts.CustomerService;
import source_files.services.vehicleFeaturesServices.abstracts.*;
import source_files.services.vehicleService.abstracts.CarService;

import java.util.*;

import static source_files.data.Status.DefaultVehicleStatus.*;
import static source_files.data.types.itemTypes.DrivingLicenseType.*;
import static source_files.data.types.itemTypes.PaymentType.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class SeedDataConfig implements CommandLineRunner {

    private final UserEntityService userEntityService;
    private final AdminService adminService;

    private final CustomerService customerService;

    private final PasswordEncoder passwordEncoder;

    private final DiscountService discountService;
    private final ColorService colorService;

    private final BrandService brandService;
    private final CarModelService carModelService;

    private final CarBodyTypeService carBodyTypeService;

    private final PaymentTypeService paymentTypeService;

    private final CarService carService;

    private final ShiftTypeService shiftTypeService;

    private final FuelTypeService fuelTypeService;

    private final VehicleStatusService vehicleStatusService;

    @Override
    public void run(String... args) {
        try {
            paymentTypeService.getAll();
        } catch (DataNotFoundException e) {
            HashMap<PaymentType, String> paymentTypes = new LinkedHashMap<>();
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
            String[] brands = {"Audi", "BMW", "Tesla", "Honda", "Toyota"};
            for (String brand : brands) {
                brandService.create(new CreateBrandRequest(brand));
            }
        }

        try {
            carModelService.getAll();
        } catch (DataNotFoundException e) {
            List<BrandDTO> brands = this.brandService.getAll();
            String[] carModels = {"A4", "M3", "ModelY", "Civic", "Corolla"};
            brands.forEach(brand -> carModelService.create(
                    new CreateCarModelRequest(brand.getId(), carModels[brands.indexOf(brand)]))
            );
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
            HashMap<String, Integer> discounts = new LinkedHashMap<>();
            discounts.put("DEFAULT", 10);
            discounts.put("PAIR5", 20);
            discounts.put("HOSGELDIN", 10);
            discounts.forEach((name, percentage) -> discountService.create(new CreateDiscountRequest(name, percentage)));
        }

        try {
            shiftTypeService.getAll();
        } catch (DataNotFoundException e) {
            String[] defaultShiftTypes = {"Yarı Otomatik", "Manuel", "Otomatik", "Triptonik", "Vites Yok"};
            for (String defaultShiftType : defaultShiftTypes) {
                shiftTypeService.create(new CreateShiftTypeRequest(defaultShiftType));
            }
        }

        try {
            fuelTypeService.getAll();
        } catch (DataNotFoundException e) {
            String[] defaultFuelTypes = {"Benzin", "Dizel", "Elektrik", "Hybrid", "Lpg", "Benzin Lpg", "Yakıt Yok"};
            for (String defaultFuelType : defaultFuelTypes) {
                fuelTypeService.create(new CreateFuelTypeRequest(defaultFuelType));
            }
        }

        try {
            vehicleStatusService.getAll();
        } catch (DataNotFoundException e) {
            HashMap<String, String> defaultVehicleStatuses = new LinkedHashMap<>();
            defaultVehicleStatuses.put(AVAILABLE.name(), "Kiralanabilir");
            defaultVehicleStatuses.put(IN_USE.name(), "Kullanımda");
            defaultVehicleStatuses.put(MAINTENANCE.name(), "Bakımda");
            defaultVehicleStatuses.put(UNAVAILABLE.name(), "Kullanım Dışı");
            defaultVehicleStatuses.put(BOOKED.name(), "Rezerve");
            defaultVehicleStatuses.put(DELETED.name(), "Sistem Dışı");
            defaultVehicleStatuses.forEach((status, name) ->
                    vehicleStatusService.create(new CreateVehicleStatusRequest(name, status)));
        }


        try {
            carService.getAll();
        } catch (DataNotFoundException e) {

            for (int i = 1; i <= 3; i++) {
                carService.create(CreateCarRequest.builder()
                        .carModelEntityId(i).brandEntityId(i)
                        .colorEntityId(i).carBodyTypeEntityId(i)
                        .kilometer(10000 - i*1000).details("lorem ipsum")
                        .seat(i + 3).year(2020 + i)
                        .luggage(2).imagePaths(Collections.singletonList("https://mediaservice.audi.com/media/live/50900/fly1400x601n1/8yabdc/2023.png?wid=850"))
                        .fuelTypeEntityId(i).licensePlate("46kk35" + i)
                        .rentalPrice(100 + i*100).shiftTypeEntityId(i)
                        .vehicleStatusEntityId(1)
                        .expectedDrivingLicenseTypes(new ArrayList<>() {{
                            add(A);
                            add(B);
                            add(BE);
                            add(C1);
                            add(E);
                        }}).build());
            }

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
                    .drivingLicenseTypes(new ArrayList<>() {{
                        add(A);
                        add(B);
                        add(BE);
                        add(C1);
                        add(E);
                    }})
                    .imagePath("https://img.memurlar.net/galeri/4599/2cc5bb86-a578-e311-a7bb-14feb5cc13c9.jpg?width=800")
                    .build());


            adminService.create(CreateAdminRequest.builder()
                    .name("admin")
                    .surname("admin")
                    .phoneNumber("22222222222")
                    .emailAddress("admin@gmail.com")
                    .password(passwordEncoder.encode("pass"))
                    .imagePath("https://avatars.githubusercontent.com/u/92371744?v=4")
                    .salary(10000.00)
                    .status(DefaultUserStatus.VERIFIED)
                    .build());
        }
    }
}
