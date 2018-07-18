<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <!-- 设置360浏览器渲染模式,webkit 为极速内核，ie-comp 为 IE 兼容内核，ie-stand 为 IE 标准内核。 -->
    <meta name="renderer" content="webkit">
    <meta name="google" value="notranslate"><!-- 禁止Chrome 浏览器中自动提示翻译 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <%
        pageContext.setAttribute("APP_PATH", request.getContextPath());
    %>
    <link rel="stylesheet" type="text/css" href="../../static/css/public.css">
    <link rel="stylesheet" type="text/css" href="../../static/css/publicPart.css">
    <link rel="stylesheet" type="text/css" href="../../static/css/secondaryPage.css">
    <script type="text/javascript" src="../../static/jq/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="../../static/js/public.js"></script>
    <script type="text/javascript" src="../../static/js/publicPart.js"></script>
    <script type="text/javascript" src="../../static/js/secondaryPage.js"></script>
    <script type="text/javascript" src="../../static/js/data/djgh.js"></script>
    <title>党建工会</title>
</head>
<body>
<div class="wrapper">
    <div class="bg">
        <img src="../../static/images/bg.png">
    </div>
    <%--学校logo--%>
    <div class="school_logo clearfix">
        <div class="logo">
            <img src="../../static/images/logo.png">
        </div>
        <div class="search">
            <div class="search_input">
                <input type="text" placeholder="请输入关键词">
                <span><img src="../../static/images/search.png"></span>
            </div>
        </div>
    </div>
    <div class="content">
        <%--导航栏--%>
        <div class="navigation clearfix">
                <div class="title_tab">
                    <div class="first_title"><a href="${APP_PATH}/pc/homepage.jsp">学校首页</a></div>
                </div>
                <div class="title_tab">
                    <div class="first_title">致用校园</div>
                    <div class="second_title">
                        <a href="${APP_PATH}/pc/zyxiaoyuan/xxgk.jsp">学校概况</a>
                        <a href="${APP_PATH}/pc/zyxiaoyuan/ldjj.jsp">领导简介</a>
                        <a href="${APP_PATH}/pc/zyxiaoyuan/tzgg.jsp">通知公告</a>
                        <a href="${APP_PATH}/pc/zyxiaoyuan/xyxw.jsp">校园新闻</a>
                        <a href="${APP_PATH}/pc/zyxiaoyuan/djgh.jsp">党建工会</a>
                        <a href="${APP_PATH}/pc/zyxiaoyuan/gzzd.jsp">规章制度</a>
                        <a href="${APP_PATH}/pc/zyxiaoyuan/zrdx.jsp">责任督学</a>
                        <a href="${APP_PATH}/pc/zyxiaoyuan/wmcj.jsp">文明创建</a>
                        <a href="${APP_PATH}/pc/zyxiaoyuan/fzxy.jsp">法治校园</a>
                        <a href="${APP_PATH}/pc/zyxiaoyuan/xyfg.jsp">校园风光</a>
                    </div>
                </div>
                <div class="title_tab">
                    <div class="first_title">致用学院</div>
                    <div class="second_title">
                        <a href="${APP_PATH}/pc/zyxueyuan/ktyj.jsp">课题研究</a>
                        <a href="${APP_PATH}/pc/zyxueyuan/xbpx.jsp">校本培训</a>
                        <a href="${APP_PATH}/pc/zyxueyuan/jyky.jsp">教育科研</a>
                        <a href="${APP_PATH}/pc/zyxueyuan/zykt.jsp">致用课堂</a>
                    </div>
                </div>
                <div class="title_tab">
                    <div class="first_title">致用园丁</div>
                    <div class="second_title">
                        <a href="${APP_PATH}/pc/zyyuanding/msfc.jsp">名师风采</a>
                        <a href="${APP_PATH}/pc/zyyuanding/zygzs.jsp">致远工作室</a>
                        <a href="${APP_PATH}/pc/zyyuanding/jxzy.jsp">教学资源</a>
                    </div>
                </div>
                <div class="title_tab">
                    <div class="first_title">致用少年</div>
                    <div class="second_title">
                        <a href="${APP_PATH}/pc/zyshaonian/zyy.jsp">致用邑</a>
                        <a href="${APP_PATH}/pc/zyshaonian/gqxjh.jsp">国旗下讲话</a>
                        <a href="${APP_PATH}/pc/zyshaonian/xzfc.jsp">学子风采</a>
                        <a href="${APP_PATH}/pc/zyshaonian/zpzs.jsp">作品展示</a>
                    </div>
                </div>
                <div class="title_tab">
                    <div class="first_title">致用课程</div>
                    <div class="second_title">
                        <a href="${APP_PATH}/pc/zykecheng/xyjq.jsp">校园节庆</a>
                        <a href="${APP_PATH}/pc/zykecheng/jpst.jsp">精品社团</a>
                        <a href="${APP_PATH}/pc/zykecheng/bbkc.jsp">班本课程</a>
                    </div>
                </div>
                <div class="title_tab">
                    <div class="first_title">致睦家校</div>
                    <div class="second_title">
                        <a href="${APP_PATH}/pc/zmjiaxiao/jxxq.jsp">家校心桥</a>
                        <a href="${APP_PATH}/pc/zmjiaxiao/jkjy.jsp">健康教育</a>
                        <a href="${APP_PATH}/pc/zmjiaxiao/jxziyuan.jsp">家校资源</a>
                        <a href="${APP_PATH}/pc/zmjiaxiao/mail.jsp">校长信箱</a>
                    </div>
                </div>
                <div class="title_tab">
                    <div class="first_title">联系我们</div>
                </div>
        </div>

        <%--校史--%>
        <div class="infor clearfix">

            <div class="leftSide">
                <div class="leftClass">
                    <div class="leftClass_title">
                        致用校园
                    </div>
                    <div class="leftClass_con">
                        <a href="${APP_PATH}/pc/zyxiaoyuan/xxgk.jsp"><p class="leftChild">学校概况<span></span></p></a>
                        <a href="${APP_PATH}/pc/zyxiaoyuan/ldjj.jsp"><p class="leftChild">领导简介<span></span></p></a>
                        <a href="${APP_PATH}/pc/zyxiaoyuan/tzgg.jsp"><p class="leftChild">通知公告<span></span></p></a>
                        <a href="${APP_PATH}/pc/zyxiaoyuan/xyxw.jsp"><p class="leftChild">校园新闻<span></span></p></a>
                        <a href="${APP_PATH}/pc/zyxiaoyuan/djgh.jsp"><p class="leftChild choosen">党建工会<span></span></p></a>
                        <a href="${APP_PATH}/pc/zyxiaoyuan/gzzd.jsp"><p class="leftChild">规章制度<span></span></p></a>
                        <a href="${APP_PATH}/pc/zyxiaoyuan/zrdx.jsp"><p class="leftChild">责任督学<span></span></p></a>
                        <a href="${APP_PATH}/pc/zyxiaoyuan/wmcj.jsp"><p class="leftChild">文明创建<span></span></p></a>
                        <a href="${APP_PATH}/pc/zyxiaoyuan/fzxy.jsp"><p class="leftChild">法治校园<span></span></p></a>
                        <a href="${APP_PATH}/pc/zyxiaoyuan/xyfg.jsp"><p class="leftChild">校园风光<span></span></p></a>
                    </div>
                </div>
                <div class="history">
                    <div class="title_news">
                        校史天地丨School History
                        <a href="javascript:;">更多<span><img src="../../static/images/more1.png"></span></a>
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
                    党建工会
                    <a href="javascript:;">您当前的位置：<b>首页-致用校园-党建工会</b></a>
                </div>
                <div class="content_news01">
                    <div class="newsCon">
                    </div>
                    <script type="text/javascript" src="../../static/js/paging.js"></script>
                </div>
            </div>
        </div>
    </div>

    <%--footer--%>
    <div class="footer">
        <div class="footer_con clearfix">
            <div class="footer_img">
                <img src="../../static/images/img_02.png">
            </div>
            <div class="footer_font">
                <p style="line-height: 80px;">Copyright@2018 http://xxx.org All Rights Reserved</p>
                <p style="line-height: 30px;">学校地址：江苏省高邮市xxx路xxx号 邮编：225600 <a href="javascript:;">用户登录</a></p>
                <p style="line-height: 30px;">联系电话：0514-8xxxxxxx 苏ICP备xxxxxxxx号 校长信箱：xxxxx@163.com</p>
                <p style="line-height: 80px;">苏公网安备用 7852595156256</p>
            </div>
            <div class="footer_img">
                <img src="../../static/images/img_01.png">
            </div>
        </div>
    </div>
</div>
</body>
<script>
    var path  = '<%=request.getContextPath()%>';
</script>
</html>
