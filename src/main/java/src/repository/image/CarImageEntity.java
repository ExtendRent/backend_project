package src.repository.image;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.*;
import src.core.BaseEntity;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "carImageBuilder")
@Table(name = "car_images")
public class CarImageEntity extends BaseEntity {

    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;
    @Lob
    @Column(name = "image_data", length = 1000)
    private byte[] imageData;

    @Column(name = "image_Url", length = 1000)
    private String url;
}
