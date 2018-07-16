package domain.home.service;

import domain.home.entity.ClassroomEntity;
import domain.shiro.entity.PageQueryResult;

import java.util.List;

public interface ClassroomManagementService {
    /**
     * 致用校园分页
     * @param classroomEntity 分页实体
     * @return PageQueryResult
     */
    PageQueryResult classroomList(ClassroomEntity classroomEntity);

    /**
     * 致用校园新增
     * @param classroomEntity 新增实体
     * @return Boolean
     */
    Boolean classroomAdd(ClassroomEntity classroomEntity);

    /**
     * 致用课堂修改
     * @param classroomEntity 修改实体
     * @return Boolean
     */
    Boolean classroomEdit(ClassroomEntity classroomEntity);

    /**
     * 致用课堂删除
     * @param id id
     * @param loginId 当前登录id
     * @return Boolean
     */
    Boolean classroomDelete(Long id, Long loginId);

    List<ClassroomEntity> classroomAllList(ClassroomEntity classroomEntity);
}
