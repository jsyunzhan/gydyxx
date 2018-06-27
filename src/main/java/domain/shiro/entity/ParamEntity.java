package domain.shiro.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParamEntity extends AbstractEntity {

    //参数名称
    private String paramName;

    //参数类型
    private String paramType;

    @Override
    public String toString() {
        return "ParamEntity{" +
                "paramName='" + paramName + '\'' +
                ", paramType='" + paramType + '\'' +
                '}';
    }
}
