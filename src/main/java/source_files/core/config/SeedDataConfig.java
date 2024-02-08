package source_files.core.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import source_files.data.DTO.itemDTOs.BrandDTO;
import source_files.data.Status.DefaultVehicleStatus;
import source_files.data.models.paperWorkEntities.paymentEntities.CreditCardInformation;
import source_files.data.requests.CreateDrivingLicenseTypeRequest;
import source_files.data.requests.paperworkRequests.RentalRequests.CreateRentalRequest;
import source_files.data.requests.paperworkRequests.discountRequests.CreateDiscountRequest;
import source_files.data.requests.paperworkRequests.paymentRequests.CreatePaymentTypeRequest;
import source_files.data.requests.userRequests.CreateAdminRequest;
import source_files.data.requests.userRequests.CreateCustomerRequest;
import source_files.data.requests.userRequests.CreateEmployeeRequest;
import source_files.data.requests.vehicleRequests.CarRequests.CreateCarRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.BrandRequests.CreateBrandRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarBodyTypeRequests.CreateCarBodyTypeRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarModelRequests.CreateCarModelRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.ColorRequests.CreateColorRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.FuelTypeRequests.CreateFuelTypeRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.ShiftTypeRequests.CreateShiftTypeRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.VehicleStatusRequests.CreateVehicleStatusRequest;
import source_files.data.types.itemTypes.DefaultCarDrivingLicenseType;
import source_files.data.types.itemTypes.DefaultPaymentType;
import source_files.exception.DataNotFoundException;
import source_files.services.DrivingLicenseTypeService;
import source_files.services.entityServices.abstracts.userAbstract.UserEntityService;
import source_files.services.paperWorkServices.abstracts.DiscountService;
import source_files.services.paperWorkServices.abstracts.PaymentTypeService;
import source_files.services.paperWorkServices.abstracts.RentalService;
import source_files.services.userServices.abstracts.AdminService;
import source_files.services.userServices.abstracts.CustomerService;
import source_files.services.userServices.abstracts.EmployeeService;
import source_files.services.vehicleFeaturesServices.abstracts.*;
import source_files.services.vehicleService.abstracts.CarService;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class SeedDataConfig implements CommandLineRunner {
    private final RentalService rentalService;
    private final DrivingLicenseTypeService drivingLicenseTypeService;
    private final UserEntityService userEntityService;
    private final AdminService adminService;
    private final CustomerService customerService;
    private final EmployeeService employeeService;
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
            for (DefaultPaymentType paymentType : DefaultPaymentType.getAll()) {
                paymentTypeService.create(new CreatePaymentTypeRequest(paymentType.getLabel(), paymentType, true));
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
            HashMap<String, String> brands = new LinkedHashMap<>();
            brands.put("Audi", "https://www.carlogos.org/car-logos/audi-logo-2016-640.png");
            brands.put("BMW", "https://www.carlogos.org/car-logos/bmw-logo-2020-gray.png");
            brands.put("Tesla", "https://www.carlogos.org/car-logos/tesla-logo-2007-full-download.png");
            brands.put("Honda", "https://www.carlogos.org/car-logos/honda-logo-2000-full-640.png");
            brands.put("Toyota", "https://www.carlogos.org/car-logos/toyota-logo-2020-europe-640.png");
            for (String brand : brands.keySet()) {
                brandService.create(new CreateBrandRequest(brand, brands.get(brand)));
            }
        }

        try {
            carModelService.getAll();
        } catch (DataNotFoundException e) {
            List<BrandDTO> brands = brandService.getAll();
            String[] carModels = {"A4", "M3", "ModelY", "Civic", "Corolla"};
            brands.forEach(brand -> carModelService.create(
                    new CreateCarModelRequest(brand.getId(), carModels[brands.indexOf(brand)]))
            );
        }

        try {
            colorService.getAll();
        } catch (DataNotFoundException e) {
            String[] colors = {"Beyaz", "Siyah", "Kırmızı", "Yeşil", "Mavi"};
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
            for (DefaultVehicleStatus defaultVehicleStatus : DefaultVehicleStatus.getAll()) {
                vehicleStatusService.create(new CreateVehicleStatusRequest(defaultVehicleStatus.getLabel(), defaultVehicleStatus));
            }
        }

        try {
            drivingLicenseTypeService.getAll();
        } catch (DataNotFoundException e) {
            for (DefaultCarDrivingLicenseType defaultCarDrivingLicenseType : DefaultCarDrivingLicenseType.getAll()) {
                drivingLicenseTypeService.create(new CreateDrivingLicenseTypeRequest(
                        defaultCarDrivingLicenseType.name(),
                        defaultCarDrivingLicenseType.getLabel(),
                        defaultCarDrivingLicenseType.ordinal()));
            }
        }

        try {
            carService.getAll();
        } catch (DataNotFoundException e) {

            for (int i = 1; i <= 3; i++) {
                int x = 2;
                if (i == 3) {
                    x = 3;
                }
                carService.create(CreateCarRequest.builder()
                        .carModelEntityId(i).brandEntityId(i)
                        .colorEntityId(i).carBodyTypeEntityId(i)
                        .kilometer(10000 - i * 1000).details("lorem ipsum")
                        .seat(i + 3).year(2020 + i)
                        .luggage(2).imagePaths(Collections.singletonList("https://mediaservice.audi.com/media/live/50900/fly1400x601n1/8yabdc/2023.png?wid=850"))
                        .fuelTypeEntityId(i).licensePlate("46kk35" + i)
                        .rentalPrice(100 + i * 100).shiftTypeEntityId(i)
                        .vehicleStatusEntityId(1)
                        .isAvailable(true)
                        .expectedMinDrivingLicenseTypeId(x)
                        .build());
            }
        }


        //-------------------------------SEED USERS-----------------------------------------

        if (userEntityService.getAll().toArray().length == 0) {
            customerService.create(CreateCustomerRequest.builder()
                    .name("customer")
                    .surname("customer")
                    .phoneNumber("11111111111")
                    .emailAddress("customer@gmail.com")
                    .password("pass")
                    .drivingLicenseNumber("123456")
                    .drivingLicenseTypeEntityId(2)
                    .imagePath("https://img.memurlar.net/galeri/4599/2cc5bb86-a578-e311-a7bb-14feb5cc13c9.jpg?width=800")
                    .build());

            customerService.create(CreateCustomerRequest.builder()
                    .name("customer2")
                    .surname("customer2")
                    .phoneNumber("22222222222")
                    .emailAddress("customer2@gmail.com")
                    .password("pass")
                    .drivingLicenseNumber("123457")
                    .drivingLicenseTypeEntityId(3)
                    .imagePath("https://img.memurlar.net/galeri/4599/2cc5bb86-a578-e311-a7bb-14feb5cc13c9.jpg?width=800")
                    .build());

            adminService.create(CreateAdminRequest.builder()
                    .name("admin")
                    .surname("admin")
                    .phoneNumber("33333333333")
                    .emailAddress("admin@gmail.com")
                    .password("pass")
                    .imagePath("https://avatars.githubusercontent.com/u/92371744?v=4")
                    .salary(10000.00)
                    .build());

            employeeService.create(CreateEmployeeRequest.builder()
                    .name("employee")
                    .surname("employee")
                    .phoneNumber("44444444444")
                    .emailAddress("employee@gmail.com")
                    .password("pass")
                    .imagePath("https://avatars.githubusercontent.com/u/92371744?v=4")
                    .salary(10000.00)
                    .build()
            );

        }

        //-------------------------------SEED RENTALS-----------------------------------------

        try {
            rentalService.getAll();
        } catch (DataNotFoundException e) {
            CreditCardInformation creditCardInformation = CreditCardInformation.builder()
                    .cardOwnerName("customer")
                    .cardOwnerSurname("customer")
                    .cardNumber("1111111111111111")
                    .cvc("111")
                    .expirationDate(LocalDate.parse("2024-04-25"))
                    .build();
            rentalService.create(CreateRentalRequest.builder()
                    .customerEntityId(1).carEntityId(1)
                    .amount(1800.00)
                    .startDate(LocalDate.parse("2024-03-10"))
                    .endDate(LocalDate.parse("2024-03-15"))
                    .paymentTypeId(1)
                    .discountCode("HOSGELDIN")
                    .creditCardInformation(creditCardInformation)
                    .build());
        }
    }
}

