package domain.home.entity;

import domain.shiro.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BannerEntity extends AbstractEntity{

    //轮播图标题
    private String bannerTitle;

    //轮播图内容
    private String bannerDetails;

    //轮播图图片地址
    private String picturePath;

    //状态id
    private Long statueId;

    private Long statueCount;

    @Override
    public String toString() {
        return "BannerEntity{" +
                "bannerTitle='" + bannerTitle + '\'' +
                ", bannerDetails='" + bannerDetails + '\'' +
                ", picturePath='" + picturePath + '\'' +
                '}';
    }
}
