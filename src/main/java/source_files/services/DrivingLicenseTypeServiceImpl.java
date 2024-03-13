package source_files.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.DrivingLicenseTypeDTO;
import source_files.data.models.DrivingLicenseTypeEntity;
import source_files.data.requests.CreateDrivingLicenseTypeRequest;
import source_files.data.requests.UpdateDrivingLicenseTypeRequest;
import source_files.services.BusinessRules.DrivingLicenseTypeRules;
import source_files.services.entityServices.abstracts.DrivingLicenseTypeEntityService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DrivingLicenseTypeServiceImpl implements DrivingLicenseTypeService {
    private final DrivingLicenseTypeEntityService entityService;
    private final DrivingLicenseTypeRules rules;

    @Override
    public void create(CreateDrivingLicenseTypeRequest createDrivingLicenseTypeRequest) {
        createDrivingLicenseTypeRequest = rules.fix(createDrivingLicenseTypeRequest);
        rules.check(createDrivingLicenseTypeRequest);
        entityService.create(createDrivingLicenseTypeRequest);
    }

    @Override
    public DrivingLicenseTypeDTO update(UpdateDrivingLicenseTypeRequest updateDrivingLicenseTypeRequest) {
        updateDrivingLicenseTypeRequest = rules.fix(updateDrivingLicenseTypeRequest);
        rules.check(updateDrivingLicenseTypeRequest);
        return entityService.update(updateDrivingLicenseTypeRequest).toModel();
    }

    @Override
    public DrivingLicenseTypeDTO getById(int id) {
        return entityService.getById(id).toModel();
    }

    @Override
    public List<DrivingLicenseTypeDTO> getAll() {
        return mapToDTOList(entityService.getAll());
    }

    @Override
    public List<DrivingLicenseTypeDTO> getAllByDeletedState(boolean isDeleted) {
        return mapToDTOList(entityService.getAllByDeletedState(isDeleted));
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

    private List<DrivingLicenseTypeDTO> mapToDTOList(List<DrivingLicenseTypeEntity> entities) {
        rules.checkDataList(entities);
        return entities.stream().map(DrivingLicenseTypeEntity::toModel).toList();
    }
}
