package source_files.controllers.paperWork.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentTypeDTO {
    int id;
    String name;
    boolean isActive;
}
