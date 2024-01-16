package source_files.services.paperWorkServices.payment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.paperWorkDTOs.PaymentDetailsDTO;
import source_files.data.models.paperWorkEntities.paymentEntities.PaymentDetailsEntity;
import source_files.data.requests.paperworkRequests.RentalRequests.AddRentalRequest;
import source_files.exception.PaymentException;
import source_files.services.BusinessRules.paperWork.PaymentBusinessRules;
import source_files.services.entityServices.abstracts.paperWorkAbstracts.PaymentTypeEntityService;
import source_files.services.paperWorkServices.abstracts.PaymentService;
import source_files.services.systemServices.SysPaymentDetailsService;

import static source_files.exception.exceptionTypes.PaymentExceptionType.PAYMENT_REJECTED;

@Service
@AllArgsConstructor
public class PaymentManager implements PaymentService {

    private final PayWithCreditCard payWithCreditCard;

    private final PaymentBusinessRules paymentBusinessRules;

    private final SysPaymentDetailsService sysPaymentDetailsService;

    private final PaymentTypeEntityService paymentTypeEntityService;

    private final ModelMapperService modelMapperService;

    @Override
    public AddRentalRequest payWithCreditCard(AddRentalRequest addRentalRequest) {

        if (this.payWithCreditCard.pay(
                addRentalRequest.getAmount()
                , this.paymentBusinessRules
                        .checkCreditCard(
                                this.paymentBusinessRules.fixCreditCardInformation(addRentalRequest.getCreditCardInformation())
                        )
        )) {
            addRentalRequest.setPaymentDetailsDTO(
                    this.modelMapperService.forResponse().map(
                            this.sysPaymentDetailsService.add(new PaymentDetailsEntity(addRentalRequest.getAmount()
                                    , this.paymentTypeEntityService.getById(addRentalRequest.getPaymentTypeId()))
                            ), PaymentDetailsDTO.class));
        }

        throw new PaymentException(PAYMENT_REJECTED, "Ödeme Banka Tarafından Reddedildi.");
    }


}
