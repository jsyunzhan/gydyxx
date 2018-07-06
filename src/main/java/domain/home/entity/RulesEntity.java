package domain.home.entity;

import domain.shiro.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RulesEntity extends AbstractEntity{

    //规章制度标题
    private String rulesTitle;

    //规章制度正文
    private String rulesDetails;

    @Override
    public String toString() {
        return "RulesEntity{" +
                "rulesTitle='" + rulesTitle + '\'' +
                ", rulesDetails='" + rulesDetails + '\'' +
                '}';
    }
}
