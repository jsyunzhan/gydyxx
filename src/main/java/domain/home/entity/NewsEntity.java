package domain.home.entity;

import domain.shiro.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsEntity extends AbstractEntity{

    //新闻标题
    private String newsTitle;

    private byte[] newsDetailsByte;

    //新闻内容
    private String newsDetails;

    //图片地址
    private String picturePath;

    private Long mainFlag;

    private Long changeFlag;

    private Long changeCount;

//    public byte[] getNewsDetails() {
//        return newsDetails == null ? new byte[0] : copyOf(newsDetails, newsDetails.length);
//    }
//
//    public void setNewsDetails(byte[] newsDetails) {
//        this.newsDetails = newsDetails == null ? new byte[0] : copyOf(newsDetails, newsDetails.length);
//    }
//
//    public String getNewsDetailsStr() throws UnsupportedEncodingException {
//        return newsDetails == null ? "" : new String(newsDetails, "UTF-8");
//    }
//
//    public void setNewsDetailsStr(String newsDetailsStr) throws UnsupportedEncodingException {
//        this.newsDetails = isNullOrEmpty(newsDetailsStr) ? new byte[0] : newsDetailsStr.getBytes("UTF-8");
//    }



}
