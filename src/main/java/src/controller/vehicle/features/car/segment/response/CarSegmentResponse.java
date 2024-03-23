package src.controller.vehicle.features.car.segment.response;

import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarSegmentResponse {
    int id;
    String name;
    boolean isDeleted;


}
