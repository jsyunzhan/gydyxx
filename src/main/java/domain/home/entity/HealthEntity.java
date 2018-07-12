package domain.home.entity;

import domain.shiro.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HealthEntity extends AbstractEntity{

    //健康教育标题
    private String healthTitle;

    //健康教育内容
    private String healthDetails;

    @Override
    public String toString() {
        return "HealthEntity{" +
                "healthTitle='" + healthTitle + '\'' +
                ", healthDetails='" + healthDetails + '\'' +
                '}';
    }
}
