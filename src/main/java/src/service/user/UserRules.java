package src.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import src.core.exception.DataNotFoundException;
import src.repository.user.UserRepository;
import src.service.businessrules.abstracts.BaseUserRules;

import java.util.List;

import static src.core.exception.type.NotFoundExceptionType.EMAIL_ADDRESS_NOT_FOUND;
import static src.core.exception.type.NotFoundExceptionType.USER_LIST_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class UserRules implements BaseUserRules {

    UserRepository repository;

    @Override
    public void checkDataList(List<?> list) {
        if (list.isEmpty()) {
            throw new DataNotFoundException(USER_LIST_NOT_FOUND);
        }
    }

    public void checkEmail(String emailAddress) {
        if (!repository.existsByEmailAddressIgnoreCase(emailAddress)) {
            throw new DataNotFoundException(EMAIL_ADDRESS_NOT_FOUND);
        }
    }

    @Override
    public String fixName(String name) {
        return name;
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
