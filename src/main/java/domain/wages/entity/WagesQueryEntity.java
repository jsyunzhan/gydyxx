package domain.wages.entity;

import domain.shiro.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class WagesQueryEntity extends AbstractEntity{

    private Date startDate;

    private Date endDate;

    @Override
    public String toString() {
        return "WagesQueryEntity{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
