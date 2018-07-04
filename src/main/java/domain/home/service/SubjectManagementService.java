package domain.home.service;

import domain.home.entity.SubjectEntity;
import domain.shiro.entity.PageQueryResult;

public interface SubjectManagementService {
    /**
     * 课题研究分页
     * @param subjectEntity 分页实体
     * @return PageQueryResult
     */
    PageQueryResult subjectList(SubjectEntity subjectEntity);

    /**
     * 课题研究新增
     * @param subjectEntity 新增实体
     * @return Boolean
     */
    Boolean subjectAdd(SubjectEntity subjectEntity);
}
