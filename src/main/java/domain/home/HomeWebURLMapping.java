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

    /*******************国旗下讲话管理*************************/
    /**
     * 去国旗下讲话管理页面
     */
    public static final String SPEECH_MANAGEMENT_PAGE = HOME_ROOT + "speechmanpage";

    /**
     * 国旗下讲话管理分页
     */
    public static final String SPEECH_MANAGEMENT_LIST = SPEECH_MANAGEMENT_PAGE + "/list";

    /**
     * 国旗下讲话新增
     */
    public static final String SPEECH_MANAGEMENT_ADD = SPEECH_MANAGEMENT_PAGE + "/add";

    /**
     * 国旗下讲话修改
     */
    public static final String SPEECH_MANAGEMENT_EDIT = SPEECH_MANAGEMENT_PAGE + "/edit";

    /**
     * 国旗下讲话删除
     */
    public static final String SPEECH_MANAGEMENT_DELETE = SPEECH_MANAGEMENT_PAGE + "/delete/{id}";

    /*******************校园节庆管理*************************/
    /**
     * 去校园节庆管理页面
     */
    public static final String CELEBRATE_MANAGEMENT_PAGE = HOME_ROOT + "celebratemanpage";

    /**
     * 校园节庆分页
     */
    public static final String CELEBRATE_MANAGEMENT_LIST = CELEBRATE_MANAGEMENT_PAGE + "/list";

    /**
     * 校园节庆新增
     */
    public static final String CELEBRATE_MANAGEMENT_ADD = CELEBRATE_MANAGEMENT_PAGE + "/add";

    /**
     * 校园节庆修改
     */
    public static final String CELEBRATE_MANAGEMENT_EDIT = CELEBRATE_MANAGEMENT_PAGE + "/edit";

    /**
     * 校园节庆删除
     */
    public static final String CELEBRATE_MANAGEMENT_DELETE = CELEBRATE_MANAGEMENT_PAGE + "/delete/{id}";

    /*******************精品社团管理*************************/
    /**
     * 去精品社团管理页面
     */
    public static final String COMMUNITY_MANAGEMENT_PAGE = HOME_ROOT + "communitymanpage";

    /**
     * 精品社团分页
     */
    public static final String COMMUNITY_MANAGEMENT_LIST = COMMUNITY_MANAGEMENT_PAGE + "/list";

    /**
     * 精品社团新增
     */
    public static final String COMMUNITY_MANAGEMENT_ADD = COMMUNITY_MANAGEMENT_PAGE + "/add";

    /**
     * 精品社团修改
     */
    public static final String COMMUNITY_MANAGEMENT_EDIT = COMMUNITY_MANAGEMENT_PAGE + "/edit";

    /**
     * 精品社团删除
     */
    public static final String COMMUNITY_MANAGEMENT_DELETE = COMMUNITY_MANAGEMENT_PAGE + "/delete/{id}";

    /*******************班本课程管理*************************/
    /**
     * 去班本课程管理页面
     */
    public static final String COURSE_MANAGEMENT_PAGE = HOME_ROOT + "coursemanpage";

    /**
     * 班本课程分页
     */
    public static final String COURSE_MANAGEMENT_LIST = COURSE_MANAGEMENT_PAGE + "/list";

    /**
     * 班本课程新增
     */
    public static final String COURSE_MANAGEMENT_ADD = COURSE_MANAGEMENT_PAGE + "/add";

    /**
     * 班本课程修改
     */
    public static final String COURSE_MANAGEMENT_EDIT = COURSE_MANAGEMENT_PAGE + "/edit";

    /**
     * 班本课程删除
     */
    public static final String COURSE_MANAGEMENT_DELETE = COURSE_MANAGEMENT_PAGE + "/delete/{id}";

    /*******************家校心桥管理*************************/
    /**
     * 去家校心桥管理页面
     */
    public static final String HOMESCHOOL_MANAGEMENT_PAGE = HOME_ROOT + "homeschoolmanpage";

    /**
     * 家校心桥分页
     */
    public static final String HOMESCHOOL_MANAGEMENT_LIST = HOMESCHOOL_MANAGEMENT_PAGE + "/list";

    /**
     * 家校心桥新增
     */
    public static final String HOMESCHOOL_MANAGEMENT_ADD = HOMESCHOOL_MANAGEMENT_PAGE + "/add";

    /**
     * 家校心桥修改
     */
    public static final String HOMESCHOOL_MANAGEMENT_EDIT = HOMESCHOOL_MANAGEMENT_PAGE + "/edit";

    /**
     * 家校心桥删除
     */
    public static final String HOMESCHOOL_MANAGEMENT_DELETE = HOMESCHOOL_MANAGEMENT_PAGE + "/delete/{id}";

    /*******************健康教育管理*************************/
    /**
     * 去健康教育管理页面
     */
    public static final String HEALTH_MANAGEMENT_PAGE = HOME_ROOT + "healthmanpage";

    /**
     * 健康教育分页
     */
    public static final String HEALTH_MANAGEMENT_LIST = HEALTH_MANAGEMENT_PAGE + "/list";

    /**
     * 健康教育新增
     */
    public static final String HEALTH_MANAGEMENT_ADD = HEALTH_MANAGEMENT_PAGE + "/add";

    /**
     * 健康教育修改
     */
    public static final String HEALTH_MANAGEMENT_EDIT = HEALTH_MANAGEMENT_PAGE + "/edit";

    /**
     * 健康教育删除
     */
    public static final String HEALTH_MANAGEMENT_DELETE = HEALTH_MANAGEMENT_PAGE + "/delete/{id}";

    /*******************家校资源管理*************************/
    /**
     * 去家校资源管理页面
     */
    public static final String RESOURCES_MANAGEMENT_PAGE = HOME_ROOT + "resourcesmanpage";

    /**
     * 家校资源分页
     */
    public static final String RESOURCES_MANAGEMENT_LIST = RESOURCES_MANAGEMENT_PAGE + "/list";

    /**
     * 家校资源新增
     */
    public static final String RESOURCES_MANAGEMENT_ADD = RESOURCES_MANAGEMENT_PAGE + "/add";

    /**
     * 家校资源修改
     */
    public static final String RESOURCES_MANAGEMENT_EDIT = RESOURCES_MANAGEMENT_PAGE + "/edit";

    /**
     * 家校资源删除
     */
    public static final String RESOURCES_MANAGEMENT_DELETE = RESOURCES_MANAGEMENT_PAGE + "/delete/{id}";

    /*******************校长信箱管理*************************/
    /**
     * 去校长信箱页面
     */
    public static final String EMAIL_MANAGEMENT_PAGE = HOME_ROOT + "emailmanpage";

    /**
     * 校长信箱分页
     */
    public static final String EMAIL_MANAGEMENT_LIST = EMAIL_MANAGEMENT_PAGE + "/list";

    /*******************领导简介管理*************************/
    /**
     * 去领导简介页面
     */
    public static final String LEADER_MANAGEMENT_PAGE = HOME_ROOT + "leadermanpage";

    /**
     * 领导简介分页
     */
    public static final String LEADER_MANAGEMENT_LIST = LEADER_MANAGEMENT_PAGE + "/list";

    /**
     * 领导简介新增
     */
    public static final String LEADER_MANAGEMENT_ADD = LEADER_MANAGEMENT_PAGE + "/add";

    /**
     * 领导简介修改
     */
    public static final String LEADER_MANAGEMENT_EDIT = LEADER_MANAGEMENT_PAGE + "/edit";

    /**
     * 领导简介删除
     */
    public static final String LEADER_MANAGEMENT_DELETE = LEADER_MANAGEMENT_PAGE + "/delete/{id}";

}
