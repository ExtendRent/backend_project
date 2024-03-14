package source_files.core.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.tongfei.progressbar.ProgressBar;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import source_files.controllers.item.requests.CreateDrivingLicenseTypeRequest;
import source_files.controllers.paperWork.requests.RentalRequests.CreateRentalRequest;
import source_files.controllers.paperWork.requests.RentalRequests.ReturnRentalRequest;
import source_files.controllers.paperWork.requests.discountRequests.CreateDiscountRequest;
import source_files.controllers.paperWork.requests.paymentRequests.CreatePaymentTypeRequest;
import source_files.controllers.user.requests.CreateAdminRequest;
import source_files.controllers.user.requests.CreateCustomerRequest;
import source_files.controllers.user.requests.CreateEmployeeRequest;
import source_files.controllers.vehicle.dtos.BrandDTO;
import source_files.controllers.vehicle.requests.CarRequests.CreateCarRequest;
import source_files.controllers.vehicle.requests.VehicleFeaturesRequests.BrandRequests.CreateBrandRequest;
import source_files.controllers.vehicle.requests.VehicleFeaturesRequests.CarBodyTypeRequests.CreateCarBodyTypeRequest;
import source_files.controllers.vehicle.requests.VehicleFeaturesRequests.CarModelRequests.CreateCarModelRequest;
import source_files.controllers.vehicle.requests.VehicleFeaturesRequests.CarSegmentRequests.CreateCarSegmentRequest;
import source_files.controllers.vehicle.requests.VehicleFeaturesRequests.ColorRequests.CreateColorRequest;
import source_files.controllers.vehicle.requests.VehicleFeaturesRequests.FuelTypeRequests.CreateFuelTypeRequest;
import source_files.controllers.vehicle.requests.VehicleFeaturesRequests.ShiftTypeRequests.CreateShiftTypeRequest;
import source_files.controllers.vehicle.requests.VehicleFeaturesRequests.VehicleStatusRequests.CreateVehicleStatusRequest;
import source_files.data.enums.defaultDataEnums.*;
import source_files.data.enums.defaultDataEnums.Status.DefaultRentalStatus;
import source_files.data.enums.defaultDataEnums.Status.DefaultVehicleStatus;
import source_files.data.models.paperWorkEntities.paymentEntities.CreditCardInformation;
import source_files.data.models.paperWorkEntities.rentalEntities.RentalStatusEntity;
import source_files.exception.DataNotFoundException;
import source_files.services.DrivingLicenseTypeService;
import source_files.services.Image.BrandImageService;
import source_files.services.Image.CarImageService;
import source_files.services.Image.UserImageService;
import source_files.services.entityServices.abstracts.userAbstract.UserEntityService;
import source_files.services.paperWork.abstracts.DiscountService;
import source_files.services.paperWork.abstracts.PaymentTypeService;
import source_files.services.paperWork.abstracts.RentalService;
import source_files.services.paperWork.abstracts.RentalStatusService;
import source_files.services.user.abstracts.AdminService;
import source_files.services.user.abstracts.CustomerService;
import source_files.services.user.abstracts.EmployeeService;
import source_files.services.vehicle.abstracts.CarService;
import source_files.services.vehicleFeatures.abstracts.*;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static source_files.Application.*;

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
    private boolean isRunning;

    public void runFirst() throws IOException {
        isRunning = true;
        this.run();
    }

    @Override
    public void run(String... args) throws IOException {
        if (isRunning) {
            final ProgressBar progress = new ProgressBar("seed data", 100);
            try {
                brandService.getAll();
            } catch (DataNotFoundException e) {
                progress.setExtraMessage("Creating default brands...");
                for (DefaultBrand brandEnum : DefaultBrand.getAll()) {
                    URL url = new URL(brandEnum.getImageUrl());
                    Path path = Paths.get("src/main/resources/assets/default/brand", brandEnum.name() + ".jpg");
                    if (Files.exists(path)) {
                        Files.delete(path);
                    }
                    Files.copy(url.openStream(), path);
                    MultipartFile file = new MockMultipartFile(
                            brandEnum.name(), path.getFileName().toString(), "image/jpeg", Files.readAllBytes(path));
                    brandService.create(new CreateBrandRequest(brandEnum.getLabel(),
                            brandImageService.create(file, brandEnum.getLabel()).getId()));
                    progress.step();
                }
            }
            progress.stepTo(5);
            try {
                carModelService.getAll();
            } catch (DataNotFoundException e) {
                progress.setExtraMessage("Creating default car models..");
                List<BrandDTO> brands = brandService.getAll();
                String[] carModels = Arrays.stream(DefaultCarModel.getAll()).map(DefaultCarModel::getLabel).toArray(String[]::new);
                brands.forEach(brand -> carModelService.create(
                        new CreateCarModelRequest(brand.getId(), carModels[brands.indexOf(brand)]))
                );
            }

            try {
                carSegmentService.getAll();
            } catch (DataNotFoundException e) {
                progress.setExtraMessage("Creating default car segments...");
                for (DefaultCarSegment carSegment : DefaultCarSegment.getAll()) {
                    carSegmentService.create(new CreateCarSegmentRequest(carSegment.getLabel()));
                    progress.step();
                }
            }

            try {
                carBodyTypeService.getAll();
            } catch (DataNotFoundException e) {
                progress.setExtraMessage("Creating default car body types...");
                for (DefaultCarBodyType carBodyType : DefaultCarBodyType.getAll()) {
                    carBodyTypeService.create(new CreateCarBodyTypeRequest(carBodyType.getLabel()));
                    progress.step();
                }
            }

            try {
                colorService.getAll();
            } catch (DataNotFoundException e) {
                progress.setExtraMessage("Creating default colors...");
                for (DefaultColors defaultColor : DefaultColors.getAll()) {
                    colorService.create(new CreateColorRequest(defaultColor.getLabel()));
                    progress.step();
                }
            }

            try {
                shiftTypeService.getAll();
            } catch (DataNotFoundException e) {
                progress.setExtraMessage("Creating default gearshifts...");
                for (DefaultShiftType defaultShiftType : DefaultShiftType.getAll()) {
                    shiftTypeService.create(new CreateShiftTypeRequest(defaultShiftType.getLabel()));
                    progress.step();
                }
            }

            try {
                fuelTypeService.getAll();
            } catch (DataNotFoundException e) {
                progress.setExtraMessage("Creating default fuel types...");
                for (DefaultFuelType defaultFuelType : DefaultFuelType.getAll()) {
                    fuelTypeService.create(new CreateFuelTypeRequest(defaultFuelType.getLabel()));
                    progress.step();
                }
            }

            try {
                vehicleStatusService.getAll();
            } catch (DataNotFoundException e) {
                progress.setExtraMessage("Creating default vehicle statuses...");
                for (DefaultVehicleStatus defaultVehicleStatus : DefaultVehicleStatus.getAll()) {
                    vehicleStatusService.create(new CreateVehicleStatusRequest(defaultVehicleStatus.getLabel(), defaultVehicleStatus));
                    progress.step();
                }
            }

            try {
                discountService.getAll();
            } catch (DataNotFoundException e) {
                progress.setExtraMessage("Creating default discounts...");
                for (DefaultDiscount defaultDiscount : DefaultDiscount.getAll()) {
                    discountService.create(new CreateDiscountRequest(defaultDiscount.name(), defaultDiscount.getPercentage()));
                    progress.step();
                }
            }

            try {
                drivingLicenseTypeService.getAll();
            } catch (DataNotFoundException e) {
                progress.setExtraMessage("Creating default discounts...");
                for (DefaultCarDrivingLicenseType defaultCarDrivingLicenseType : DefaultCarDrivingLicenseType.getAll()) {
                    drivingLicenseTypeService.create(new CreateDrivingLicenseTypeRequest(
                            defaultCarDrivingLicenseType.name(),
                            defaultCarDrivingLicenseType.getLabel(),
                            defaultCarDrivingLicenseType.ordinal())
                    );
                    progress.step();
                }
            }


            try {
                paymentTypeService.getAll();
            } catch (DataNotFoundException e) {
                progress.setExtraMessage("Creating default payment types...");
                for (DefaultPaymentType paymentType : DefaultPaymentType.getAll()) {
                    paymentTypeService.create(new CreatePaymentTypeRequest(paymentType.getLabel(), paymentType, true));
                    progress.step();
                }
            }

            try {
                rentalStatusService.getAll();
            } catch (DataNotFoundException e) {
                progress.setExtraMessage("Creating default rental statuses...");
                for (DefaultRentalStatus defaultRentalStatus : DefaultRentalStatus.getAll()) {
                    rentalStatusService.create(new RentalStatusEntity(defaultRentalStatus.getLabel(), defaultRentalStatus));
                    progress.step();
                }
            }

            try {
                carService.getAll();
            } catch (DataNotFoundException e) {
                progress.setExtraMessage("Creating default cars...");
                DefaultCarImageUrl[] defaultCarImageEnums = DefaultCarImageUrl.getAll();
                for (int i = 1; i <= 4; i++) {
                    int x = 2;
                    if (i == 3) {
                        x = 3;
                    }
                    DefaultCarImageUrl defaultCarImageEnum = defaultCarImageEnums[i - 1];

                    URL url = new URL(defaultCarImageEnum.getLabel());
                    Path path = Paths.get("src/main/resources/assets/default/car", defaultCarImageEnum.name() + ".jpg");
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
                            .carImageEntityId(carImageService.create(file, "46kk35" + +i).getId())
                            .expectedMinDrivingLicenseTypeId(x)
                            .carSegmentEntityId(i)
                            .build());
                    progress.stepBy(3);
                }
            }


            //-------------------------------SEED USERS-----------------------------------------
            Pageable pageable = PageRequest.of(0, 3);
            if (userEntityService.getAll(pageable).toList().isEmpty()) {
                progress.setExtraMessage("Creating default users...");
                for (DefaultUserImageUrl defaultUserImageEnum : DefaultUserImageUrl.getAll()) {
                    URL url = new URL(defaultUserImageEnum.getUrl());
                    Path path = Paths.get("src/main/resources/assets/default/user", defaultUserImageEnum.name() + ".jpg");
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
                                progress.step();
                            }
                        }
                    }
                    progress.step();
                }
            }

            //-------------------------------SEED RENTALS-----------------------------------------

            try {
                rentalService.getAll();
            } catch (DataNotFoundException e) {
                progress.setExtraMessage("Creating default rentals...");
                CreditCardInformation creditCardInformation = CreditCardInformation.builder()
                        .cardOwnerName("customer")
                        .cardOwnerSurname("customer")
                        .cardNumber("1111111111111111")
                        .cvc("111")
                        .expirationDate(LocalDate.parse("2024-09-01"))
                        .build();


                //----------------------end rentals------------------------------------------
                rentalService.create(CreateRentalRequest.builder()
                        .customerEntityId(3).carEntityId(1)
                        .amount(900.00)
                        .startDate(LocalDate.parse("2024-02-07"))
                        .endDate(LocalDate.parse("2024-02-12"))
                        .paymentTypeId(1)
                        .discountCode("HOSGELDIN")
                        .creditCardInformation(creditCardInformation)
                        .build());

                rentalService.create(CreateRentalRequest.builder()
                        .customerEntityId(5).carEntityId(2)
                        .amount(1500.00)
                        .startDate(LocalDate.parse("2024-02-10"))
                        .endDate(LocalDate.parse("2024-02-15"))
                        .paymentTypeId(1)
                        .discountCode("")
                        .creditCardInformation(creditCardInformation)
                        .build());

                rentalService.returnCar(ReturnRentalRequest.builder()
                        .id(1)
                        .returnDate(LocalDate.parse("2024-02-12"))
                        .endKilometer(12000)
                        .build());

                rentalService.returnCar(ReturnRentalRequest.builder()
                        .id(2)
                        .returnDate(LocalDate.parse("2024-02-16"))
                        .endKilometer(10000)
                        .build());
                //----------------------------------------------------------------
                rentalService.create(CreateRentalRequest.builder()
                        .customerEntityId(3).carEntityId(2)
                        .amount(1350.00)
                        .startDate(LocalDate.parse("2024-03-10"))
                        .endDate(LocalDate.parse("2024-03-15"))
                        .paymentTypeId(1)
                        .discountCode("HOSGELDIN")
                        .creditCardInformation(creditCardInformation)
                        .build());

                rentalService.create(CreateRentalRequest.builder()
                        .customerEntityId(4).carEntityId(1)
                        .amount(800.00)
                        .startDate(LocalDate.parse("2024-03-20"))
                        .endDate(LocalDate.parse("2024-03-25"))
                        .paymentTypeId(1)
                        .discountCode("PAIR5")
                        .creditCardInformation(creditCardInformation)
                        .build());
            }
            progress.stepTo(100);
            progress.setExtraMessage(ANSI_GREEN + ANSI_BOLD + "✔..." + ANSI_RESET);
            progress.close();
            isRunning = false;
        }
    }
}



