package source_files.services.BusinessRules.abstractsBusinessRules;

public interface BaseItemRules extends BaseRules {

    void existsByName(String name);

    void existsByNameAndIdNot(String name, int id);
}
