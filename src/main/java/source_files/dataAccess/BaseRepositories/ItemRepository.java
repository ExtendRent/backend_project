package source_files.dataAccess.BaseRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import source_files.data.models.baseEntities.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

}
