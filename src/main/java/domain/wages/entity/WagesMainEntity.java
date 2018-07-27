package domain.wages.entity;

import domain.shiro.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class WagesMainEntity extends AbstractEntity{

    //工资名称
    private String wagesName;

    //工资发放时间
    private Date wagesDate;

    @Override
    public String toString() {
        return "WagesMainEntity{" +
                "wagesName='" + wagesName + '\'' +
                ", wagesDate=" + wagesDate +
                '}';
    }
}
