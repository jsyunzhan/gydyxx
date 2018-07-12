package domain.home.entity;

import domain.shiro.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourcesEntity extends AbstractEntity{

    //家校资源标题
    private String resourcesTitle;

    //家校资源内容
    private String resourcesDetails;

    @Override
    public String toString() {
        return "ResourcesEntity{" +
                "resourcesTitle='" + resourcesTitle + '\'' +
                ", resourcesDetails='" + resourcesDetails + '\'' +
                '}';
    }
}
