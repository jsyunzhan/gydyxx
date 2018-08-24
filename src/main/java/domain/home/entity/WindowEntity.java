package domain.home.entity;

import domain.shiro.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WindowEntity extends AbstractEntity{

    private String windowTitle;

    private String windowDetails;

    private String windowUrl;

    private String picturePath;

    private Long statueId;

    private Long statueCount;

    @Override
    public String toString() {
        return "WindowEntity{" +
                "windowTitle='" + windowTitle + '\'' +
                ", windowDetails='" + windowDetails + '\'' +
                ", windowUrl='" + windowUrl + '\'' +
                ", picturePath='" + picturePath + '\'' +
                ", statueId=" + statueId +
                ", statueCount=" + statueCount +
                '}';
    }
}
