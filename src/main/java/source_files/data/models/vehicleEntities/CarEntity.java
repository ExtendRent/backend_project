package source_files.data.models.vehicleEntities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import source_files.data.DTO.vehicleDTOs.CarDTO;
import source_files.data.models.baseEntities.Vehicle;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.CarBodyTypeEntity;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.CarModelEntity;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.ImagesEntity;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cars")
public class CarEntity extends Vehicle {
    @ManyToOne
    @JoinColumn(name = "model_id")
    private CarModelEntity carModelEntity;

    @ManyToOne
    @JoinColumn(name = "body_type_id")
    private CarBodyTypeEntity carBodyTypeEntity;

    @Column(name = "license_plate", unique = true)
    private String licensePlate;

    @Column(name = "kilometer")
    private int kilometer;

    @OneToOne(mappedBy = "carEntity"
            , cascade = {CascadeType.REMOVE, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private ImagesEntity imagesEntity;

    //todo : shot tipinde minFindexRate diye bir alan ekledi hoca araba kiralamalarda kullanılıyormuş

    public CarDTO convertToDTO() {
        return CarDTO.builder()
                .id(getId())
                .carModelEntityBrandEntityName(this.carModelEntity.getBrandEntity().getName())
                .carBodyTypeEntityName(this.carBodyTypeEntity.getName())
                .kilometer(this.kilometer)
                .seat(this.getSeat())
                .luggage(this.getLuggage())
                .year(this.getYear())
                .details(this.getDetails())
                .rentalPrice(this.getRentalPrice())
                .imagesEntityImagePaths(this.imagesEntity.getImagePaths())
                .expectedDrivingLicenseTypes(this.getExpectedDrivingLicenseTypes())
                .build();
    }

}