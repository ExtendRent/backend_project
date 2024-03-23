package src.controller.vehicle.features.car.model.response;

import lombok.*;

@ToString
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


}
