package domain.home.entity;

import domain.shiro.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileEntity extends AbstractEntity{

    //学校概况标题
    private String profileTitle;

    //学校概况正文
    private String profileDetails;

    @Override
    public String toString() {
        return "ProfileEntity{" +
                "profileTitle='" + profileTitle + '\'' +
                ", profileDetails='" + profileDetails + '\'' +
                '}';
    }
}
