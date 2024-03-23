package src.controller.vehicle.features.common.status.response;

import lombok.*;

@ToString
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
