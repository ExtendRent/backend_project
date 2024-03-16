package src.controllers.vehicle.responses;

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
