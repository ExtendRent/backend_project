package src.controllers.vehicle.responses;

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
