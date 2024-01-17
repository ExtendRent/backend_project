package source_files.services.vehicleFeaturesServices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.itemDTOs.ColorDTO;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.ColorEntity;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.ColorRequests.AddColorRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.ColorRequests.UpdateColorRequest;
import source_files.data.types.itemTypes.ItemType;
import source_files.services.BusinessRules.vehicleFeaturesBusinessRules.ColorBusinessRules;
import source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts.ColorEntityService;
import source_files.services.vehicleFeaturesServices.abstracts.ColorService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ColorManager implements ColorService {

    private final ColorEntityService colorEntityService;
    private final ModelMapperService modelMapperService;
    private final ColorBusinessRules colorBusinessRules;

    @Override
    public ColorDTO add(AddColorRequest addColorRequest) {

        ColorEntity colorEntity = modelMapperService.forRequest()
                .map(colorBusinessRules.checkAddColorRequest(
                        colorBusinessRules.fixAddColorRequest(addColorRequest)), ColorEntity.class
                );

        colorEntity.setItemType(ItemType.COLOR);

        return this.modelMapperService.forResponse().map(this.colorEntityService.add(colorEntity), ColorDTO.class);
    }

    @Override
    public ColorDTO update(UpdateColorRequest updateColorRequest) {
        ColorEntity color = modelMapperService.forRequest().map(colorBusinessRules.checkUpdateColorRequest(
                colorBusinessRules.fixUpdateColorRequest(updateColorRequest)), ColorEntity.class);
        color.setItemType(ItemType.COLOR);
        return modelMapperService.forResponse().map(colorEntityService.add(color), ColorDTO.class);
    }

    @Override
    public ColorDTO getById(int id) {

        return modelMapperService.forResponse().map(colorEntityService.getById(id), ColorDTO.class);
    }

    @Override
    public List<ColorDTO> getAll() throws Exception {

        //bilgi: önce gelen listenin boş olup olmadığını kontrol ediyoruz. boş değilse listeyi dönüyor.
        return colorBusinessRules.checkDataList(colorEntityService.getAll())
                .stream().map(color -> modelMapperService.forResponse()
                        .map(color, ColorDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<ColorDTO> getAllByDeletedState(boolean isDeleted) {

        return colorBusinessRules.checkDataList(colorEntityService.getAllByDeletedState(isDeleted)).stream().map(
                color -> modelMapperService.forResponse().map(color, ColorDTO.class)).toList();
    }

    @Override
    public void delete(int id, boolean hardDelete) {
        if (hardDelete) {
            this.colorEntityService.delete(this.colorEntityService.getById(id));
        } else {
            this.softDelete(id);
        }
    }

    @Override
    public void softDelete(int id) {
        ColorEntity colorEntity = this.colorEntityService.getById(id);
        colorEntity.setIsDeleted(true);
        this.colorEntityService.update(colorEntity);
    }


}
