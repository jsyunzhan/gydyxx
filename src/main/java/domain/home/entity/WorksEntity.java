package domain.home.entity;

import domain.shiro.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorksEntity extends AbstractEntity{

    //作品标题
    private String worksTitle;

    //作品内容
    private String worksDetails;

    //图片地址
    private String picturePath;

    @Override
    public String toString() {
        return "WorksEntity{" +
                "worksTitle='" + worksTitle + '\'' +
                ", worksDetails='" + worksDetails + '\'' +
                ", picturePath='" + picturePath + '\'' +
                '}';
    }
}
