package source_files.services.vehicleFeaturesServices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.BaseDTO;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.models.baseEntities.Item;
import source_files.data.requests.BaseRequest;
import source_files.exception.exceptionTypes.NotFoundExceptionType;
import source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts.ItemEntityService;
import source_files.services.vehicleFeaturesServices.abstracts.ItemService;

@Service
@RequiredArgsConstructor
public class ItemManager implements ItemService<BaseDTO, Item> {

    ItemEntityService<Item> itemEntityService;

    ModelMapperService mapper;

    @Override
    public void create(BaseRequest request, Class<Item> destinationType) {
        itemEntityService.create(mapper.forRequest().map(request, Item.class));
    }

    @Override
    public void delete(int id, NotFoundExceptionType exceptionType) {
        itemEntityService.delete(itemEntityService.getById(id, exceptionType));
    }

    @Override
    public BaseDTO update(BaseRequest request, NotFoundExceptionType exceptionType, Class<Item> entityClass) {
        return mapper.forResponse().map(
                itemEntityService.update(mapper.forResponse().map(request, entityClass))
                , BaseDTO.class
        );
    }

    @Override
    public BaseDTO getById(int id, NotFoundExceptionType exceptionType) {
        return mapper.forResponse().map(itemEntityService.getById(id, exceptionType), BaseDTO.class);
    }

}
