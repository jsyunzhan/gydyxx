package domain.home.entity;

import domain.shiro.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Getter
@Setter
public class NoticeEntity extends AbstractEntity{

    //公告标题
    private String noticeTitle;

    private byte[] noticeDetailsByte;


    private Long noticeFlag;

    //公告内容
    private String noticeDetails;

    //图片地址
    private String picturePath;


//    public String getNoticeDetails() throws UnsupportedEncodingException {
//        return noticeDetailsByte == null ? "" : new String(noticeDetailsByte, "UTF-8");
//    }
//
//    public void setNoticeDetails(String noticeDetailsStr) throws UnsupportedEncodingException {
//        this.noticeDetailsByte = isNullOrEmpty(noticeDetails) ? new byte[0] : noticeDetails.getBytes("UTF-8");
//    }

    @Override
    public String toString() {
        return "NoticeEntity{" +
                "noticeTitle='" + noticeTitle + '\'' +
                ", noticeDetailsByte=" + Arrays.toString(noticeDetailsByte) +
                ", noticeDetails='" + noticeDetails + '\'' +
                ", picturePath='" + picturePath + '\'' +
                '}';
    }
}
