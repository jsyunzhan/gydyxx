package domain.system;

/**
 * 系统设置请求地址
 */
public final class SystemWebURLMapping {
    private SystemWebURLMapping(){

    }

    public static final String SYSTEM_ROOT = "/system/";

    /*------------------角色管理--------------------------------------------------------- */
    /**
     * 去角色管理页面
     */
    public static final String ROLE_MANAGEMENT_PAGE = SYSTEM_ROOT + "rolemanpage";

    /**
     * 角色管理分页
     */
    public static final String ROLE_LIST_GET = ROLE_MANAGEMENT_PAGE + "/rolelist";

    /**
     * 新增
     */
    public static final String ROLE_ADD_POST = ROLE_MANAGEMENT_PAGE + "/add";

    //验证是否重名
    public static final String ROLE_CHECK_ROLE_NAME = ROLE_MANAGEMENT_PAGE + "/checkrolename";

    /**
     * 修改
     */
    public static final String ROLE_EDIT_POST = ROLE_MANAGEMENT_PAGE + "/edit";

    /**
     * 删除
     */
    public static final String ROLE_DELETE_GET = ROLE_MANAGEMENT_PAGE + "/delete/{id}";

    /**
     * 资源树
     */
    public static final String RESOURCES_LIST_GET = ROLE_MANAGEMENT_PAGE + "/resources";

    /**
     * 授权
     */
    public static final String RESOURCES_AUTHORIZATION = ROLE_MANAGEMENT_PAGE + "/authorization";

    /*------------------用户管理--------------------------------------------------------- */
    /**
     * 去用户管理页面
     */
    public static final String ACCOUNT_MANAGEMENT_PAGE = SYSTEM_ROOT + "accountmanpage";

    /**
     * 分页
     */
    public static final String ACCOUT_LIST_GET = ACCOUNT_MANAGEMENT_PAGE + "/accountlist";

    /**
     * 获取所有角色,下拉框加载
     */
    public static final String GET_ALL_ROLE = ACCOUNT_MANAGEMENT_PAGE + "/getAllRole";

    /**
     * 新增
     */
    public static final String ACCOUNT_ADD_POST = ACCOUNT_MANAGEMENT_PAGE + "/add";

    /**
     * 新增
     */
    public static final String ACCOUNT_EDIT_POST = ACCOUNT_MANAGEMENT_PAGE + "/edit";

    /**
     * 删除
     */
    public static final String ACCOUNT_DELETE_POST = ACCOUNT_MANAGEMENT_PAGE + "/delete/{id}";

    /**
     * 验证登录名是否重复
     */
    public static final String CHECK_LOGIN_NAME = ACCOUNT_MANAGEMENT_PAGE + "/checkaccountname";


    /************************修改密码**********************************/
    public static final String EDIT_PASSWORD = SYSTEM_ROOT + "editpassword";

}
