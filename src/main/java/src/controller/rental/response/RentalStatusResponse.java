package src.controller.rental.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RentalStatusResponse {
    int id;
    String name;
    boolean isDeleted;


}
