package src.controller.vehicle.features.car.body.response;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarBodyTypeResponse {
    int id;
    String name;
    boolean isDeleted;

    @Override
    public String toString() {
        return "CarBodyTypeResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
