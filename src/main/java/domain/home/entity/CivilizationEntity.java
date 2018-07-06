package domain.home.entity;

import domain.shiro.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CivilizationEntity extends AbstractEntity{

    //文明创建标题
    private String civilizationTitle;

    //文明创建正文
    private String civilizationDetails;

    //图片地址
    private String picturePath;

    @Override
    public String toString() {
        return "CivilizationEntity{" +
                "civilizationTitle='" + civilizationTitle + '\'' +
                ", civilizationDetails='" + civilizationDetails + '\'' +
                ", picturePath='" + picturePath + '\'' +
                '}';
    }
}
