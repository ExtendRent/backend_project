package src.controller.vehicle.features.common.fuel.response;

import lombok.*;

@ToString
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
