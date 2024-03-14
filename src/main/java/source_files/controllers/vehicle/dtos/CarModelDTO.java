package source_files.controllers.vehicle.dtos;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarModelDTO {
    int id;
    int brandEntityId;
    String name;
    String brandEntityName;
    boolean isDeleted;
}
