package source_files.services.paperWorkServices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.paperWorkDTOs.DiscountCodeDTO;
import source_files.data.models.paperWorkEntities.paymentEntities.DiscountCodeEntity;
import source_files.data.requests.paperworkRequests.discountRequests.AddDiscountCodeRequest;
import source_files.data.requests.paperworkRequests.discountRequests.UpdateDiscountCodeRequest;
import source_files.services.entityServices.abstracts.paperWorkAbstracts.DiscountCodeEntityService;
import source_files.services.paperWorkServices.abstracts.DiscountCodeService;

import java.util.List;

@Service
@AllArgsConstructor
public class DiscountCodeManager implements DiscountCodeService {
    private final DiscountCodeEntityService discountCodeEntityService;
    private final ModelMapperService modelMapperService;

    @Override
    public DiscountCodeDTO add(AddDiscountCodeRequest addDiscountCodeRequest) {
        return this.modelMapperService.forResponse()
                .map(this.discountCodeEntityService.add(
                        this.modelMapperService.forRequest()
                                .map(addDiscountCodeRequest, DiscountCodeEntity.class)), DiscountCodeDTO.class);
    }

    @Override
    public DiscountCodeDTO update(UpdateDiscountCodeRequest updateDiscountCodeRequest) {
        return this.modelMapperService.forResponse()
                .map(this.discountCodeEntityService.update(
                        this.modelMapperService.forRequest()
                                .map(updateDiscountCodeRequest, DiscountCodeEntity.class)), DiscountCodeDTO.class);
    }

    @Override
    public DiscountCodeDTO getById(int id) {
        return this.modelMapperService.forResponse()
                .map(this.discountCodeEntityService.getById(id), DiscountCodeDTO.class);
    }

    @Override
    public DiscountCodeDTO getByDiscountCode(String discountCode) {
        return this.modelMapperService.forResponse()
                .map(this.discountCodeEntityService.getByDiscountCode(discountCode), DiscountCodeDTO.class);
    }

    @Override
    public void delete(int id, boolean hardDelete) {
        if (hardDelete) {
            this.discountCodeEntityService.delete(this.discountCodeEntityService.getById(id));
        } else {
            this.softDelete(id);
        }
    }

    @Override
    public void softDelete(int id) {
        DiscountCodeEntity discountCode = this.discountCodeEntityService.getById(id);
        discountCode.setIsDeleted(true);
        this.discountCodeEntityService.update(discountCode);
    }

    @Override
    public List<DiscountCodeDTO> getAll() {
        return this.discountCodeEntityService.getAll().stream()
                .map(discountCode -> this.modelMapperService.forResponse()
                        .map(discountCode, DiscountCodeDTO.class)).toList();
    }

    @Override
    public List<DiscountCodeDTO> getAllByDeletedState(boolean isDeleted) {
        return this.discountCodeEntityService.getAllByDeletedState(isDeleted)
                .stream().map(discountCode -> this.modelMapperService.forResponse()
                        .map(discountCode, DiscountCodeDTO.class)).toList();
    }

}
