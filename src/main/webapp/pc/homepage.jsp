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
            <div class="infor">
                <div class="notice">
                    <div class="title_notice">通知公告</div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>