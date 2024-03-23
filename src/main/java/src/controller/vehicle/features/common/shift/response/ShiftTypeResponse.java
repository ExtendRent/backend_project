package src.controller.vehicle.features.common.shift.response;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ShiftTypeResponse {
    int id;
    String name;
    boolean isDeleted;

}
