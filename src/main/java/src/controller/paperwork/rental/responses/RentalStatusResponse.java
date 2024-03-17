package src.controller.paperwork.rental.responses;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RentalStatusResponse {
    int id;
    String name;
    boolean isDeleted;
}
