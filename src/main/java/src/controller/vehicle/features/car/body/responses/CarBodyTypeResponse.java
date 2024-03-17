package src.controller.vehicle.features.car.body.responses;

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
}
