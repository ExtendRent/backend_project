package src.controller.vehicle.features.common.color.response;

import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ColorResponse {
    int id;
    String name;
    boolean isDeleted;

    @Override
    public String toString() {
        return "ColorResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
