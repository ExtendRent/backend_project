package src.controller.vehicle.features.common.shift.response;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ShiftTypeResponse {
    int id;
    String name;
    boolean isDeleted;

    @Override
    public String toString() {
        return "ShiftTypeResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
