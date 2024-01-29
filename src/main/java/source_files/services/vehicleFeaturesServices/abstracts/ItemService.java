package source_files.services.vehicleFeaturesServices.abstracts;

import source_files.data.DTO.BaseDTO;
import source_files.data.models.baseEntities.Item;
import source_files.data.requests.BaseRequest;
import source_files.exception.exceptionTypes.NotFoundExceptionType;

public interface ItemService<DTO extends BaseDTO, C extends Item> {
    void create(BaseRequest request, Class<C> entityClass);

    void delete(int id, NotFoundExceptionType exceptionType);

    DTO update(BaseRequest request, NotFoundExceptionType exceptionType, Class<C> entityClass);

    //public <D> D map(Object source, Class<D> destinationType)
    DTO getById(int id, NotFoundExceptionType exceptionType);
}
