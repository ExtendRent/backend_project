package src.controller.vehicle.features.common.status.response;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleStatusResponse {
    int id;
    String name;
    boolean isDeleted;

    @Override
    public String toString() {
        return "VehicleStatusResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
