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

    /**
     * 学子风采修改
     */
    public static final String STUDENT_MANAGEMENT_EDIT = STUDENT_MANAGEMENT_PAGE + "/edit";

    /**
     * 学子风采删除
     */
    public static final String STUDENT_MANAGEMENT_DELETE = STUDENT_MANAGEMENT_PAGE + "/delete/{id}";

    /*******************课题研究管理*************************/
    /**
     * 去课题研究管理页面
     */
    public static final String SUBJECT_MANAGEMENT_PAGE = HOME_ROOT + "subjectmanpage";

    /**
     * 课题研究分页
     */
    public static final String SUBJECT_MANAGEMENT_LIST = SUBJECT_MANAGEMENT_PAGE + "/list";

    /**
     * 课题研究新增
     */
    public static final String SUBJECT_MANAGEMENT_ADD = SUBJECT_MANAGEMENT_PAGE + "/add";

    /**
     * 课题研究修改
     */
    public static final String SUBJECT_MANAGEMENT_EDIT = SUBJECT_MANAGEMENT_PAGE + "/edit";

    /**
     * 课题研究删除
     */
    public static final String SUBJECT_MANAGEMENT_DELETE = SUBJECT_MANAGEMENT_PAGE + "/delete/{id}";

    /*******************学校风采管理*************************/
    /**
     * 去学校风采管理页面
     */
    public static final String SCHOOL_MANAGEMENT_PAGE = HOME_ROOT + "schoolmanpage";

    /**
     * 学校风采分页
     */
    public static final String SCHOOL_MANAGEMENT_LIST = SCHOOL_MANAGEMENT_PAGE + "/list";

    /**
     * 学校风采新增
     */
    public static final String SCHOOL_MANAGEMENT_ADD = SCHOOL_MANAGEMENT_PAGE + "/add";

    /**
     * 学校风采修改
     */
    public static final String SCHOOL_MANAGEMENT_EDIT = SCHOOL_MANAGEMENT_PAGE + "/edit";

    /**
     * 学校风采删除
     */
    public static final String SCHOOL_MANAGEMENT_DELETE = SCHOOL_MANAGEMENT_PAGE + "/delete/{id}";

    /*******************学校概况管理*************************/
    /**
     * 去学校概况管理页面
     */
    public static final String PROFILE_MANAGEMENT_PAGE = HOME_ROOT + "profilemanpage";

    /**
     * 学校概况分页
     */
    public static final String PROFILE_MANAGEMENT_LIST = PROFILE_MANAGEMENT_PAGE + "/list";

    /**
     * 学校概况新增
     */
    public static final String PROFILE_MANAGEMENT_ADD = PROFILE_MANAGEMENT_PAGE + "/add";

    /**
     * 学校概况修改
     */
    public static final String PROFILE_MANAGEMENT_EDIT = PROFILE_MANAGEMENT_PAGE + "/edit";

    /**
     * 学校概况删除
     */
    public static final String PROFILE_MANAGEMENT_DELETE = PROFILE_MANAGEMENT_PAGE + "/delete/{id}";

    /*******************党建工会管理*************************/
    /**
     * 去党建工会管理页面
     */
    public static final String PARTY_MANAGEMENT_PAGE = HOME_ROOT + "partymanpage";

    /**
     * 党建工会分页
     */
    public static final String PARTY_MANAGEMENT_LIST = PARTY_MANAGEMENT_PAGE + "/list";

    /**
     * 党建工会新增
     */
    public static final String PARTY_MANAGEMENT_ADD = PARTY_MANAGEMENT_PAGE + "/add";

    /**
     * 党建工会修改
     */
    public static final String PARTY_MANAGEMENT_EDIT = PARTY_MANAGEMENT_PAGE + "/edit";

    /**
     * 党建工会删除
     */
    public static final String PARTY_MANAGEMENT_DELETE = PARTY_MANAGEMENT_PAGE + "/delete/{id}";

    /*******************规章制度管理*************************/
    /**
     * 去规章制度管理页面
     */
    public static final String RULES_MANAGEMENT_PAGE = HOME_ROOT + "rulesmanpage";

    /**
     * 规章制度分页
     */
    public static final String RULES_MANAGEMENT_LIST = RULES_MANAGEMENT_PAGE + "/list";

    /**
     * 规章制度新增
     */
    public static final String RULES_MANAGEMENT_ADD = RULES_MANAGEMENT_PAGE + "/add";

    /**
     * 规章制度修改
     */
    public static final String RULES_MANAGEMENT_EDIT = RULES_MANAGEMENT_PAGE + "/edit";

    /**
     * 规章制度删除
     */
    public static final String RULES_MANAGEMENT_DELETE = RULES_MANAGEMENT_PAGE + "/delete/{id}";

    /*******************责任督学管理*************************/
    /**
     * 去责任督学管理页面
     */
    public static final String RESPONSIBILITY_MANAGEMENT_PAGE = HOME_ROOT + "responsibilitymanpage";

    /**
     * 责任督学分页
     */
    public static final String RESPONSIBILITY_MANAGEMENT_LIST = RESPONSIBILITY_MANAGEMENT_PAGE + "/list";

    /**
     * 责任督学新增
     */
    public static final String RESPONSIBILITY_MANAGEMENT_ADD = RESPONSIBILITY_MANAGEMENT_PAGE + "/add";

    /**
     * 责任督学修改
     */
    public static final String RESPONSIBILITY_MANAGEMENT_EDIT = RESPONSIBILITY_MANAGEMENT_PAGE + "/edit";

    /**
     * 责任督学删除
     */
    public static final String RESPONSIBILITY_MANAGEMENT_DELETE = RESPONSIBILITY_MANAGEMENT_PAGE + "/delete/{id}";

    /*******************文明创建管理*************************/
    /**
     * 去文明创建管理页面
     */
    public static final String CIVILIZATION_MANAGEMENT_PAGE = HOME_ROOT + "civilizationmanpage";

    /**
     * 文明创建分页
     */
    public static final String CIVILIZATION_MANAGEMENT_LIST = CIVILIZATION_MANAGEMENT_PAGE + "/list";

    /**
     * 文明创建新增
     */
    public static final String CIVILIZATION_MANAGEMENT_ADD = CIVILIZATION_MANAGEMENT_PAGE + "/add";

    /**
     * 文明创建修改
     */
    public static final String CIVILIZATION_MANAGEMENT_EDIT = CIVILIZATION_MANAGEMENT_PAGE + "/edit";

    /**
     * 文明创建删除
     */
    public static final String CIVILIZATION_MANAGEMENT_DELETE = CIVILIZATION_MANAGEMENT_PAGE + "/delete/{id}";
}
