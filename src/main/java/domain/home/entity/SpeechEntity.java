package domain.home.entity;

import domain.shiro.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpeechEntity extends AbstractEntity{

    //国旗下讲话标题
    private String speechTitle;

    //国旗下讲话正文
    private String speechDetails;

    //图片地址
    private String picturePath;

    @Override
    public String toString() {
        return "SpeechEntity{" +
                "speechTitle='" + speechTitle + '\'' +
                ", speechDetails='" + speechDetails + '\'' +
                ", picturePath='" + picturePath + '\'' +
                '}';
    }
}
