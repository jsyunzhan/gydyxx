package domain.shiro.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//登录Session
public class WebSessionObject {

    //登录人ID
    private Long id;

    //用户姓名
    private String userName;

    //登录名
    private String loginName;

    //角色ID
    private Long roleId;

    //角色名称
    private String roleName;

}
