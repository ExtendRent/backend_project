package source_files.services.vehicleFeaturesServices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.itemDTOs.ColorDTO;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.ColorEntity;
import source_files.data.requests.itemRequests.VehicleFeaturesRequests.ColorRequests.AddColorRequest;
import source_files.data.requests.itemRequests.VehicleFeaturesRequests.ColorRequests.UpdateColorRequest;
import source_files.services.entityServices.vehicleFeaturesEntityManagers.ColorEntityManager;
import source_files.services.vehicleFeaturesServices.abstracts.ColorService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ColorManager implements ColorService {

    private ColorEntityManager colorEntityManager;
    private ModelMapperService modelMapperService;

    @Override
    public ColorDTO add(AddColorRequest addColorRequest) {
        ColorEntity color = modelMapperService.forRequest().map(addColorRequest,ColorEntity.class);
        ColorDTO colorDTO = modelMapperService.forResponse().map(colorEntityManager.add(color),ColorDTO.class);
        return colorDTO;
    }

    @Override
    public ColorDTO update(UpdateColorRequest updateColorRequest) {
        ColorEntity color = modelMapperService.forRequest().map(updateColorRequest,ColorEntity.class);
        ColorDTO colorDTO = modelMapperService.forResponse().map(colorEntityManager.update(color), ColorDTO.class);
        return colorDTO;
    }

    @Override
    public ColorDTO getById(int id) {

        ColorDTO colorDTO = modelMapperService.forResponse().map(colorEntityManager.getById(id),ColorDTO.class);
        return colorDTO;
    }

    @Override
    public void delete(int id) {

        colorEntityManager.delete(colorEntityManager.getById(id));
    }

    @Override
    public List<ColorDTO> getAll() {
        List<ColorEntity> colorList = colorEntityManager.getAll();
        List<ColorDTO> colorDTOSList =  colorList.stream().map(color -> modelMapperService.forResponse().map(color,ColorDTO.class)).collect(Collectors.toList());

        return colorDTOSList;
    }
}
