package domain.home.dao;

import domain.home.entity.RulesEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RulesDao {
    Integer rulesCount(RulesEntity rulesEntity);

    List<RulesEntity> rulesList(RulesEntity rulesEntity);

    Integer rulesAdd(RulesEntity rulesEntity);

    Integer rulesEdit(RulesEntity rulesEntity);
}
