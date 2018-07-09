package domain.home.entity;

import domain.shiro.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LawEntity extends AbstractEntity{

    //法制校园正文
    private String lawTitle;

    //法制校园标题
    private String lawDetails;

    @Override
    public String toString() {
        return "LawEntity{" +
                "lawTitle='" + lawTitle + '\'' +
                ", lawDetails='" + lawDetails + '\'' +
                '}';
    }
}
