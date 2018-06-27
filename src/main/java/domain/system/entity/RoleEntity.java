package domain.system.entity;

import domain.shiro.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 角色实体
 */
@Getter
@Setter
public class RoleEntity extends AbstractEntity {

    //角色名称
    private String roleName;

    //角色被使用的个数,>0则不能被删除
    private Integer useCount;

    @Override
    public String toString() {
        return "RoleEntity{" +
                "roleName='" + roleName + '\'' +
                '}';
    }
}
