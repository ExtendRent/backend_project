package source_files.services.BusinessRules.abstractsBusinessRules;

public interface BaseItemBusinessRulesService extends BaseBusinessRulesService {

    void existsByName(String name);

    void existsByNameAndIdNot(String name, int id);
}
