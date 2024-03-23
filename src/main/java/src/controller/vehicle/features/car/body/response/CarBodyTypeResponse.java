package src.controller.vehicle.features.car.body.response;

import lombok.*;

@ToString
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
