package domain.shiro.controller;

import domain.shiro.entity.WebSessionObject;
import org.apache.shiro.subject.Subject;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMapWithExpectedSize;
import static domain.shiro.entity.SystemConfig.LOGIN_SESSION;
import static org.apache.shiro.SecurityUtils.getSubject;

public class AbstractActionController {

    //获取登录Session
    private WebSessionObject getLoginSession(){
        final Subject securitySubject = getSubject();
        return (WebSessionObject) securitySubject.getSession().getAttribute(LOGIN_SESSION);
    }

    //获取当前登录id
    protected final Long getLoginId(){
        return getLoginSession().getId();
    }

    //获取当前用户名称
    protected final String getUserName(){
        return getLoginSession().getUserName();
    }

    //获取角色id
    protected final Long getRoleId(){
        return getLoginSession().getRoleId();
    }

    //获取角色名称
    protected final String getRoleName(){
        return getLoginSession().getRoleName();
    }

    /**
     * 封装成树(easyuitree)
     *
     * @param list         实体列表
     * @param pid          父id
     * @param entityClass  实体类
     * @param name         页面显示数据
     * @param parentIdName 父级id的实体字段名
     */
    protected <T> List createTreeGridTree(List<T> list, String pid, Class<T> entityClass, String name, String parentIdName) throws ClassNotFoundException, IllegalAccessException, NoSuchFieldException {
        final List<Map<String, Object>> treeGridList = newArrayList();
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = null;
            final Object object = list.get(i);
            final Field idF = entityClass.getSuperclass().getDeclaredField("id");
            final Field nameF = entityClass.getDeclaredField(name);
            final Field parentIdNameF = entityClass.getDeclaredField(parentIdName);
            idF.setAccessible(true);
            nameF.setAccessible(true);
            parentIdNameF.setAccessible(true);
            if (parentIdNameF.get(object).toString().equals(pid)) {
                map = newHashMapWithExpectedSize(3);
                map.put("id", idF.get(object));
                map.put("text", nameF.get(object));
                map.put("children", createTreeGridChildren(list, (Long) idF.get(object), entityClass, name, parentIdName));
            }
            if (map != null) {
                treeGridList.add(map);
            }
        }
        return treeGridList;
    }

    /**
     * 递归设置树
     *
     * @param list         组织列表
     * @param pid          父id
     * @param entityClass  实体类
     * @param name         显示名字
     * @param parentIdName 父级字段
     */
    private <T> List<Map<String, Object>> createTreeGridChildren(List<T> list, Long pid, Class<T> entityClass, String name, String parentIdName) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        List<Map<String, Object>> childList = newArrayList();
        for (int j = 0; j < list.size(); j++) {
            Map<String, Object> map = null;
            final Object object = list.get(j);
            final Field idF = entityClass.getSuperclass().getDeclaredField("id");
            final Field nameF = entityClass.getDeclaredField(name);
            final Field parentIdNameF = entityClass.getDeclaredField(parentIdName);
            idF.setAccessible(true);
            nameF.setAccessible(true);
            parentIdNameF.setAccessible(true);
            if (parentIdNameF.get(object).toString().equals(pid.toString())) {
                map = newHashMapWithExpectedSize(3);
                map.put("id", idF.get(object));
                map.put("text", nameF.get(object));
                map.put("children", createTreeGridChildren(list, (Long) idF.get(object), entityClass, name, parentIdName));
            }
            if (map != null) {
                childList.add(map);
            }
        }
        return childList;
    }
}
