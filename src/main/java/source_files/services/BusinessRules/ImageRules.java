package source_files.services.BusinessRules;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.exception.DataNotFoundException;
import source_files.services.BusinessRules.abstractsBusinessRules.BaseRules;

import java.util.List;

import static source_files.exception.exceptionTypes.NotFoundExceptionType.IMAGE_LIST_NOT_FOUND;

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
