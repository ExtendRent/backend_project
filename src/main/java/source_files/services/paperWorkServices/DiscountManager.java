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

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscountManager implements DiscountService {
    private final DiscountEntityService discountEntityService;
    private final ModelMapperService modelMapperService;

    private final DiscountRules discountRules;

    @Override
    public void create(CreateDiscountRequest createDiscountRequest) {
        this.discountEntityService.create(
                this.modelMapperService.forRequest()
                        .map(discountRules.fixDiscountRequest(createDiscountRequest), DiscountEntity.class));
    }

    @Override
    public DiscountDTO update(UpdateDiscountRequest updateDiscountRequest) {
        return this.modelMapperService.forResponse()
                .map(this.discountEntityService.update(
                        this.modelMapperService.forRequest()
                                .map(updateDiscountRequest, DiscountEntity.class)), DiscountDTO.class);
    }

    @Override
    public DiscountDTO getById(int id) {
        return this.modelMapperService.forResponse()
                .map(this.discountEntityService.getById(id), DiscountDTO.class);
    }

    @Override
    public DiscountDTO getByDiscountCode(String discountCode) {
        return this.modelMapperService.forResponse()
                .map(this.discountEntityService.getByDiscountCode(discountCode), DiscountDTO.class);
    }

    @Override
    public void delete(int id, boolean hardDelete) {
        if (hardDelete) {
            this.discountEntityService.delete(this.discountEntityService.getById(id));
        } else {
            this.softDelete(id);
        }
    }

    @Override
    public void softDelete(int id) {
        DiscountEntity discountCode = this.discountEntityService.getById(id);
        discountCode.setIsDeleted(true);
        this.discountEntityService.update(discountCode);
    }

    @Override
    public List<DiscountDTO> getAll() {
        return discountRules.checkDataList(this.discountEntityService.getAll()).stream()
                .map(discountCode -> this.modelMapperService.forResponse()
                        .map(discountCode, DiscountDTO.class)).toList();
    }

    @Override
    public List<DiscountDTO> getAllByDeletedState(boolean isDeleted) {
        return this.discountEntityService.getAllByDeletedState(isDeleted)
                .stream().map(discountCode -> this.modelMapperService.forResponse()
                        .map(discountCode, DiscountDTO.class)).toList();
    }

}
