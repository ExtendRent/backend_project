package source_files.data.DTO.paperWorkDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentTypeDTO {
    int id;
    String name;
    boolean isActive;
}
