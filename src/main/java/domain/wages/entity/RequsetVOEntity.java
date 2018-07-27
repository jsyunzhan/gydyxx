package domain.wages.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RequsetVOEntity {

    private WagesMainEntity wagesMainEntity;

    private List<WagesEntity> wagesEntities;

    @Override
    public String toString() {
        return "RequsetVOEntity{" +
                "wagesMainEntity=" + wagesMainEntity +
                ", wagesEntities=" + wagesEntities +
                '}';
    }
}
