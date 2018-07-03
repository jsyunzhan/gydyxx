package domain.home.entity;

import domain.shiro.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeEntity extends AbstractEntity{

    //公告标题
    private String noticeTitle;

    //公告内容
    private String noticeDetails;

    //图片地址
    private String picturePath;

    @Override
    public String toString() {
        return "NoticeEntity{" +
                "noticeTitle='" + noticeTitle + '\'' +
                ", noticeDetails='" + noticeDetails + '\'' +
                ", picturePath='" + picturePath + '\'' +
                '}';
    }
}
