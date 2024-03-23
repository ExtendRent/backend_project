package src.controller.payment.type.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PaymentTypeResponse {
    int id;
    String name;
    boolean isActive;

}
