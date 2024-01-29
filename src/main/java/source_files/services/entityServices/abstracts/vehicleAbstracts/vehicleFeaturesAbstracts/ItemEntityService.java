package source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts;

import source_files.data.models.baseEntities.Item;
import source_files.exception.exceptionTypes.NotFoundExceptionType;

public interface ItemEntityService<C extends Item> {

    C create(C item);

    void delete(C item);

    C update(C item);

    C getById(int id, NotFoundExceptionType exceptionType);
}
