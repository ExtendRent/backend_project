package src.controller.rental.response;

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
