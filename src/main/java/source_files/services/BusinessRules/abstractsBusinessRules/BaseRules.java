package source_files.services.BusinessRules.abstractsBusinessRules;

import java.util.List;

public interface BaseRules {
    void checkDataList(List<?> list);

    String fixName(String name);

}
