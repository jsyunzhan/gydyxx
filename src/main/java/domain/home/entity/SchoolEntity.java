package domain.home.entity;

import domain.shiro.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SchoolEntity extends AbstractEntity{

    //学校风采标题
    private String schoolTitle;

    //学校风采正文
    private String schoolDetails;

    //图片地址
    private String picturePath;

    @Override
    public String toString() {
        return "SchoolEntity{" +
                "schoolTitle='" + schoolTitle + '\'' +
                ", schoolDetails='" + schoolDetails + '\'' +
                ", picturePath='" + picturePath + '\'' +
                '}';
    }
}
