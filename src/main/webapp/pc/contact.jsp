<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <!-- 设置360浏览器渲染模式,webkit 为极速内核，ie-comp 为 IE 兼容内核，ie-stand 为 IE 标准内核。 -->
    <meta name="renderer" content="webkit">
    <meta name="google" value="notranslate"><!-- 禁止Chrome 浏览器中自动提示翻译 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <link rel="stylesheet" type="text/css" href="../static/css/public.css">
    <link rel="stylesheet" type="text/css" href="../static/css/publicPart.css">
    <link rel="stylesheet" type="text/css" href="../static/css/secondaryPage.css">
    <script type="text/javascript" src="../static/jq/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="../static/js/public.js"></script>
    <script type="text/javascript" src="../static/js/publicPart.js"></script>
    <script type="text/javascript" src="../static/js/secondaryPage.js"></script>
    <title>搜索结果</title>
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

        <%--校史--%>
        <div class="infor clearfix">

            <div class="leftSide">
                <div class="notice">
                    <div class="title_notice">
                        通知公告
                        <a href="javascript:;">更多<span><img src="../static/images/more2.png"></span></a>
                    </div>
                    <div class="content_notice">
                        <div class="notice_scroll">
                            <div class="notification">1.公公告公告公告公告公告公告公告公告公告公告公告公告</div>
                            <div class="notification">2.公告公告公告公告公告公告公告公告公告公告公告公告</div>
                            <div class="notification">3.公告公告公告公告公告公公告公告公告公告公告公告告公告公告告公告公告告公告</div>
                            <div class="notification">4.公告公告公告公告公告公告公告公告公告公告公告公告</div>
                            <div class="notification">5.公告公告公告公告公告公告公告公告公告公告公告公告</div>
                            <div class="notification">6.公告公告公告公告公告公告公告公告公告公告公告告公告公告告公告公告公告</div>
                            <div class="notification">7.公告公告公告公告公告公告公告公告公告公告公告公告公告告公告</div>
                            <div class="notification">8.公告公告公告公告公告公告公告公告公告公告公告公告</div>
                        </div>
                    </div>
                </div>
                <div class="history">
                    <div class="title_news">
                        校史天地丨School History
                        <a href="javascript:;">更多<span><img src="../static/images/more1.png"></span></a>
                    </div>
                    <div class="history_con">
                        <div class="history_list">关于某活动获得奖项关于某活动获得奖项关于某活动获得奖项关于某活动获得奖项</div>
                        <div class="history_list">关于某活动获得奖项</div>
                        <div class="history_list">关于某活动获得奖项</div>
                        <div class="history_list">关于某活动获得奖项</div>
                        <div class="history_list">关于某活动获得奖项</div>
                        <div class="history_list">关于某活动获得奖项</div>
                    </div>
                </div>
            </div>

            <div class="news">
                <div class="title_news">
                    联系我们
                    <a href="javascript:;">您当前的位置：<b>联系我们</b></a>
                </div>
                <div class="content_news01">
                    <div class="main_title01">联系我们</div>
                    <div class="newsCon">
                        <p class="tc tindex0">学校地址：高邮市11路11号</p>
                        <p class="tc tindex0">邮编：225600</p>
                        <p class="tc tindex0">电话：0514—80000000</p>
                        <p class="tc tindex0">邮箱：XXX@163.com</p>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%--footer--%>
    <div class="footer">
        <div class="footer_con clearfix">
            <div class="footer_img">
                <img src="../static/images/img_02.png">
            </div>
            <div class="footer_font">
                <p style="line-height: 80px;">Copyright@2018 http://xxx.org All Rights Reserved</p>
                <p style="line-height: 30px;">学校地址：江苏省高邮市xxx路xxx号 邮编：225600 <a href="javascript:;">用户登录</a></p>
                <p style="line-height: 30px;">联系电话：0514-8xxxxxxx 苏ICP备xxxxxxxx号 校长信箱：xxxxx@163.com</p>
                <p style="line-height: 80px;">苏公网安备用 7852595156256</p>
            </div>
            <div class="footer_img">
                <img src="../static/images/img_01.png">
            </div>
        </div>
    </div>
</div>
</body>
</html>
