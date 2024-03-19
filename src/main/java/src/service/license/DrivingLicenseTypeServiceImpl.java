package src.service.license;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import src.controller.license.request.CreateDrivingLicenseTypeRequest;
import src.controller.license.request.UpdateDrivingLicenseTypeRequest;
import src.controller.license.response.DrivingLicenseTypeResponse;
import src.repository.license.DrivingLicenseTypeEntity;
import src.repository.license.DrivingLicenseTypeEntityService;

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
    public DrivingLicenseTypeResponse update(UpdateDrivingLicenseTypeRequest updateDrivingLicenseTypeRequest) {
        updateDrivingLicenseTypeRequest = rules.fix(updateDrivingLicenseTypeRequest);
        rules.check(updateDrivingLicenseTypeRequest);
        return entityService.update(updateDrivingLicenseTypeRequest).toModel();
    }

    @Override
    public DrivingLicenseTypeResponse getById(int id) {
        return entityService.getById(id).toModel();
    }

    @Override
    public List<DrivingLicenseTypeResponse> getAll() {
        return mapToDTOList(entityService.getAll());
    }

    @Transactional
    @Override
    public List<DrivingLicenseTypeResponse> getAllByDeletedState(boolean isDeleted) {
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

    private List<DrivingLicenseTypeResponse> mapToDTOList(List<DrivingLicenseTypeEntity> entities) {
        rules.checkDataList(entities);
        return entities.stream().map(DrivingLicenseTypeEntity::toModel).toList();
    }
}
