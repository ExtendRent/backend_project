package src.controllers.vehicle.responses;

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
}
