package domain.home.entity;

import domain.shiro.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeachingEntity extends AbstractEntity{

    //教学资源标题
    private String teachingTitle;

    //教学资源内容
    private String teachingDetails;

    @Override
    public String toString() {
        return "TeachingEntity{" +
                "teachingTitle='" + teachingTitle + '\'' +
                ", teachingDetails='" + teachingDetails + '\'' +
                '}';
    }
}
