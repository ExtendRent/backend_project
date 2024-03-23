package src.controller.vehicle.features.common.brand.response;

import lombok.*;

@ToString
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
}
