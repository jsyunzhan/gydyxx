package domain.home;

public final class HomeWebURLMapping {
    private HomeWebURLMapping(){
    }

    public static final String HOME_ROOT = "/home/";

    /*******************消息管理*************************/
    /**
     * 去消息管理页面
     */
    public static final String NOTICE_MANAGEMENT_PAGE = HOME_ROOT + "noticemanpage";

    /**
     * 消息管理分页
     */
    public static final String NOTICE_MANAGEMENT_LIST = NOTICE_MANAGEMENT_PAGE + "/list";

    /**
     * 消息管理新增
     */
    public static final String NOTICE_MANAGEMENT_ADD = NOTICE_MANAGEMENT_PAGE + "/add";

    /**
     * 消息管理修改
     */
    public static final String NOTICE_MANAGEMENT_EDIT = NOTICE_MANAGEMENT_PAGE + "/edit";
}
