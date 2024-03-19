package src.controller.vehicle.features.car.segment.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarSegmentResponse {
    int id;
    String name;
    boolean isDeleted;

    @Override
    public String toString() {
        return "CarSegmentResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
