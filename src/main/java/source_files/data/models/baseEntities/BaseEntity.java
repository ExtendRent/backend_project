package source_files.data.models.baseEntities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
@MappedSuperclass //Alt klasların database tablosuna buradaki kolonları eklemek için kullanılır.
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
    @Column(name = "last_modified")
    private LocalDateTime lastModified;

    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;


    @PrePersist
    protected void beforeCreate() {
        createdDate = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        isDeleted = false;
    }

    @PreUpdate
    protected void beforeUpdate() {
        lastModified = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        isDeleted = false;
    }

}
