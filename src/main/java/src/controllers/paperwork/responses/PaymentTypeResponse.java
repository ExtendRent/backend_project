package src.controllers.paperwork.responses;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentTypeResponse {
    int id;
    String name;
    boolean isActive;
}
