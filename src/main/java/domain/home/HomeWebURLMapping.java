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

    /*******************法制校园管理*************************/
    /**
     * 去法制校园管理页面
     */
    public static final String LAW_MANAGEMENT_PAGE = HOME_ROOT + "lawmanpage";

    /**
     * 法制校园分页
     */
    public static final String LAW_MANAGEMENT_LIST = LAW_MANAGEMENT_PAGE + "/list";

    /**
     * 法制校园新增
     */
    public static final String LAW_MANAGEMENT_ADD = LAW_MANAGEMENT_PAGE + "/add";

    /**
     * 法制校园修改
     */
    public static final String LAW_MANAGEMENT_EDIT = LAW_MANAGEMENT_PAGE + "/edit";

    /**
     * 法制校园删除
     */
    public static final String LAW_MANAGEMENT_DELETE = LAW_MANAGEMENT_PAGE + "/delete/{id}";

    /*******************校本培训管理*************************/
    /**
     * 去校本培训管理页面
     */
    public static final String TRAINING_MANAGEMENT_PAGE = HOME_ROOT + "trainingmanpage";

    /**
     * 校本培训分页
     */
    public static final String TRAINING_MANAGEMENT_LIST = TRAINING_MANAGEMENT_PAGE + "/list";

    /**
     * 校本培训新增
     */
    public static final String TRAINING_MANAGEMENT_ADD = TRAINING_MANAGEMENT_PAGE + "/add";

    /**
     * 校本培训修改
     */
    public static final String TRAINING_MANAGEMENT_EDIT = TRAINING_MANAGEMENT_PAGE + "/edit";

    /**
     * 校本培训删除
     */
    public static final String TRAINING_MANAGEMENT_DELETE = TRAINING_MANAGEMENT_PAGE + "/delete/{id}";

    /*******************教育科研管理*************************/
    /**
     * 去教育科研管理页面
     */
    public static final String EDUCATION_MANAGEMENT_PAGE = HOME_ROOT + "educationmanpage";

    /**
     * 去教育科研管理分页
     */
    public static final String EDUCATION_MANAGEMENT_LIST = EDUCATION_MANAGEMENT_PAGE + "/list";

    /**
     * 去教育科研管理新增
     */
    public static final String EDUCATION_MANAGEMENT_ADD = EDUCATION_MANAGEMENT_PAGE + "/add";

    /**
     * 去教育科研管理修改
     */
    public static final String EDUCATION_MANAGEMENT_EDIT = EDUCATION_MANAGEMENT_PAGE + "/edit";

    /**
     * 去教育科研管理删除
     */
    public static final String EDUCATION_MANAGEMENT_DELETE = EDUCATION_MANAGEMENT_PAGE + "/delete/{id}";

    /*******************致用课堂管理*************************/
    /**
     * 去致用课堂管理页面
     */
    public static final String CLASSROOM_MANAGEMENT_PAGE = HOME_ROOT + "classroommanpage";

    /**
     * 致用校园管理分页
     */
    public static final String CLASSROOM_MANAGEMENT_LIST = CLASSROOM_MANAGEMENT_PAGE + "/list";

    /**
     * 致用校园管理新增
     */
    public static final String CLASSROOM_MANAGEMENT_ADD = CLASSROOM_MANAGEMENT_PAGE + "/add";

    /**
     * 致用校园管理修改
     */
    public static final String CLASSROOM_MANAGEMENT_EDIT = CLASSROOM_MANAGEMENT_PAGE + "/edit";

    /**
     * 致用校园管理删除
     */
    public static final String CLASSROOM_MANAGEMENT_DELETE = CLASSROOM_MANAGEMENT_PAGE + "/delete/{id}";

    /*******************致远工作室管理*************************/
    /**
     * 去致远工作室管理页面
     */
    public static final String FAR_MANAGEMENT_PAGE = HOME_ROOT + "farmanpage";

    /**
     * 致用工作室分页
     */
    public static final String FAR_MANAGEMENT_LIST = FAR_MANAGEMENT_PAGE + "/list";

    /**
     * 致用工作室新增
     */
    public static final String FAR_MANAGEMENT_ADD = FAR_MANAGEMENT_PAGE + "/add";

    /**
     * 致用工作室修改
     */
    public static final String FAR_MANAGEMENT_EDIT = FAR_MANAGEMENT_PAGE + "/edit";

    /**
     * 致用工作室删除
     */
    public static final String FAR_MANAGEMENT_DELETE = FAR_MANAGEMENT_PAGE + "/delete/{id}";

    /*******************教学资源管理*************************/
    /**
     * 去教学资源管理页面
     */
    public static final String TEACHING_MANAGEMENT_PAGE = HOME_ROOT + "teachingmanpage";

    /**
     * 教学资源分页
     */
    public static final String TEACHING_MANAGEMENT_LIST = TEACHING_MANAGEMENT_PAGE + "/list";

    /**
     * 教学资源新增
     */
    public static final String TEACHING_MANAGEMENT_ADD = TEACHING_MANAGEMENT_PAGE + "/add";

    /**
     * 教学资源修改
     */
    public static final String TEACHING_MANAGEMENT_EDIT = TEACHING_MANAGEMENT_PAGE + "/edit";

    /**
     * 教学资源删除
     */
    public static final String TEACHING_MANAGEMENT_DELETE = TEACHING_MANAGEMENT_PAGE + "/delete/{id}";

    /*******************致用邑管理*************************/
    /**
     * 去致用邑管理页面
     */
    public static final String YI_MANAGEMENT_PAGE = HOME_ROOT + "yimanpage";

    /**
     * 致用邑分页
     */
    public static final String YI_MANAGEMENT_LIST = YI_MANAGEMENT_PAGE + "/list";

    /**
     * 致用邑新增
     */
    public static final String YI_MANAGEMENT_ADD = YI_MANAGEMENT_PAGE + "/add";

    /**
     * 致用邑修改
     */
    public static final String YI_MANAGEMENT_EDIT = YI_MANAGEMENT_PAGE + "/edit";

    /**
     * 致用邑删除
     */
    public static final String YI_MANAGEMENT_DELETE = YI_MANAGEMENT_PAGE + "/delete/{id}";
}
