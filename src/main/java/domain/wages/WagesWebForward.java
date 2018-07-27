package domain.wages;

/**
 * 系统设置返回jsp
 */
public class WagesWebForward {
    private WagesWebForward(){
    }

    /**
     * 去工资管理页面
     */
    public static final String TO_WAGES_PAGE = "wages/wagesmanagementpanel";

    /**
     * 去工资查询页面
     */
    public static final String TO_WAGES_QUERY_PAGE = "wages/wagesquerymanagementpanel";
}
