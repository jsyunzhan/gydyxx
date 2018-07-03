package domain.home.entity;


import domain.shiro.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherEntity extends AbstractEntity{

    //名师风采标题
    private String teacherTitle;

    //名师风采正文
    private String teacherDetails;

    //图片地址
    private String picturePath;

    @Override
    public String toString() {
        return "TeacherEntity{" +
                "teacherTitle='" + teacherTitle + '\'' +
                ", teacherDetails='" + teacherDetails + '\'' +
                ", picturePath='" + picturePath + '\'' +
                '}';
    }
}
