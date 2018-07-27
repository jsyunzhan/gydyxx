package domain.wages;

/**
 * 系统设置请求地址
 */
public final class WagesWebURLMapping {
    private WagesWebURLMapping(){

    }

    public static final String WAGES_ROOT = "/wages/";

    /*------------------角色管理--------------------------------------------------------- */
    /**
     * 去工资管理页面
     */
    public static final String WAGES_MANAGEMENT_PAGE = WAGES_ROOT + "wagesmanpage";

    /**
     * 工资管理主表分页
     */
    public static final String WAGES_MANAGEMENT_LIST = WAGES_MANAGEMENT_PAGE + "/mainlist";

    /**
     * 工资管理查看详情
     */
    public static final String WAGES_MANAGEMENT_DETAILS = WAGES_MANAGEMENT_PAGE + "/details/{id}";

    /**
     * 工资管理新增
     */
    public static final String WAGES_MANAGEMENT_ADD = WAGES_MANAGEMENT_PAGE + "/add";

    /**
     * 工资管理修改
     */
    public static final String WAGES_MANAGEMENT_EDIT = WAGES_MANAGEMENT_PAGE + "/edit";

    /**
     * 工资管理删除
     */
    public static final String WAGES_MANAGEMENT_DELETE = WAGES_MANAGEMENT_PAGE + "/delete/{id}";

    /**
     * 用户表所有数据
     */
    public static final String WAGES_MANAGEMENT_ACCOUNT_ALL_LIST = WAGES_MANAGEMENT_PAGE + "/allaccountlist";

    /**
     * 表格上传
     */
    public static final String WAGES_MANAGEMENT_EXCEL_IMPORT = WAGES_MANAGEMENT_PAGE + "/import";


}
