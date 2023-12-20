package source_files.services.vehicleFeaturesServices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.itemDTOs.ColorDTO;
import source_files.data.DTO.vehicleDTOs.CarDTO;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.ColorEntity;
import source_files.data.requests.itemRequests.VehicleFeaturesRequests.ColorRequests.AddColorRequest;
import source_files.data.requests.itemRequests.VehicleFeaturesRequests.ColorRequests.UpdateColorRequest;
import source_files.services.BusinessRules.ColorBusinessRules;
import source_files.services.entityServices.abstracts.vehicleFeaturesAbstracts.ColorEntityService;
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
        colorBusinessRules.existsByName(addColorRequest.getName());
        ColorEntity color = modelMapperService.forRequest().map(addColorRequest, ColorEntity.class);
        return modelMapperService.forResponse().map(colorEntityService.add(color), ColorDTO.class);
    }

    @Override
    public ColorDTO update(UpdateColorRequest updateColorRequest) {
        ColorEntity color = modelMapperService.forRequest().map(updateColorRequest, ColorEntity.class);
        return modelMapperService.forResponse().map(colorEntityService.update(color), ColorDTO.class);
    }

    @Override
    public ColorDTO getById(int id) {

        return modelMapperService.forResponse().map(colorEntityService.getById(id), ColorDTO.class);
    }

    @Override
    public List<ColorDTO> getAll() {
        List<ColorEntity> colorList = colorEntityService.getAll();

        return colorList.stream().map(color -> modelMapperService.forResponse().map(color, ColorDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<ColorDTO> getAllByIsDeletedFalse() {
        return this.colorEntityService.getAllByIsDeletedFalse()
                .stream().map(colorEntity ->  modelMapperService.forResponse().map(colorEntity, ColorDTO.class)).toList();
    }

    @Override
    public List<ColorDTO> getAllByIsDeletedTrue() {
        return this.colorEntityService.getAllByIsDeletedTrue()
                .stream().map(colorEntity ->  modelMapperService.forResponse().map(colorEntity, ColorDTO.class)).toList();
    }

    @Override
    public void delete(int id, boolean hardDelete) {
        if (hardDelete) {
            this.hardDelete(id);
        } else {
            this.softDelete(id);
        }
    }

    @Override
    public void hardDelete(int id) {

    }

    @Override
    public void softDelete(int id) {
        ColorEntity colorEntity = this.colorEntityService.getById(id);
        colorEntity.setIsDeleted(true);
        this.colorEntityService.update(colorEntity);
    }
}
