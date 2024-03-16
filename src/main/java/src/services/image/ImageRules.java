package src.services.image;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import src.core.exception.DataNotFoundException;
import src.services.businessrules.abstracts.BaseRules;

import java.util.List;

import static src.core.exception.exception_types.NotFoundExceptionType.IMAGE_LIST_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class ImageRules implements BaseRules {
    @Override
    public void checkDataList(List<?> list) {
        if (list.isEmpty()) {
            throw new DataNotFoundException(IMAGE_LIST_NOT_FOUND);
        }

    }

    @Override
    public String fixName(String name) {
        return null;
    }
}
