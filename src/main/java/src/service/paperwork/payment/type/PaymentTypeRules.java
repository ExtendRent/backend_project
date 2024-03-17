package src.service.paperwork.payment.type;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import src.controller.paperwork.payment.requests.CreatePaymentTypeRequest;
import src.controller.paperwork.payment.requests.UpdatePaymentTypeRequest;
import src.core.exception.AlreadyExistsException;
import src.core.exception.DataNotFoundException;
import src.repository.paperwork.payment.type.PaymentTypeRepository;
import src.service.businessrules.abstracts.BaseItemRules;

import java.util.List;

import static src.core.exception.type.AlreadyExistsExceptionType.PAYMENT_TYPE_ALREADY_EXISTS;
import static src.core.exception.type.NotFoundExceptionType.PAYMENT_TYPE_LIST_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class PaymentTypeRules implements BaseItemRules {

    private final PaymentTypeRepository repository;

    public CreatePaymentTypeRequest fix(CreatePaymentTypeRequest createPaymentTypeRequest) {
        createPaymentTypeRequest.setPaymentTypeEntityName(fixName(createPaymentTypeRequest.getPaymentTypeEntityName()));
        return createPaymentTypeRequest;
    }

    public UpdatePaymentTypeRequest fix(UpdatePaymentTypeRequest updatePaymentTypeRequest) {
        updatePaymentTypeRequest.setName(fixName(updatePaymentTypeRequest.getName()));
        return updatePaymentTypeRequest;
    }

    public void check(CreatePaymentTypeRequest createPaymentTypeRequest) {
        existsByName(createPaymentTypeRequest.getPaymentTypeEntityName());
    }

    public void check(UpdatePaymentTypeRequest updatePaymentTypeRequest) {
        existsByNameAndIdNot(updatePaymentTypeRequest.getName(), updatePaymentTypeRequest.getId());
    }

    @Override
    public void checkDataList(List<?> list) {
        if (list.isEmpty()) {
            throw new DataNotFoundException(PAYMENT_TYPE_LIST_NOT_FOUND);
        }
    }

    @Override
    public String fixName(String name) {
        return name;
    }

    @Override
    public void existsByName(String name) {
        if (repository.existsByNameIgnoreCase(name)) {
            throw new AlreadyExistsException(PAYMENT_TYPE_ALREADY_EXISTS);
        }
    }

    @Override
    public void existsByNameAndIdNot(String name, int id) {
        if (repository.existsByNameIgnoreCaseAndIdNot(name, id)) {
            throw new AlreadyExistsException(PAYMENT_TYPE_ALREADY_EXISTS);
        }
    }
}
