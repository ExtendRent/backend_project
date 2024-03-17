package src.controller.vehicle.features.common.fuel.responses;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FuelTypeResponse {
    int id;
    String name;
    boolean isDeleted;
}
