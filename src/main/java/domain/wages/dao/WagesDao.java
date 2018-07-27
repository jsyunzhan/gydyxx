package domain.wages.dao;

import domain.wages.entity.WagesEntity;
import domain.wages.entity.WagesMainEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WagesDao {
    Integer wagesMainCount(WagesMainEntity wagesMainEntity);

    List<WagesMainEntity> wagesMainList(WagesMainEntity wagesMainEntity);

    /**
     * 工资主表新增
     * @param wagesMainEntity 新增实体
     * @return Integer
     */
    Integer wagesAddMain(WagesMainEntity wagesMainEntity);

    Integer wagesAdd(WagesEntity wagesEntity);

    List<WagesEntity> wagesDetails(Long id);

    Integer wagesMainDelete(Long id);

    Integer wagesDelete(Long id);

    Integer wagesEditMain(WagesMainEntity wagesMainEntity);

    List<WagesEntity> wagesListByAccountId(Long loginId);
}
