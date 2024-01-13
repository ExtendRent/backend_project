package source_files.services.BusinessRules.abstractsBusinessRules;

public interface BaseUserBusinessRulesService extends BaseBusinessRulesService {

    void existsByPhoneNumber(String phoneNumber);

    void existsByEmailAddress(String emailAddress);

    void existsByEmailAddressAndIdNot(String emailAddress, int id);

    void existsByPhoneNumberAndIdNot(String phoneNumber, int id);

}
