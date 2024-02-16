package source_files.services.paperWorkServices.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.paperWorkDTOs.PaymentDetailsDTO;
import source_files.data.models.paperWorkEntities.paymentEntities.PaymentDetailsEntity;
import source_files.data.requests.paperworkRequests.paymentRequests.UpdatePaymentDetailsRequest;
import source_files.services.BusinessRules.paperWork.PaymentDetailsBusinessRules;
import source_files.services.paperWorkServices.abstracts.PaymentDetailsService;
import source_files.services.systemServices.SysPaymentDetailsService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentDetailsManager implements PaymentDetailsService {

    private final SysPaymentDetailsService entityService;
    private final ModelMapperService mapper;
    private final PaymentDetailsBusinessRules rules;

    @Override
    public PaymentDetailsDTO update(UpdatePaymentDetailsRequest updatePaymentDetailsRequest) {
        PaymentDetailsEntity paymentDetails = mapper.forRequest().map(updatePaymentDetailsRequest, PaymentDetailsEntity.class);
        return mapToDTO(entityService.update(paymentDetails));
    }

    @Override
    public PaymentDetailsDTO getById(int id) {
        return mapToDTO(entityService.getById(id));
    }

    @Override
    public List<PaymentDetailsDTO> getAll() {
        return rules.checkDataList(entityService.getAll()).stream()
                .map(o -> mapToDTO((PaymentDetailsEntity) o)).toList();
    }

    @Override
    public List<PaymentDetailsDTO> getAllFiltered(Double minAmount, Double maxAmount,
                                                  LocalDate minDate, LocalDate maxDate, Boolean isDeleted) {

        List<PaymentDetailsEntity> filteredEntities =
                entityService.getAllFiltered(
                        minAmount, maxAmount);
        filteredEntities = this.filterByBetweenDates(filteredEntities, minDate, maxDate);
        filteredEntities = this.filterByIsDeleted(filteredEntities, isDeleted);

        return rules.checkDataList(filteredEntities)
                .stream()
                .map(o -> mapToDTO((PaymentDetailsEntity) o))
                .toList();
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

    private PaymentDetailsDTO mapToDTO(PaymentDetailsEntity paymentDetailsEntity) {
        return mapper.forResponse().map(paymentDetailsEntity, PaymentDetailsDTO.class);
    }

}
