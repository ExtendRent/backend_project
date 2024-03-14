package source_files.controllers.vehicle.dtos;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class VehicleStatusDTO {
    int id;
    String name;
    boolean isDeleted;
}
