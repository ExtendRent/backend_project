package source_files.controllers.vehicle.dtos;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarBodyTypeDTO {
    int id;
    String name;
    boolean isDeleted;
}
