package domain.home.entity;

import domain.shiro.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CelebrateEntity extends AbstractEntity{

    //校园节庆标题
    private String celebrateTitle;

    //校园节庆内容
    private String celebrateDetails;

    //图片地址
    private String picturePath;

    @Override
    public String toString() {
        return "CelebrateEntity{" +
                "celebrateTitle='" + celebrateTitle + '\'' +
                ", celebrateDetails='" + celebrateDetails + '\'' +
                ", picturePath='" + picturePath + '\'' +
                '}';
    }
}
