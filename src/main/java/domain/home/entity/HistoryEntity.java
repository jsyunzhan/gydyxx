package domain.home.entity;

import domain.shiro.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HistoryEntity extends AbstractEntity{

    private String historyTitle;

    private String historyDetails;

    private String picturePath;

    @Override
    public String toString() {
        return "HistoryEntity{" +
                "historyTitle='" + historyTitle + '\'' +
                ", historyDetails='" + historyDetails + '\'' +
                ", picturePath='" + picturePath + '\'' +
                '}';
    }
}
