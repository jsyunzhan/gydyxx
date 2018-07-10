package domain.home.entity;

import domain.shiro.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FarEntity extends AbstractEntity{

    //致远工作室标题
    private String farTitle;

    //致远工作室正文
    private String farDetails;

    @Override
    public String toString() {
        return "FarEntity{" +
                "farTitle='" + farTitle + '\'' +
                ", farDetails='" + farDetails + '\'' +
                '}';
    }
}
