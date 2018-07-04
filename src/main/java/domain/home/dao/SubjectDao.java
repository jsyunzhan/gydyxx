package domain.home.dao;

import domain.home.entity.SubjectEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectDao {
    /**
     * 分页个数
     * @param subjectEntity 查询实体
     * @return Integer
     */
    Integer subjectCount(SubjectEntity subjectEntity);

    /**
     * 分页
     * @param subjectEntity 分页实体
     * @return List<SubjectEntity>
     */
    List<SubjectEntity> subjectList(SubjectEntity subjectEntity);

    /**
     * 课题研究新增
     * @param subjectEntity 新增实体
     * @return Integer
     */
    Integer subjectAdd(SubjectEntity subjectEntity);
}
