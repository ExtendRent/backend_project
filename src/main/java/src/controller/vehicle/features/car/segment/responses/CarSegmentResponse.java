package src.controller.vehicle.features.car.segment.responses;

import lombok.*;

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
