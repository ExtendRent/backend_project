package source_files.services.BusinessRules.paperWork;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.requests.paperworkRequests.paymentRequests.CreatePaymentTypeRequest;
import source_files.data.requests.paperworkRequests.paymentRequests.UpdatePaymentTypeRequest;
import source_files.dataAccess.paperWorkRepositories.PaymentTypeEntityRepository;
import source_files.exception.AlreadyExistsException;
import source_files.exception.DataNotFoundException;
import source_files.services.BusinessRules.abstractsBusinessRules.BaseItemBusinessRulesService;

import java.util.List;

import static source_files.exception.exceptionTypes.AlreadyExistsExceptionType.PAYMENT_TYPE_ALREADY_EXISTS;
import static source_files.exception.exceptionTypes.NotFoundExceptionType.PAYMENT_TYPE_LIST_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class PaymentTypeBusinessRules implements BaseItemBusinessRulesService {

    private final PaymentTypeEntityRepository repository;

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
