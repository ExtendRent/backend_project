package source_files.services.BusinessRules;

import java.util.List;

public interface BaseBusinessRulesService {
    List<?> checkDataList(List<?> list);

    String fixName(String name);

}
