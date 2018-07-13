package domain.home.entity;

import domain.shiro.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailEntity extends AbstractEntity{

    //姓名
    private String sendName;

    //邮箱
    private String sendEmail;

    //内容
    private String sendDetails;

    //号码
    private String sendNumber;

    @Override
    public String toString() {
        return "EmailEntity{" +
                "sendName='" + sendName + '\'' +
                ", sendEmail='" + sendEmail + '\'' +
                ", sendDetails='" + sendDetails + '\'' +
                ", sendNumber='" + sendNumber + '\'' +
                '}';
    }
}
