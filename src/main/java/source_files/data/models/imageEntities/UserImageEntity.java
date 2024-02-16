package source_files.data.models.imageEntities;

import jakarta.persistence.*;
import lombok.*;
import source_files.data.models.baseEntities.Item;
import source_files.data.models.baseEntities.UserEntity;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user_images")
public class UserImageEntity extends Item {
    @JoinColumn(name = "user_id", unique = true)
    @OneToOne
    private UserEntity userEntity;
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
