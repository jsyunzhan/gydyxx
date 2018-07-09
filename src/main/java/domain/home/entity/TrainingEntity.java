package domain.home.entity;

import domain.shiro.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrainingEntity extends AbstractEntity{

    //校本培训标题
    private String trainingTitle;

    //校本培训正文
    private String trainingDetails;

    @Override
    public String toString() {
        return "TrainingEntity{" +
                "trainingTitle='" + trainingTitle + '\'' +
                ", trainingDetails='" + trainingDetails + '\'' +
                '}';
    }
}
