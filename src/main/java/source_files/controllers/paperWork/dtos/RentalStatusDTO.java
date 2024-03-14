package source_files.controllers.paperWork.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentalStatusDTO {
    int id;
    String name;
    boolean isDeleted;
}
