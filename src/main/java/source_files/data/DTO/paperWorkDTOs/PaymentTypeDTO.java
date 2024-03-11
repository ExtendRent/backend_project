package source_files.data.DTO.paperWorkDTOs;

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
