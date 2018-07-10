package domain.home.entity;

import domain.shiro.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClassroomEntity extends AbstractEntity{

    //致用课堂标题
    private String classroomTitle;

    //致用课堂内容
    private String classroomDetails;

    @Override
    public String toString() {
        return "ClassroomEntity{" +
                "classroomTitle='" + classroomTitle + '\'' +
                ", classroomDetails='" + classroomDetails + '\'' +
                '}';
    }
}
