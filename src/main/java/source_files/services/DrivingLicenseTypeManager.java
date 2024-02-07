package source_files.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.DrivingLicenseTypeDTO;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.models.DrivingLicenseTypeEntity;
import source_files.data.requests.CreateDrivingLicenseTypeRequest;
import source_files.data.requests.UpdateDrivingLicenseTypeRequest;
import source_files.services.BusinessRules.DrivingLicenseTypeBusinessRules;
import source_files.services.entityServices.abstracts.DrivingLicenseTypeEntityService;

import java.util.List;

import static source_files.data.types.itemTypes.ItemType.DRIVING_LICENSE_TYPE;

@Service
@RequiredArgsConstructor
public class DrivingLicenseTypeManager implements DrivingLicenseTypeService {
    private final DrivingLicenseTypeEntityService entityService;

    private final DrivingLicenseTypeBusinessRules rules;
    private final ModelMapperService mapper;

    @Override
    public void create(CreateDrivingLicenseTypeRequest createDrivingLicenseTypeRequest) {
        DrivingLicenseTypeEntity drivingLicenseTypeEntity = mapper.forRequest().map(createDrivingLicenseTypeRequest, DrivingLicenseTypeEntity.class);
        drivingLicenseTypeEntity.setItemType(DRIVING_LICENSE_TYPE);
        entityService.create(drivingLicenseTypeEntity);
    }

    @Override
    public DrivingLicenseTypeDTO update(UpdateDrivingLicenseTypeRequest updateDrivingLicenseTypeRequest) {
        return mapper.forResponse().map(entityService.update(
                        mapper.forRequest().map(updateDrivingLicenseTypeRequest, DrivingLicenseTypeEntity.class))
                , DrivingLicenseTypeDTO.class);
    }

    @Override
    public DrivingLicenseTypeDTO getById(int id) {
        return mapper.forResponse().map(entityService.getById(id), DrivingLicenseTypeDTO.class);
    }

    @Override
    public List<DrivingLicenseTypeDTO> getAll() {
        return rules.checkDataList(entityService.getAll()).stream().map(
                entity -> mapper.forResponse().map(entity, DrivingLicenseTypeDTO.class)).toList();
    }

    @Override
    public List<DrivingLicenseTypeDTO> getAllByDeletedState(boolean isDeleted) {
        return rules.checkDataList(entityService.getAll()).stream().map(entity ->
                mapper.forResponse().map(entity, DrivingLicenseTypeDTO.class)).toList();
    }

    @Override
    public void delete(int id, boolean hardDelete) {
        if (hardDelete) {
            entityService.delete(entityService.getById(id));
        } else {
            softDelete(id);
        }
    }

    @Override
    public void softDelete(int id) {
        DrivingLicenseTypeEntity entity = entityService.getById(id);
        entity.setIsDeleted(true);
        entityService.update(entity);
    }
}
