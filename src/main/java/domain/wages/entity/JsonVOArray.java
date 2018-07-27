package domain.wages.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JsonVOArray {

    private Boolean success = true;

    private String reason;

    private List<WagesEntity> wagesEntityList;

    @Override
    public String toString() {
        return "JsonVOArray{" +
                "success=" + success +
                ", reason='" + reason + '\'' +
                ", wagesEntityList=" + wagesEntityList +
                '}';
    }
}
