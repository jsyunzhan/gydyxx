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

    /**
     * 文件上传回显
     */
    public static final String NOTICE_MANAGEMENT_PICTURE_DETAIL = NOTICE_MANAGEMENT_PAGE + "/pictureDetail";

    /**
     * 根据文件路径显示
     */
    public static final String NOTICE_MANAGEMENT_PICTURE_SHOW = HOME_ROOT + "picture/show";

    /**
     * 通知管理图上传
     */
    public static final String NOTICE_MANAGEMENT_PICTURE_UPLOAD = NOTICE_MANAGEMENT_PAGE + "/pictureUpload/{name}";

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

    /*******************作品管理*************************/
    /**
     * 去作品管理页面
     */
    public static final String WROKS_MANAGEMENT_PAGE = HOME_ROOT + "worksmanpage";

    /**
     * 作品管理分页
     */
    public static final String WORKS_MANAGEMENT_LIST = WROKS_MANAGEMENT_PAGE + "/list";

    /**
     * 作品管理新增
     */
    public static final String WORKS_MANAGEMENT_ADD = WROKS_MANAGEMENT_PAGE + "/add";

    /**
     * 作品管理修改
     */
    public static final String WORKS_MANAGEMENT_EDIT = WROKS_MANAGEMENT_PAGE + "/edit";

    /**
     * 作品管理删除
     */
    public static final String WORKS_MANAGEMENT_DELETE = WROKS_MANAGEMENT_PAGE + "/delete/{id}";

    /*******************名师风采管理*************************/
    /**
     * 去名师风采管理页面
     */
    public static final String TEACHER_MANAGEMENT_PAGE = HOME_ROOT + "teachermanpage";

    /**
     * 名师风采分页
     */
    public static final String TEACHER_MANAGEMENT_LIST = TEACHER_MANAGEMENT_PAGE + "/list";

    /**
     * 名师风采新增
     */
    public static final String TEACHER_MANAGEMENT_ADD = TEACHER_MANAGEMENT_PAGE + "/add";

    /**
     * 名师风采修改
     */
    public static final String TEACHER_MANAGEMENT_EDIT= TEACHER_MANAGEMENT_PAGE + "/edit";

    /**
     * 名师风采删除
     */
    public static final String TEACHER_MANAGEMENT_DELETE= TEACHER_MANAGEMENT_PAGE + "/delete/{id}";

    /*******************学子风采管理*************************/
    /**
     * 去学子风采管理页面
     */
    public static final String STUDENT_MANAGEMENT_PAGE = HOME_ROOT + "studentmanpage";

    /**
     * 学子风采分页
     */
    public static final String STUDENT_MANAGEMENT_LIST = STUDENT_MANAGEMENT_PAGE + "/list";

    /**
     * 学子风采新增
     */
    public static final String STUDENT_MANAGEMENT_ADD = STUDENT_MANAGEMENT_PAGE + "/add";
}
