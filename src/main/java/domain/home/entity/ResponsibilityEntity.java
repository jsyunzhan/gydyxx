package domain.home.entity;

import domain.shiro.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponsibilityEntity extends AbstractEntity{

    //责任督学标题
    private String responsibilityTitle;

    //责任督学正文
    private String responsibilityDetails;

    @Override
    public String toString() {
        return "ResponsibilityEntity{" +
                "responsibilityTitle='" + responsibilityTitle + '\'' +
                ", responsibilityDetails='" + responsibilityDetails + '\'' +
                '}';
    }
}
