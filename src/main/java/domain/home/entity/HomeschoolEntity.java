package domain.home.entity;

import domain.shiro.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HomeschoolEntity extends AbstractEntity{

    //家校心桥标题
    private String homeschoolTitle;

    //家校心桥正文
    private String homeschoolDetails;

    @Override
    public String toString() {
        return "HomeschoolEntity{" +
                "homeschoolTitle='" + homeschoolTitle + '\'' +
                ", homeschoolDetails='" + homeschoolDetails + '\'' +
                '}';
    }
}
