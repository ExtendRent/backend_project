package src.service.businessrules.abstracts;

import java.util.List;

public interface BaseRules {
    void checkDataList(List<?> list);

    String fixName(String name);

}
