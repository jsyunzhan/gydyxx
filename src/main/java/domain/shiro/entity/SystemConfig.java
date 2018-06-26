package domain.shiro.entity;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
//公共文件
public final class SystemConfig {

    //登录Session
    public static final String LOGIN_SESSION= "loginSession";

    /**
     * 字符串
     */
    public static final String ROWS = "rows";

    /**
     * 字符串-0
     */
    public static final String ZERO = "0";
}
