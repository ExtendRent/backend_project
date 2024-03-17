package src.controller.vehicle.features.common.shift.responses;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ShiftTypeResponse {
    int id;
    String name;
    boolean isDeleted;
}
