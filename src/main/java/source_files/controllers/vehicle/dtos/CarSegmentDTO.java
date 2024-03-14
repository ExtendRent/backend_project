package source_files.controllers.vehicle.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarSegmentDTO {
    int id;
    String name;
    boolean isDeleted;
}
