package src.services.businessrules.abstracts;

public interface BaseItemRules extends BaseRules {

    void existsByName(String name);

    void existsByNameAndIdNot(String name, int id);
}
