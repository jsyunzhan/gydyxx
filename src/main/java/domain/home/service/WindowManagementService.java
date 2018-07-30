package domain.home.service;

import domain.home.entity.WindowEntity;
import domain.shiro.entity.PageQueryResult;

import java.util.List;

public interface WindowManagementService {
    /**
     * 飘窗分页
     * @param windowEntity 查询实体
     * @return PageQueryResult
     */
    PageQueryResult windowList(WindowEntity windowEntity);

    /**
     * 飘窗新增
     * @param windowEntity 新增实体
     * @return Boolean
     */
    Boolean windowAdd(WindowEntity windowEntity);

    /**
     * 飘窗修改
     * @param windowEntity 修改实体
     * @return Boolean
     */
    Boolean windowEdit(WindowEntity windowEntity);

    /**
     * 飘窗删除
     * @param id id
     * @param loginId 当前登录id
     * @return Boolean
     */
    Boolean windowDelete(Long id, Long loginId);

    List<WindowEntity> windowAllList(WindowEntity windowEntity);
}
