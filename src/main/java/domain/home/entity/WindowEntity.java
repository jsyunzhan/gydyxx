package domain.home.entity;

import domain.shiro.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WindowEntity extends AbstractEntity{

    private String windowTitle;

    private String windowDetails;

    private String picturePath;

    private Long statueId;

    @Override
    public String toString() {
        return "WindowEntity{" +
                "windowTitle='" + windowTitle + '\'' +
                ", windowDetails='" + windowDetails + '\'' +
                ", picturePath='" + picturePath + '\'' +
                '}';
    }
}
