package src.controller.vehicle.features.common.status.responses;

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
}
