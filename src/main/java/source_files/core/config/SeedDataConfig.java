package source_files.core.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import source_files.data.DTO.itemDTOs.BrandDTO;
import source_files.data.enums.defaultDataEnums.*;
import source_files.data.enums.defaultDataEnums.Status.DefaultRentalStatus;
import source_files.data.enums.defaultDataEnums.Status.DefaultVehicleStatus;
import source_files.data.models.paperWorkEntities.paymentEntities.CreditCardInformation;
import source_files.data.models.paperWorkEntities.rentalEntities.RentalStatusEntity;
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
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarSegmentRequests.CreateCarSegmentRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.ColorRequests.CreateColorRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.FuelTypeRequests.CreateFuelTypeRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.ShiftTypeRequests.CreateShiftTypeRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.VehicleStatusRequests.CreateVehicleStatusRequest;
import source_files.exception.DataNotFoundException;
import source_files.services.DrivingLicenseTypeService;
import source_files.services.entityServices.abstracts.userAbstract.UserEntityService;
import source_files.services.paperWorkServices.abstracts.DiscountService;
import source_files.services.paperWorkServices.abstracts.PaymentTypeService;
import source_files.services.paperWorkServices.abstracts.RentalService;
import source_files.services.paperWorkServices.abstracts.RentalStatusService;
import source_files.services.systemServices.ImageServices.BrandImageService;
import source_files.services.systemServices.ImageServices.CarImageService;
import source_files.services.systemServices.ImageServices.UserImageService;
import source_files.services.userServices.abstracts.AdminService;
import source_files.services.userServices.abstracts.CustomerService;
import source_files.services.userServices.abstracts.EmployeeService;
import source_files.services.vehicleFeaturesServices.abstracts.*;
import source_files.services.vehicleService.abstracts.CarService;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;
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
    private final CarSegmentService carSegmentService;
    private final CarImageService carImageService;
    private final UserImageService userImageService;
    private final BrandImageService brandImageService;
    private final RentalStatusService rentalStatusService;

    @Override
    public void run(String... args) throws IOException {
        try {
            brandService.getAll();
        } catch (DataNotFoundException e) {
            for (DefaultBrand brandEnum : DefaultBrand.getAll()) {
                URL url = new URL(brandEnum.getImageUrl());
                Path path = Paths.get("src/main/assets/default/brand", brandEnum.name() + ".jpg");
                if (Files.exists(path)) {
                    Files.delete(path);
                }
                Files.copy(url.openStream(), path);
                MultipartFile file = new MockMultipartFile(
                        brandEnum.name(), path.getFileName().toString(), "image/jpeg", Files.readAllBytes(path));
                brandService.create(new CreateBrandRequest(brandEnum.getLabel(),
                        brandImageService.create(file, brandEnum.getLabel()).getId()));
            }
        }

        try {
            carModelService.getAll();
        } catch (DataNotFoundException e) {
            List<BrandDTO> brands = brandService.getAll();
            String[] carModels = Arrays.stream(DefaultCarModel.getAll()).map(DefaultCarModel::getLabel).toArray(String[]::new);
            brands.forEach(brand -> carModelService.create(
                    new CreateCarModelRequest(brand.getId(), carModels[brands.indexOf(brand)]))
            );
        }

        try {
            paymentTypeService.getAll();
        } catch (DataNotFoundException e) {
            for (DefaultPaymentType paymentType : DefaultPaymentType.getAll()) {
                paymentTypeService.create(new CreatePaymentTypeRequest(paymentType.getLabel(), paymentType, true));
            }
        }

        try {
            carSegmentService.getAll();
        } catch (DataNotFoundException e) {
            for (DefaultCarSegment carSegment : DefaultCarSegment.getAll()) {
                carSegmentService.create(new CreateCarSegmentRequest(carSegment.getLabel()));
            }
        }

        try {
            carBodyTypeService.getAll();
        } catch (DataNotFoundException e) {
            for (DefaultCarBodyType carBodyType : DefaultCarBodyType.getAll()) {
                carBodyTypeService.create(new CreateCarBodyTypeRequest(carBodyType.getLabel()));
            }
        }


        try {
            colorService.getAll();
        } catch (DataNotFoundException e) {
            for (DefaultColors defaultColor : DefaultColors.getAll()) {
                colorService.create(new CreateColorRequest(defaultColor.getLabel()));
            }
        }

        try {
            shiftTypeService.getAll();
        } catch (DataNotFoundException e) {
            for (DefaultShiftType defaultShiftType : DefaultShiftType.getAll()) {
                shiftTypeService.create(new CreateShiftTypeRequest(defaultShiftType.getLabel()));
            }
        }

        try {
            fuelTypeService.getAll();
        } catch (DataNotFoundException e) {
            for (DefaultFuelType defaultFuelType : DefaultFuelType.getAll()) {
                fuelTypeService.create(new CreateFuelTypeRequest(defaultFuelType.getLabel()));
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
            discountService.getAll();
        } catch (DataNotFoundException e) {
            for (DefaultDiscount defaultDiscount : DefaultDiscount.getAll()) {
                discountService.create(new CreateDiscountRequest(defaultDiscount.name(), defaultDiscount.getPercentage()));
            }
        }

        try {
            drivingLicenseTypeService.getAll();
        } catch (DataNotFoundException e) {
            for (DefaultCarDrivingLicenseType defaultCarDrivingLicenseType : DefaultCarDrivingLicenseType.getAll()) {
                drivingLicenseTypeService.create(new CreateDrivingLicenseTypeRequest(
                        defaultCarDrivingLicenseType.name(),
                        defaultCarDrivingLicenseType.getLabel(),
                        defaultCarDrivingLicenseType.ordinal())
                );
            }
        }

        try {
            rentalStatusService.getAll();
        } catch (DataNotFoundException e) {
            for (DefaultRentalStatus defaultRentalStatus : DefaultRentalStatus.getAll()) {
                rentalStatusService.create(new RentalStatusEntity(defaultRentalStatus.getLabel(), defaultRentalStatus));
            }
        }

        try {
            carService.getAll();
        } catch (DataNotFoundException e) {
            DefaultCarImageUrl[] defaultCarImageEnums = DefaultCarImageUrl.getAll();
            for (int i = 1; i <= 4; i++) {
                int x = 2;
                if (i == 3) {
                    x = 3;
                }
                DefaultCarImageUrl defaultCarImageEnum = defaultCarImageEnums[i - 1];

                URL url = new URL(defaultCarImageEnum.getLabel());
                Path path = Paths.get("src/main/assets/default/car", defaultCarImageEnum.name() + ".jpg");
                if (Files.exists(path)) {
                    Files.delete(path);
                }

                Files.copy(url.openStream(), path);

                MultipartFile file = new MockMultipartFile(
                        defaultCarImageEnum.name(), path.getFileName().toString(), "image/jpeg", Files.readAllBytes(path));


                carService.create(CreateCarRequest.builder()
                        .carModelEntityId(i).brandEntityId(i)
                        .colorEntityId(i).carBodyTypeEntityId(i)
                        .kilometer(10000 - i * 1000).details("lorem ipsum")
                        .seat(i + 3).year(2020 + i)
                        .luggage(2)
                        .fuelTypeEntityId(i).licensePlate("46kk35" + i)
                        .rentalPrice(100 + i * 100).shiftTypeEntityId(i)
                        .vehicleStatusEntityId(1)
                        .isAvailable(true)
                        .carImageEntityId(carImageService.create(file, "46kk35" + i).getId())
                        .expectedMinDrivingLicenseTypeId(x)
                        .carSegmentEntityId(i)
                        .build());
            }
        }


        //-------------------------------SEED USERS-----------------------------------------

        if (userEntityService.getAll().toArray().length == 0) {
            for (DefaultUserImageUrl defaultUserImageEnum : DefaultUserImageUrl.getAll()) {
                URL url = new URL(defaultUserImageEnum.getUrl());
                Path path = Paths.get("src/main/assets/default/user", defaultUserImageEnum.name() + ".jpg");
                if (Files.exists(path)) {
                    Files.delete(path);
                }
                Files.copy(url.openStream(), path);
                MultipartFile file = new MockMultipartFile(
                        defaultUserImageEnum.name(), path.getFileName().toString(), "image/jpeg", Files.readAllBytes(path));

                switch (defaultUserImageEnum) {

                    case ADMIN_IMAGE -> {
                        adminService.create(CreateAdminRequest.builder()
                                .name("admin")
                                .surname("admin")
                                .phoneNumber("11111111111")
                                .emailAddress("admin@gmail.com")
                                .password("pass")
                                .userImageEntityId(userImageService.create(file, "admin@gmail.com"))
                                .salary(10000.00)
                                .build());
                    }
                    case EMPLOYEE_IMAGE -> {
                        employeeService.create(CreateEmployeeRequest.builder()
                                .name("employee")
                                .surname("employee")
                                .phoneNumber("22222222222")
                                .emailAddress("employee@gmail.com")
                                .password("pass")
                                .userImageEntityId(userImageService.create(file, "employee@gmail.com"))
                                .salary(10000.00)
                                .build()
                        );
                    }
                    case CUSTOMER_IMAGE -> {
                        customerService.create(CreateCustomerRequest.builder()
                                .name("customer")
                                .surname("customer")
                                .phoneNumber("33333333333")
                                .emailAddress("customer@gmail.com")
                                .password("pass")
                                .drivingLicenseNumber("123456")
                                .drivingLicenseTypeEntityId(3)
                                .userImageEntityId(userImageService.create(file, "customer@gmail.com"))
                                .build());
                    }
                    case DEFAULT_USER_IMAGE -> {
                        int defaultImageID = userImageService.create(file, "default_user_image");
                        for (int i = 1; i <= 20; i++) {
                            String drivingLicenseNumber = String.valueOf((123456 + i));
                            String name = "defcustomer" + i;
                            String phoneNumber = String.valueOf(44444444444L + i);
                            String emailAddress = name + "@gmail.com";
                            customerService.create(CreateCustomerRequest.builder()
                                    .name(name)
                                    .surname(name)
                                    .phoneNumber(phoneNumber)
                                    .emailAddress(emailAddress)
                                    .password("pass")
                                    .drivingLicenseNumber(drivingLicenseNumber)
                                    .drivingLicenseTypeEntityId(2)
                                    .userImageEntityId(defaultImageID)
                                    .build());
                        }
                    }
                }
            }
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
                    .expirationDate(LocalDate.parse("2024-09-01"))
                    .build();
            rentalService.create(CreateRentalRequest.builder()
                    .customerEntityId(3).carEntityId(2)
                    .amount(1200.00)
                    .startDate(LocalDate.parse("2024-03-10"))
                    .endDate(LocalDate.parse("2024-03-15"))
                    .paymentTypeId(1)
                    .discountCode("HOSGELDIN")
                    .creditCardInformation(creditCardInformation)
                    .build());

            rentalService.create(CreateRentalRequest.builder()
                    .customerEntityId(4).carEntityId(1)
                    .amount(750.00)
                    .startDate(LocalDate.parse("2024-03-20"))
                    .endDate(LocalDate.parse("2024-03-25"))
                    .paymentTypeId(1)
                    .discountCode("PAIR5")
                    .creditCardInformation(creditCardInformation)
                    .build());
        }
    }
}

