package src.controller.vehicle.features.car.model.responses;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarModelResponse {
    int id;
    int brandEntityId;
    String name;
    String brandEntityName;
    boolean isDeleted;

    @Override
    public String toString() {
        return "CarModelResponse{" +
                "id=" + id +
                ", brandEntityId=" + brandEntityId +
                ", name='" + name + '\'' +
                ", brandEntityName='" + brandEntityName + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
