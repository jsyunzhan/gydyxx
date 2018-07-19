package domain.home.entity;

import domain.shiro.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import static com.google.common.base.Strings.isNullOrEmpty;
import static java.util.Arrays.copyOf;

@Getter
@Setter
public class NewsEntity extends AbstractEntity{

    //新闻标题
    private String newsTitle;

    private byte[] newsDetails;

    //新闻内容
    private String newsDetailsStr;

    //图片地址
    private String picturePath;

    public byte[] getNewsDetails() {
        return newsDetails == null ? new byte[0] : copyOf(newsDetails, newsDetails.length);
    }

    public void setNewsDetails(byte[] newsDetails) {
        this.newsDetails = newsDetails == null ? new byte[0] : copyOf(newsDetails, newsDetails.length);
    }

    public String getNewsDetailsStr() throws UnsupportedEncodingException {
        return newsDetails == null ? "" : new String(newsDetails, "UTF-8");
    }

    public void setNewsDetailsStr(String newsDetailsStr) throws UnsupportedEncodingException {
        this.newsDetails = isNullOrEmpty(newsDetailsStr) ? new byte[0] : newsDetailsStr.getBytes("UTF-8");
    }


    @Override
    public String toString() {
        return "NewsEntity{" +
                "newsTitle='" + newsTitle + '\'' +
                ", newsDetails=" + Arrays.toString(newsDetails) +
                ", newsDetailsStr='" + newsDetailsStr + '\'' +
                ", picturePath='" + picturePath + '\'' +
                '}';
    }
}
