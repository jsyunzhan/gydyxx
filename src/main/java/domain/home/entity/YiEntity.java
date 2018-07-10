package domain.home.entity;

import domain.shiro.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class YiEntity extends AbstractEntity{

    //致用邑标题
    private String yiTitle;

    //致用邑正文
    private String yiDetails;

    //图片地址
    private String picturePath;

    @Override
    public String toString() {
        return "YiEntity{" +
                "yiTitle='" + yiTitle + '\'' +
                ", yiDetails='" + yiDetails + '\'' +
                ", picturePath='" + picturePath + '\'' +
                '}';
    }
}
