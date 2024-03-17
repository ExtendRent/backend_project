package src.controller.vehicle.features.common.color.responses;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ColorResponse {
    int id;
    String name;
    boolean isDeleted;
}
