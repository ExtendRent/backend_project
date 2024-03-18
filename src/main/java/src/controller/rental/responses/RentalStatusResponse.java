package src.controller.rental.responses;

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

    @Override
    public String toString() {
        return "RentalStatusResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
