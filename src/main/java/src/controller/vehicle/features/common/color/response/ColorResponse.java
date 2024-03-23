package src.controller.vehicle.features.common.color.response;

import lombok.*;

@ToString
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
