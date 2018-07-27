package domain.wages.entity;

import domain.shiro.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class WagesEntity extends AbstractEntity{

    //用户id
    private Long accountId;

    //用户名称
    private String accountName;

    //工资主表id
    private Long wagesId;

    //基本工资
    private String wagesdetails;

    //工资日期
    private Date wagesData;

    @Override
    public String toString() {
        return "WagesEntity{" +
                "accountId=" + accountId +
                ", accountName='" + accountName + '\'' +
                ", wagesId=" + wagesId +
                ", wagesdetails='" + wagesdetails + '\'' +
                ", wagesData=" + wagesData +
                '}';
    }
}
