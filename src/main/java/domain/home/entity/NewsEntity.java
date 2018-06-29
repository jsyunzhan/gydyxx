package domain.home.entity;

import domain.shiro.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsEntity extends AbstractEntity{

    //新闻标题
    private String newsTitle;

    //新闻内容
    private String newsDetails;

    //图片地址
    private String picturePath;

    @Override
    public String toString() {
        return "NewsEntity{" +
                "newsTitle='" + newsTitle + '\'' +
                ", newsDetails='" + newsDetails + '\'' +
                ", picturePath='" + picturePath + '\'' +
                '}';
    }
}
