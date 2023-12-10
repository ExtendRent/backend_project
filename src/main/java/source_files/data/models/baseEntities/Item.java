package source_files.data.models.baseEntities;

import jakarta.persistence.Column;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass //Alt klasların database tablosuna buradaki kolonları eklemek için kullanılır.
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED) // kendini extend eden her klasa kendi değişkenlerini eklemesini sağlar.
public class Item extends BaseEntity {


    @Builder.Default
    @Column(name = "is_deleted")
    private boolean isDeleted = false;

    @LastModifiedDate
    @Column(name = "last_modified")
    private Date lastModified;

    @CreatedDate
    @Column(name = "created_date")
    private Date createdDate;

}
