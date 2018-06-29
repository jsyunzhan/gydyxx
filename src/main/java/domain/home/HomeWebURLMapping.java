package domain.home;

public final class HomeWebURLMapping {
    private HomeWebURLMapping(){
    }

    public static final String HOME_ROOT = "/home/";

    /*******************通知管理*************************/
    /**
     * 去通知管理页面
     */
    public static final String NOTICE_MANAGEMENT_PAGE = HOME_ROOT + "noticemanpage";

    /**
     * 通知管理分页
     */
    public static final String NOTICE_MANAGEMENT_LIST = NOTICE_MANAGEMENT_PAGE + "/list";

    /**
     * 通知管理新增
     */
    public static final String NOTICE_MANAGEMENT_ADD = NOTICE_MANAGEMENT_PAGE + "/add";

    /**
     * 通知管理修改
     */
    public static final String NOTICE_MANAGEMENT_EDIT = NOTICE_MANAGEMENT_PAGE + "/edit";

    /**
     * 通知管理删除
     */
    public static final String NOTICE_MANAGEMENT_DELETE = NOTICE_MANAGEMENT_PAGE + "/delete/{id}";

    /*******************新闻管理*************************/
    /**
     * 去新闻管理页面
     */
    public static final String NEWS_MANAGEMENT_PAGE = HOME_ROOT + "newsmanpage";

    /**
     * 新闻管理分页
     */
    public static final String NEWS_MANAGEMENT_LIST = NEWS_MANAGEMENT_PAGE + "/list";

    /**
     * 新闻管理新增
     */
    public static final String NEWS_MANAGEMENT_ADD = NEWS_MANAGEMENT_PAGE + "/add";

    /**
     * 新闻管理修改
     */
    public static final String NEWS_MANAGEMENT_EDIT = NEWS_MANAGEMENT_PAGE + "/edit";

    /**
     * 新闻管理删除
     */
    public static final String NEWS_MANAGEMENT_DELETE = NEWS_MANAGEMENT_PAGE + "/delete/{id}";
}
