<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <!-- 设置360浏览器渲染模式,webkit 为极速内核，ie-comp 为 IE 兼容内核，ie-stand 为 IE 标准内核。 -->
    <meta name="renderer" content="webkit">
    <meta name="google" value="notranslate"><!-- 禁止Chrome 浏览器中自动提示翻译 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <link rel="stylesheet" type="text/css" href="../static/swiper/idangerous.swiper.css">
    <link rel="stylesheet" type="text/css" href="../static/css/public.css">
    <link rel="stylesheet" type="text/css" href="../static/css/homepage.css">
    <script type="text/javascript" src="../static/jq/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="../static/swiper/idangerous.swiper.min.js"></script>
    <script type="text/javascript" src="../static/js/public.js"></script>
    <script type="text/javascript" src="../static/js/homepage.js"></script>
    <title>高邮市第一实验小学</title>
</head>
<body>
    <div class="wrapper">
        <div class="bg">
            <img src="../static/images/bg.png">
        </div>
        <%--学校logo--%>
        <div class="school_logo clearfix">
            <div class="logo">
                <img src="../static/images/logo.png">
            </div>
            <div class="search">
                <div class="search_input">
                    <input type="text" placeholder="请输入关键词">
                    <span><img src="../static/images/search.png"></span>
                </div>
            </div>
        </div>
        <div class="content">
            <%--导航栏--%>
            <div class="navigation clearfix">
            <div class="title_tab">
                <div class="first_title">学校首页</div>
            </div>
            <div class="title_tab">
                <div class="first_title">致用校园</div>
                <div class="second_title">
                    <a href="javascript:;">学校概况</a>
                    <a href="javascript:;">领导简介</a>
                    <a href="javascript:;">通知公告</a>
                    <a href="javascript:;">校园新闻</a>
                    <a href="javascript:;">党建工会</a>
                    <a href="javascript:;">规章制度</a>
                    <a href="javascript:;">责任督学</a>
                    <a href="javascript:;">文明创建</a>
                    <a href="javascript:;">法治校园</a>
                    <a href="javascript:;">校园风光</a>
                </div>
            </div>
            <div class="title_tab">
                <div class="first_title">致用学院</div>
                <div class="second_title">
                    <a href="javascript:;">课题研究</a>
                    <a href="javascript:;">校本培训</a>
                    <a href="javascript:;">教育科研</a>
                    <a href="javascript:;">致用课堂</a>
                </div>
            </div>
            <div class="title_tab">
                <div class="first_title">致用园丁</div>
                <div class="second_title">
                    <a href="javascript:;">名师风采</a>
                    <a href="javascript:;">致远工作室</a>
                    <a href="javascript:;">教学资源</a>
                </div>
            </div>
            <div class="title_tab">
                <div class="first_title">致用少年</div>
                <div class="second_title">
                    <a href="javascript:;">致用邑</a>
                    <a href="javascript:;">国旗下讲话</a>
                    <a href="javascript:;">学子风采</a>
                    <a href="javascript:;">作品展示</a>
                </div>
            </div>
            <div class="title_tab">
                <div class="first_title">致用课程</div>
                <div class="second_title">
                    <a href="javascript:;">校园节庆</a>
                    <a href="javascript:;">精品社团</a>
                    <a href="javascript:;">班本课程</a>
                </div>
            </div>
            <div class="title_tab">
                <div class="first_title">致睦家校</div>
                <div class="second_title">
                    <a href="javascript:;">家校心桥</a>
                    <a href="javascript:;">健康教育</a>
                    <a href="javascript:;">家校资源</a>
                    <a href="javascript:;">校长信箱</a>
                </div>
            </div>
            <div class="title_tab">
                <div class="first_title">联系我们</div>
            </div>
        </div>

            <%--轮播图--%>
            <div class="swiper-container banner">
                <a class="arrow-left" href="#">
                    <img src="../static/images/arrow_left.png">
                </a>
                <a class="arrow-right" href="#">
                    <img src="../static/images/arrow_right.png">
                </a>
                <div class="swiper-wrapper clearfix">
                    <div class="swiper-slide">
                        <img src="../static/images/banner.jpg">
                    </div>
                    <div class="swiper-slide">
                        <img src="../static/images/banner.jpg">
                    </div>
                    <div class="swiper-slide">
                        <img src="../static/images/banner.jpg">
                    </div>
                </div>
                <div class="pagination"></div>
            </div>

            <%--通知新闻类--%>
            <div class="infor clearfix">
                <div class="notice">
                    <div class="title_notice">通知公告</div>
                    <div class="content_notice">
                        <div class="notice_scroll">
                            <div class="notification">1.公公告公告公告公告公告公告公告公告公告公告公告公告</div>
                            <div class="notification">2.公告公告公告公告公告公告公告公告公告公告公告公告</div>
                            <div class="notification">3.公告公告公告公告公告公公告公告公告公告公告</div>
                            <div class="notification">4.公告公告公告公告公告公告公告公告公告公告公告公告</div>
                            <div class="notification">5.公告公告公告公告公告公告公告公告公告公告公告公告</div>
                            <div class="notification">6.公告公告公告公告公告公告公告公告公告公告公告公告公告公告</div>
                        </div>
                    </div>
                </div>
                <div class="news">
                    <div class="title_news">
                        新闻中心丨News Center
                        <a href="javascript:;">更多<span><img src="../static/images/more.png"></span></a>
                    </div>
                    <div class="content_news">
                        <div class="main_title">
                            新闻标题新闻标题新闻标题新闻标题新闻标题新闻标题
                        </div>
                        <div class="secondary_title">
                            新闻副标题新闻副标题新闻副标题<a href="javascript:;">[更多]</a>
                        </div>
                    </div>
                    <div class="main_content clearfix">
                        <div class="main_content_left">
                            <div class="swiper-container news_banner">
                                <div class="swiper-wrapper">
                                    <div class="swiper-slide">
                                        <img src="../static/images/news.jpg">
                                    </div>
                                    <div class="swiper-slide">
                                        <img src="../static/images/news.jpg">
                                    </div>
                                    <div class="swiper-slide">
                                        <img src="../static/images/news.jpg">
                                    </div>
                                </div>
                                <div class="pagination01"></div>
                                <div class="newsPic_title"></div>
                                <div class="newsPic_font">
                                    <div>标题</div>
                                </div>
                            </div>
                        </div>
                        <div class="main_content_right">
                            <div class="news_list"><span><img src="../static/images/icon_round.png"></span>世界献血日献血，高邮市实验小学！世界献血日献血，高邮市实验小学！</div>
                            <div class="news_list"><span><img src="../static/images/icon_round.png"></span>世界献血日献血，高邮市实验小学！世界献血日献血，高邮市实验小学！</div>
                            <div class="news_list"><span><img src="../static/images/icon_round.png"></span>世界献血日献血，高邮市实验小学！世界献血日献血，高邮市实验小学！</div>
                            <div class="news_list"><span><img src="../static/images/icon_round.png"></span>世界献血日献血，高邮市实验小学！世界献血日献血，高邮市实验小学！</div>
                            <div class="news_list"><span><img src="../static/images/icon_round.png"></span>世界献血日献血，高邮市实验小学！世界献血日献血，高邮市实验小学！</div>
                            <div class="news_list"><span><img src="../static/images/icon_round.png"></span>世界献血日献血，高邮市实验小学！世界献血日献血，高邮市实验小学！</div>
                            <div class="news_list"><span><img src="../static/images/icon_round.png"></span>世界献血日献血，高邮市实验小学！世界献血日献血，高邮市实验小学！</div>
                            <div class="news_list"><span><img src="../static/images/icon_round.png"></span>世界献血日献血，高邮市实验小学！世界献血日献血，高邮市实验小学！</div>
                        </div>
                    </div>
                </div>
            </div>

            <%--功能模块快捷方式--%>
            <div class="infor clearfix">
                <div class="icon">
                    <img src="../static/images/icon01.png">
                    <div class="icon_font">学校概况</div>
                </div>
                <div class="icon">
                    <img src="../static/images/icon02.png">
                    <div class="icon_font">通知公告</div>
                </div>
                <div class="icon">
                    <img src="../static/images/icon03.png">
                    <div class="icon_font">校园新闻</div>
                </div>
                <div class="icon">
                    <img src="../static/images/icon04.png">
                    <div class="icon_font">责任督学</div>
                </div>
                <div class="icon">
                    <img src="../static/images/icon05.png">
                    <div class="icon_font">文明创建</div>
                </div>
                <div class="icon">
                    <img src="../static/images/icon06.png">
                    <div class="icon_font">党建工会</div>
                </div>
                <div class="icon">
                    <img src="../static/images/icon07.png">
                    <div class="icon_font">校长信箱</div>
                </div>
                <div class="icon">
                    <img src="../static/images/icon08.png">
                    <div class="icon_font">班本课程</div>
                </div>
                <div class="icon">
                    <img src="../static/images/icon09.png">
                    <div class="icon_font">教育科研</div>
                </div>
                <div class="icon">
                    <img src="../static/images/icon10.png">
                    <div class="icon_font">校园节庆</div>
                </div>
                <div class="icon">
                    <img src="../static/images/icon11.png">
                    <div class="icon_font">精品社区</div>
                </div>
                <div class="icon">
                    <img src="../static/images/icon12.png">
                    <div class="icon_font">家校心桥</div>
                </div>
                <div class="icon">
                    <img src="../static/images/icon13.png">
                    <div class="icon_font">教学资源</div>
                </div>
                <div class="icon">
                    <img src="../static/images/icon14.png">
                    <div class="icon_font">校本培训</div>
                </div>
                <div class="icon">
                    <img src="../static/images/icon15.png">
                    <div class="icon_font">健康教育</div>
                </div>
                <div class="icon">
                    <img src="../static/images/icon16.png">
                    <div class="icon_font">法制校园</div>
                </div>
            </div>

            <%--校史--%>
            <div class="infor clearfix">
                <div class="history">
                    <div class="title_news">
                        校史天地丨School History
                        <a href="javascript:;">更多<span><img src="../static/images/more.png"></span></a>
                    </div>
                    <div class="history_con">
                        <div class="history_list">关于某活动获得奖项关于某活动获得奖项关于某活动获得奖项关于某活动获得奖项</div>
                        <div class="history_list">关于某活动获得奖项</div>
                        <div class="history_list">关于某活动获得奖项</div>
                        <div class="history_list">关于某活动获得奖项</div>
                        <div class="history_list">关于某活动获得奖项</div>
                        <div class="history_list">关于某活动获得奖项</div>
                    </div>
                    <div class="link">
                        <a href="javascript:;" class="linking">友情链接丨LINK</a>
                        <a href="javascript:;" class="linking">国家教育资源平台</a>
                    </div>
                </div>
                <div class="exhibition clearfix">

                    <%--作品展示--%>
                    <div class="ex_child">
                        <div class="title_ex">
                            <img src="../static/images/title_01.jpg">
                            <a href="javascript:;">更多></a>
                        </div>
                        <div class="content_ex">
                            <div class="ex_list clearfix"><p>学校管理学生规范学校管理学生规范学校管理学生规范</p><span>06-25</span></div>
                            <div class="ex_list clearfix"><p>学校管理学生规范学校管理学生规范学校管理学生规范</p><span>06-25</span></div>
                            <div class="ex_list clearfix"><p>学校管理学生规范学校管理学生规范学校管理学生规范</p><span>06-25</span></div>
                            <div class="ex_list clearfix"><p>学校管理学生规范学校管理学生规范学校管理学生规范</p><span>06-25</span></div>
                            <div class="ex_list clearfix"><p>学校管理学生规范学校管理学生规范学校管理学生规范</p><span>06-25</span></div>
                            <div class="ex_list clearfix"><p>学校管理学生规范学校管理学生规范学校管理学生规范</p><span>06-25</span></div>
                        </div>
                    </div>

                    <%--名师风采--%>
                    <div class="ex_child">
                        <div class="title_ex">
                            <img src="../static/images/title_02.jpg">
                            <a href="javascript:;">更多></a>
                        </div>
                        <div class="content_ex">
                            <div class="ex_list clearfix"><p>学校管理学生规范学校管理学生规范学校管理学生规范</p><span>06-25</span></div>
                            <div class="ex_list clearfix"><p>学校管理学生规范学校管理学生规范学校管理学生规范</p><span>06-25</span></div>
                            <div class="ex_list clearfix"><p>学校管理学生规范学校管理学生规范学校管理学生规范</p><span>06-25</span></div>
                            <div class="ex_list clearfix"><p>学校管理学生规范学校管理学生规范学校管理学生规范</p><span>06-25</span></div>
                            <div class="ex_list clearfix"><p>学校管理学生规范学校管理学生规范学校管理学生规范</p><span>06-25</span></div>
                            <div class="ex_list clearfix"><p>学校管理学生规范学校管理学生规范学校管理学生规范</p><span>06-25</span></div>
                        </div>
                    </div>

                    <%--学习风采--%>
                    <div class="ex_child">
                        <div class="title_ex">
                            <img src="../static/images/title_03.jpg">
                            <a href="javascript:;">更多></a>
                        </div>
                        <div class="content_ex">
                            <div class="ex_list clearfix"><p>学校管理学生规范学校管理学生规范学校管理学生规范</p><span>06-25</span></div>
                            <div class="ex_list clearfix"><p>学校管理学生规范学校管理学生规范学校管理学生规范</p><span>06-25</span></div>
                            <div class="ex_list clearfix"><p>学校管理学生规范学校管理学生规范学校管理学生规范</p><span>06-25</span></div>
                            <div class="ex_list clearfix"><p>学校管理学生规范学校管理学生规范学校管理学生规范</p><span>06-25</span></div>
                            <div class="ex_list clearfix"><p>学校管理学生规范学校管理学生规范学校管理学生规范</p><span>06-25</span></div>
                            <div class="ex_list clearfix"><p>学校管理学生规范学校管理学生规范学校管理学生规范</p><span>06-25</span></div>
                        </div>
                    </div>

                    <%--课题研究--%>
                    <div class="ex_child">
                        <div class="title_ex">
                            <img src="../static/images/title_04.jpg">
                            <a href="javascript:;">更多></a>
                        </div>
                        <div class="content_ex">
                            <div class="ex_list clearfix"><p>学校管理学生规范学校管理学生规范学校管理学生规范</p><span>06-25</span></div>
                            <div class="ex_list clearfix"><p>学校管理学生规范学校管理学生规范学校管理学生规范</p><span>06-25</span></div>
                            <div class="ex_list clearfix"><p>学校管理学生规范学校管理学生规范学校管理学生规范</p><span>06-25</span></div>
                            <div class="ex_list clearfix"><p>学校管理学生规范学校管理学生规范学校管理学生规范</p><span>06-25</span></div>
                            <div class="ex_list clearfix"><p>学校管理学生规范学校管理学生规范学校管理学生规范</p><span>06-25</span></div>
                            <div class="ex_list clearfix"><p>学校管理学生规范学校管理学生规范学校管理学生规范</p><span>06-25</span></div>
                        </div>
                    </div>
                </div>
            </div>

            <%--学校风采--%>
            <div class="infor">
                <div class="mien">
                    <img src="../static/images/mien.png">
                    <div class="title_mien">
                        <img src="../static/images/school_fc.png">
                    </div>
                    <div class="swiper-container mien_pic">
                        <div class="swiper-wrapper">
                            <div class="swiper-slide clearfix">
                                <div class="mien_pic_child">
                                    <img src="../static/images/image_01.jpg">
                                    <img src="../static/images/frame.png">
                                </div>
                                <div class="mien_pic_child">
                                    <img src="../static/images/image_01.jpg">
                                    <img src="../static/images/frame.png">
                                </div>
                                <div class="mien_pic_child">
                                    <img src="../static/images/image_01.jpg">
                                    <img src="../static/images/frame.png">
                                </div>
                                <div class="mien_pic_child">
                                    <img src="../static/images/image_01.jpg">
                                    <img src="../static/images/frame.png">
                                </div>
                            </div>
                            <div class="swiper-slide">
                                <div class="mien_pic_child">
                                    <img src="../static/images/image_01.jpg">
                                    <img src="../static/images/frame.png">
                                </div>
                                <div class="mien_pic_child">
                                    <img src="../static/images/image_01.jpg">
                                    <img src="../static/images/frame.png">
                                </div>
                                <div class="mien_pic_child">
                                    <img src="../static/images/image_01.jpg">
                                    <img src="../static/images/frame.png">
                                </div>
                                <div class="mien_pic_child">
                                    <img src="../static/images/image_01.jpg">
                                    <img src="../static/images/frame.png">
                                </div>
                            </div>
                            <div class="swiper-slide">
                                <div class="mien_pic_child">
                                    <img src="../static/images/image_01.jpg">
                                    <img src="../static/images/frame.png">
                                </div>
                                <div class="mien_pic_child">
                                    <img src="../static/images/image_01.jpg">
                                    <img src="../static/images/frame.png">
                                </div>
                                <div class="mien_pic_child">
                                    <img src="../static/images/image_01.jpg">
                                    <img src="../static/images/frame.png">
                                </div>
                                <div class="mien_pic_child">
                                    <img src="../static/images/image_01.jpg">
                                    <img src="../static/images/frame.png">
                                </div>
                            </div>
                        </div>
                        <div class="pagination02"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
