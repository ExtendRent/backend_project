package source_files.controllers.vehicle.dtos;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ColorDTO {
    int id;
    String name;
    boolean isDeleted;
}
