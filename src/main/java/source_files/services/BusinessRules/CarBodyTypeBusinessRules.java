package source_files.services.BusinessRules;

import source_files.exception.DataNotFoundException;

import java.util.List;

import static source_files.exception.NotFoundExceptionType.BODY_TYPE_LIST_NOT_FOUND;

public class CarBodyTypeBusinessRules implements BaseBusinessRulesService {


    @Override
    public List<?> checkDataList(List<?> list) {
        if (list.isEmpty()) {
            throw new DataNotFoundException(BODY_TYPE_LIST_NOT_FOUND, "Aradığınız kriterlere uygun araç kasası bulunamadı");
        }
        return list;
    }

    @Override
    public String fixName(String name) {
        return name.replace(" ", "").toLowerCase();
    }
}
