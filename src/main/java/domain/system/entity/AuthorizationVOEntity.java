package domain.system.entity;

import domain.shiro.entity.ResourceEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AuthorizationVOEntity {

    private Long roleId;

    private List<ResourceEntity> resourceEntities;

    @Override
    public String toString() {
        return "AuthorizationVOEntity{" +
                "roleId=" + roleId +
                ", resourceEntities=" + resourceEntities +
                '}';
    }
}
