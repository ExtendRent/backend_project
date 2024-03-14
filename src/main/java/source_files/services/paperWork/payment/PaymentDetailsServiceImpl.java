package source_files.services.paperWork.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.controllers.paperWork.dtos.PaymentDetailsDTO;
import source_files.controllers.paperWork.requests.paymentRequests.UpdatePaymentDetailsRequest;
import source_files.data.models.paperWorkEntities.paymentEntities.PaymentDetailsEntity;
import source_files.services.BusinessRules.paperWork.PaymentDetailsRules;
import source_files.services.paperWork.abstracts.PaymentDetailsService;
import source_files.services.system.SysPaymentDetailsService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentDetailsServiceImpl implements PaymentDetailsService {

    private final SysPaymentDetailsService entityService;
    private final PaymentDetailsRules rules;

    @Override
    public PaymentDetailsDTO update(UpdatePaymentDetailsRequest updatePaymentDetailsRequest) {
        return entityService.update(updatePaymentDetailsRequest).toModel();
    }

    @Override
    public PaymentDetailsDTO getById(int id) {
        return entityService.getById(id).toModel();
    }

    @Override
    public List<PaymentDetailsDTO> getAll() {
        return mapToDTOList(entityService.getAll());
    }

    @Override
    public List<PaymentDetailsDTO> getAllFiltered(Double minAmount, Double maxAmount,
                                                  LocalDate minDate, LocalDate maxDate, Boolean isDeleted) {

        List<PaymentDetailsEntity> filteredEntities =
                entityService.getAllFiltered(
                        minAmount, maxAmount);
        filteredEntities = this.filterByBetweenDates(filteredEntities, minDate, maxDate);
        filteredEntities = this.filterByIsDeleted(filteredEntities, isDeleted);

        return mapToDTOList(filteredEntities);
    }

    @Override
    public Double getMonthlyIncome(LocalDate startDate, LocalDate endDate) {
        List<PaymentDetailsEntity> entityList = filterByBetweenDates(entityService.getAll(), startDate, endDate);
        return this.calculateTotalIncome(entityList);
    }

    @Override
    public Double getYearlyIncome(int year) {
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year, 12, 31);

        return this.calculateTotalIncome(
                filterByBetweenDates(entityService.getAll(), startDate, endDate)
        );
    }

    @Override
    public Double getTotalIncome() {
        List<PaymentDetailsEntity> entityList = entityService.getAll();

        return this.calculateTotalIncome(entityList);
    }

    //----------------------------------LOCAL METHODS-----------------------------------------------------------
    private List<PaymentDetailsEntity> filterByBetweenDates(List<PaymentDetailsEntity> entityList, LocalDate minDate, LocalDate maxDate) {
        LocalDateTime minDateTemp = getTruncatedDateTime(minDate, LocalTime.MIN, LocalDateTime.MIN);
        LocalDateTime maxDateTemp = getTruncatedDateTime(maxDate, LocalTime.MAX, LocalDateTime.MAX);

        List<PaymentDetailsEntity> filteredList = new ArrayList<>();

        for (PaymentDetailsEntity entity : entityList) {
            if (isBetweenDates(entity.getCreatedDate(), minDateTemp, maxDateTemp)) {
                filteredList.add(entity);
            }
        }

        return filteredList;
    }

    private List<PaymentDetailsEntity> filterByIsDeleted(List<PaymentDetailsEntity> entityList, Boolean isDeleted) {

        List<PaymentDetailsEntity> filteredList = new ArrayList<>();

        for (PaymentDetailsEntity entity : entityList) {
            if (isDeleted == null || entity.getIsDeleted() == isDeleted) {
                filteredList.add(entity);
            }
        }
        return filteredList;
    }

    private LocalDateTime getTruncatedDateTime(LocalDate date, LocalTime time, LocalDateTime ifNull) {
        return (date != null ? date.atTime(time) : ifNull).truncatedTo(ChronoUnit.SECONDS);
    }

    private boolean isBetweenDates(LocalDateTime dateToCheck, LocalDateTime startDate, LocalDateTime endDate) {
        return (dateToCheck.isEqual(startDate) || dateToCheck.isAfter(startDate))
                && (dateToCheck.isEqual(endDate) || dateToCheck.isBefore(endDate));
    }

    private Double calculateTotalIncome(List<PaymentDetailsEntity> entityList) {
        return entityList.stream()
                .mapToDouble(PaymentDetailsEntity::getAmount)
                .sum();
    }

    private List<PaymentDetailsDTO> mapToDTOList(List<PaymentDetailsEntity> entityList) {
        rules.checkDataList(entityList);
        return entityList.stream()
                .map(PaymentDetailsEntity::toModel).toList();
    }

}
