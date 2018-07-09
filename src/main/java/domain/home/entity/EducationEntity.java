package domain.home.entity;

import domain.shiro.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EducationEntity extends AbstractEntity{

    //教育科研标题
    private String educationTitle;

    //教育科研内容
    private String educationDetails;

    @Override
    public String toString() {
        return "EducationEntity{" +
                "educationTitle='" + educationTitle + '\'' +
                ", educationDetails='" + educationDetails + '\'' +
                '}';
    }
}
