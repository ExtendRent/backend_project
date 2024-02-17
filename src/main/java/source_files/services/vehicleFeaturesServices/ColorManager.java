package source_files.services.vehicleFeaturesServices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.itemDTOs.ColorDTO;
import source_files.data.models.vehicleEntities.vehicleFeatures.ColorEntity;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.ColorRequests.CreateColorRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.ColorRequests.UpdateColorRequest;
import source_files.services.BusinessRules.vehicleFeaturesBusinessRules.ColorBusinessRules;
import source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts.ColorEntityService;
import source_files.services.vehicleFeaturesServices.abstracts.ColorService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ColorManager implements ColorService {

    private final ColorEntityService colorEntityService;
    private final ModelMapperService modelMapperService;
    private final ColorBusinessRules colorBusinessRules;

    @Override
    public void create(CreateColorRequest createColorRequest) {

        ColorEntity colorEntity = modelMapperService.forRequest()
                .map(colorBusinessRules.checkCreateColorRequest(
                        colorBusinessRules.fixCreateColorRequest(createColorRequest)), ColorEntity.class
                );
        this.colorEntityService.create(colorEntity);

    }

    @Override
    public ColorDTO update(UpdateColorRequest updateColorRequest) {
        ColorEntity color = modelMapperService.forRequest().map(colorBusinessRules.checkUpdateColorRequest(
                colorBusinessRules.fixUpdateColorRequest(updateColorRequest)), ColorEntity.class);
        return modelMapperService.forResponse().map(colorEntityService.create(color), ColorDTO.class);
    }

    @Override
    public ColorDTO getById(int id) {

        return modelMapperService.forResponse().map(colorEntityService.getById(id), ColorDTO.class);
    }

    @Override
    public List<ColorDTO> getAll() {

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
        colorEntity.setDeletedAt(LocalDateTime.now());
        this.colorEntityService.update(colorEntity);
    }


}
