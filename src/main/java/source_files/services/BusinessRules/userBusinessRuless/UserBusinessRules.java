package source_files.services.BusinessRules.userBusinessRuless;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.exception.DataNotFoundException;
import source_files.services.BusinessRules.abstractsBusinessRules.BaseUserBusinessRulesService;

import java.util.List;

import static source_files.exception.exceptionTypes.NotFoundExceptionType.USER_LIST_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class UserBusinessRules implements BaseUserBusinessRulesService {
    @Override
    public void checkDataList(List<?> list) {
        if (list.isEmpty()) {
            throw new DataNotFoundException(USER_LIST_NOT_FOUND);
        }
    }

    @Override
    public String fixName(String name) {
        return null;
    }

    @Override
    public void existsByPhoneNumber(String phoneNumber) {

    }

    @Override
    public void existsByEmailAddress(String emailAddress) {

    }

    @Override
    public void existsByEmailAddressAndIdNot(String emailAddress, int id) {

    }

    @Override
    public void existsByPhoneNumberAndIdNot(String phoneNumber, int id) {

    }
}
