package source_files.services.entityServices.vehicleEntityManagers.vehicleFeaturesEntityManagers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.models.baseEntities.Item;
import source_files.dataAccess.BaseRepositories.ItemRepository;
import source_files.exception.DataNotFoundException;
import source_files.exception.exceptionTypes.NotFoundExceptionType;
import source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts.ItemEntityService;

@Service
@RequiredArgsConstructor
public class ItemEntityManager implements ItemEntityService<Item> {
    private final ItemRepository itemRepository;


    @Override
    public Item create(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public void delete(Item item) {
        itemRepository.delete(item);
    }

    @Override
    public Item update(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public Item getById(int id, NotFoundExceptionType exceptionType) {
        return itemRepository.findById(id).orElseThrow(() -> new DataNotFoundException(exceptionType));
    }
}
