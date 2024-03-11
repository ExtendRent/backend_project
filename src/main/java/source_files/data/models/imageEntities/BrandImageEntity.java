package source_files.data.models.imageEntities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.*;
import source_files.data.models.baseEntities.BaseEntity;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "brandImageBuilder")
@Table(name = "brand_logo_images")
public class BrandImageEntity extends BaseEntity {

    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;
    @Lob
    @Column(name = "image_data", length = 1000)
    private byte[] imageData;

    @Column(name = "image_Url", length = 1000)
    private String imageUrl;
}