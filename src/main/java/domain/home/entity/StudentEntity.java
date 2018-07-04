package domain.home.entity;

import domain.shiro.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentEntity extends AbstractEntity{

    //学子风采标题
    private String studentTitle;

    //学子风采正文
    private String studentDetails;

    //图片地址
    private String picturePath;

    @Override
    public String toString() {
        return "StudentEntity{" +
                "studentTitle='" + studentTitle + '\'' +
                ", studentDetails='" + studentDetails + '\'' +
                ", picturePath='" + picturePath + '\'' +
                '}';
    }
}
