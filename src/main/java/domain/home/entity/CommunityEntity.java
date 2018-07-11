package domain.home.entity;

import domain.shiro.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommunityEntity extends AbstractEntity{

    //精品社团标题
    private String communityTitle;

    //精品社团内容
    private String communityDetails;

    //图片地址
    private String picturePath;

    @Override
    public String toString() {
        return "CommunityEntity{" +
                "communityTitle='" + communityTitle + '\'' +
                ", communityDetails='" + communityDetails + '\'' +
                ", picturePath='" + picturePath + '\'' +
                '}';
    }
}
