package domain.home.entity;

import domain.shiro.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseEntity extends AbstractEntity{

    //班本课程标题
    private String courseTitle;

    //班本课程内容
    private String courseDetails;

    //图片地址
    private String picturePath;

    @Override
    public String toString() {
        return "CourseEntity{" +
                "courseTitle='" + courseTitle + '\'' +
                ", courseDetails='" + courseDetails + '\'' +
                ", picturePath='" + picturePath + '\'' +
                '}';
    }
}
