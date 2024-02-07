package source_files.services.paperWorkServices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.paperWorkDTOs.DiscountDTO;
import source_files.data.models.paperWorkEntities.paymentEntities.DiscountEntity;
import source_files.data.requests.paperworkRequests.discountRequests.CreateDiscountRequest;
import source_files.data.requests.paperworkRequests.discountRequests.UpdateDiscountRequest;
import source_files.services.BusinessRules.paperWork.DiscountRules;
import source_files.services.entityServices.abstracts.paperWorkAbstracts.DiscountEntityService;
import source_files.services.paperWorkServices.abstracts.DiscountService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscountManager implements DiscountService {
    private final DiscountEntityService discountEntityService;
    private final ModelMapperService mapper;

    private final DiscountRules rules;

    @Override
    public void create(CreateDiscountRequest createDiscountRequest) {
        discountEntityService.create(
                mapper.forRequest()
                        .map(rules.fixDiscountRequest(createDiscountRequest), DiscountEntity.class));
    }

    @Override
    public DiscountDTO update(UpdateDiscountRequest updateDiscountRequest) {
        return mapper.forResponse()
                .map(discountEntityService.update(
                        mapper.forRequest()
                                .map(updateDiscountRequest, DiscountEntity.class)), DiscountDTO.class);
    }

    @Override
    public DiscountDTO getById(int id) {
        return mapper.forResponse()
                .map(discountEntityService.getById(id), DiscountDTO.class);
    }

    @Override
    public DiscountDTO getByDiscountCode(String discountCode) {
        return mapper.forResponse()
                .map(discountEntityService.getByDiscountCode(discountCode), DiscountDTO.class);
    }

    @Override
    public void delete(int id, boolean hardDelete) {
        if (hardDelete) {
            discountEntityService.delete(discountEntityService.getById(id));
        } else {
            softDelete(id);
        }
    }

    @Override
    public void softDelete(int id) {
        DiscountEntity discountCode = discountEntityService.getById(id);
        discountCode.setIsDeleted(true);
        discountCode.setDeletedAt(LocalDateTime.now());
        discountEntityService.update(discountCode);
    }

    @Override
    public List<DiscountDTO> getAll() {
        return rules.checkDataList(discountEntityService.getAll()).stream()
                .map(discountCode -> mapper.forResponse()
                        .map(discountCode, DiscountDTO.class)).toList();
    }

    @Override
    public List<DiscountDTO> getAllByDeletedState(boolean isDeleted) {
        return rules.checkDataList(discountEntityService.getAllByDeletedState(isDeleted))
                .stream().map(discountCode -> mapper.forResponse()
                        .map(discountCode, DiscountDTO.class)).toList();
    }

    @Override
    public List<DiscountDTO> getAllByActiveState(boolean isActive) {
        return rules.checkDataList(discountEntityService.getAllByActiveState(isActive))
                .stream().map(discountCode -> mapper.forResponse(
                ).map(discountCode, DiscountDTO.class)).toList();
    }

}
