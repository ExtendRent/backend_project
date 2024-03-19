package src.controller.vehicle.features.common.brand.response;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BrandResponse {
    int id;
    int brandImageEntityId;
    String name;
    String brandImageEntityUrl;
    boolean isDeleted;

    @Override
    public String toString() {
        return "BrandResponse{" +
                "id=" + id +
                ", brandImageEntityId=" + brandImageEntityId +
                ", name='" + name + '\'' +
                ", brandImageEntityUrl='" + brandImageEntityUrl + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
