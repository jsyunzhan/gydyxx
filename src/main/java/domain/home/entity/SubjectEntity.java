package domain.home.entity;

import domain.shiro.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectEntity extends AbstractEntity{

    //课题研究标题
    private String subjectTitle;

    //课题研究详情
    private String subjectDetails;

    //图片地址
    private String picturePath;

    @Override
    public String toString() {
        return "SubjectEntity{" +
                "subjectTitle='" + subjectTitle + '\'' +
                ", subjectDetails='" + subjectDetails + '\'' +
                ", picturePath='" + picturePath + '\'' +
                '}';
    }
}
