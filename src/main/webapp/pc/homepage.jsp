<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
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
    <link rel="icon" href="${APP_PATH}/static/images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" type="text/css" href="${APP_PATH}/static/css/public.css">
    <link rel="stylesheet" type="text/css" href="${APP_PATH}/static/css/publicPart.css">
    <link rel="stylesheet" type="text/css" href="${APP_PATH}/static/css/homepage.css">
    <script type="text/javascript" src="${APP_PATH}/static/jq/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="${APP_PATH}/static/js/newSwiper.js"></script>
    <script type="text/javascript" src="${APP_PATH}/static/js/jquery.SuperSlide.2.1.1.js"></script>
    <script type="text/javascript" src="${APP_PATH}/static/js/public.js"></script>
    <script type="text/javascript" src="${APP_PATH}/static/js/publicPart.js"></script>
    <script type="text/javascript" src="${APP_PATH}/static/js/homepage.js"></script>
    <title>高邮市第一实验小学</title>
</head>

<body>
    <div class="wrapper">
        <div class="bg">
            <img src="${APP_PATH}/static/images/bg.png">
        </div>
        <%--学校logo--%>
        <div class="school_logo clearfix">
            <div class="logo">
                <a href="${APP_PATH}/pc/homepage.jsp"><img src="${APP_PATH}/static/images/logo.png"></a>
            </div>
            <div class="search">
                <div class="search_input">
                    <input type="text" placeholder="请输入关键词">
                    <span><img src="${APP_PATH}/static/images/search.png"></span>
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
                        <a href="${APP_PATH}/homepage/profile/details">学校概况</a>
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
                    <div class="first_title"><a href="${APP_PATH}/pc/contact.jsp">联系我们</a></div>
                </div>
            </div>

            <%--轮播图--%>
            <div class="slideBox01">
                <div class="hd">
                    <ul></ul>
                </div>
                <div class="bd">
                    <ul></ul>
                </div>
                <!-- 下面是前/后按钮代码，如果不需要删除即可 -->
                <a class="prev" href="javascript:;"></a>
                <a class="next" href="javascript:;"></a>
            </div>

            <%--通知新闻类--%>
            <div class="infor clearfix">
                <div class="notice">
                    <div class="title_notice">
                        通知公告
                        <a href="${APP_PATH}/pc/zyxiaoyuan/tzgg.jsp">更多<span><img src="${APP_PATH}/static/images/more2.png"></span></a>
                    </div>
                    <div class="content_notice">
                        <div class="notice_scroll">
                        </div>
                    </div>
                </div>
                <div class="news">
                    <div class="title_news">
                        新闻中心丨News Center
                        <a href="${APP_PATH}/pc/zyxiaoyuan/xyxw.jsp">更多<span><img src="${APP_PATH}/static/images/more1.png"></span></a>
                    </div>
                    <div class="content_news"></div>
                    <div class="main_content clearfix">
                        <div class="main_content_left">
                            <div class="slideBox02">
                                <div class="hd">
                                    <ul></ul>
                                </div>
                                <div class="bd">
                                    <ul></ul>
                                    <div class="slideBox02_bg"></div>
                                </div>
                                <!-- 下面是前/后按钮代码，如果不需要删除即可 -->
                                <a class="prev" href="javascript:;"></a>
                                <a class="next" href="javascript:;"></a>
                            </div>
                        </div>
                        <div class="main_content_right">
                        </div>
                    </div>
                </div>
            </div>

            <%--功能模块快捷方式--%>
            <div class="infor clearfix">
                <a href="${APP_PATH}/homepage/profile/details">
                    <div class="icon">
                        <img src="${APP_PATH}/static/images/icon_01.png">
                        <div class="icon_font">学校概况</div>
                    </div>
                </a>
                <a href="${APP_PATH}/pc/zyxiaoyuan/tzgg.jsp">
                    <div class="icon">
                        <img src="${APP_PATH}/static/images/icon_02.png">
                        <div class="icon_font">通知公告</div>
                    </div>
                </a>
                <a href="${APP_PATH}/pc/zyxiaoyuan/xyxw.jsp">
                    <div class="icon">
                        <img src="${APP_PATH}/static/images/icon_03.png">
                        <div class="icon_font">校园新闻</div>
                    </div>
                </a>
                <a href="${APP_PATH}/pc/zyxiaoyuan/zrdx.jsp">
                    <div class="icon">
                        <img src="${APP_PATH}/static/images/icon_04.png">
                        <div class="icon_font">责任督学</div>
                    </div>
                </a>
                <a href="${APP_PATH}/pc/zyxiaoyuan/wmcj.jsp">
                    <div class="icon">
                        <img src="${APP_PATH}/static/images/icon_05.png">
                        <div class="icon_font">文明创建</div>
                    </div>
                </a>
                <a href="${APP_PATH}/pc/zyxiaoyuan/djgh.jsp">
                    <div class="icon">
                        <img src="${APP_PATH}/static/images/icon_06.png">
                        <div class="icon_font">党建工会</div>
                    </div>
                </a>
                <a href="${APP_PATH}/pc/zmjiaxiao/mail.jsp">
                    <div class="icon">
                        <img src="${APP_PATH}/static/images/icon_07.png">
                        <div class="icon_font">校长信箱</div>
                    </div>
                </a>
                <a href="${APP_PATH}/pc/zykecheng/bbkc.jsp">
                    <div class="icon">
                        <img src="${APP_PATH}/static/images/icon_08.png">
                        <div class="icon_font">班本课程</div>
                    </div>
                </a>
                <a href="${APP_PATH}/pc/zyxueyuan/jyky.jsp">
                    <div class="icon">
                        <img src="${APP_PATH}/static/images/icon_09.png">
                        <div class="icon_font">教育科研</div>
                    </div>
                </a>
                <a href="${APP_PATH}/pc/zykecheng/xyjq.jsp">
                    <div class="icon">
                        <img src="${APP_PATH}/static/images/icon_10.png">
                        <div class="icon_font">校园节庆</div>
                    </div>
                </a>
                <a href="${APP_PATH}/pc/zykecheng/jpst.jsp">
                    <div class="icon">
                        <img src="${APP_PATH}/static/images/icon_11.png">
                        <div class="icon_font">精品社团</div>
                    </div>
                </a>
                <a href="${APP_PATH}/pc/zmjiaxiao/jxxq.jsp">
                    <div class="icon">
                        <img src="${APP_PATH}/static/images/icon_12.png">
                        <div class="icon_font">家校心桥</div>
                    </div>
                </a>
                <a href="${APP_PATH}/pc/zyyuanding/jxzy.jsp">
                    <div class="icon">
                        <img src="${APP_PATH}/static/images/icon_13.png">
                        <div class="icon_font">教学资源</div>
                    </div>
                </a>
                <a href="${APP_PATH}/pc/zyxueyuan/xbpx.jsp">
                    <div class="icon">
                        <img src="${APP_PATH}/static/images/icon_14.png">
                        <div class="icon_font">校本培训</div>
                    </div>
                </a>
                <a href="${APP_PATH}/pc/zmjiaxiao/jkjy.jsp">
                    <div class="icon">
                        <img src="${APP_PATH}/static/images/icon_15.png">
                        <div class="icon_font">健康教育</div>
                    </div>
                </a>
                <a href="${APP_PATH}/pc/zyxiaoyuan/fzxy.jsp">
                    <div class="icon">
                        <img src="${APP_PATH}/static/images/icon_16.png">
                        <div class="icon_font">法治校园</div>
                    </div>
                </a>
            </div>

            <%--校史--%>
            <div class="infor clearfix">
                <div class="history">
                    <div class="title_news">
                        校史天地丨School History
                        <a href="${APP_PATH}/pc/xstiandi/xstd.jsp">更多<span><img src="${APP_PATH}/static/images/more1.png"></span></a>
                    </div>
                    <div class="history_con">
                    </div>
                    <div class="link">
                        <a href="javascript:;" class="linking">友情链接丨LINK</a>
                        <a href="javascript:;" class="linking platform">
                            国家教育资源平台
                            <span><img src="${APP_PATH}/static/images/down.png"></span>
                            <div class="linking_list none">
                                <p onclick='window.location.href = "http://www.eduyun.cn/"'>国家教育资源平台</p>
                                <p onclick='window.location.href = "http://be.jse.edu.cn/"'>江苏基础教育平台</p>
                                <p onclick='window.location.href = "http://xbjy.yzjy.com.cn"'>校本教研星级平台</p>
                                <p onclick='window.location.href = "http://yun.yzjy.com.cn"'>扬州智慧校园平台</p>
                                <p onclick='window.location.href = "http://yz.eamn.net/"'>教育装备管理平台</p>
                                <p onclick='window.location.href = "http://xueya.chaoxing.com/"'>超星校园阅读系统</p>
                            </div>
                        </a>
                    </div>
                </div>
                <div class="exhibition clearfix">

                    <%--作品展示--%>
                    <div class="ex_child">
                        <div class="title_ex">
                            作品展示
                            <a href="${APP_PATH}/pc/zyshaonian/zpzs.jsp">更多></a>
                        </div>
                        <div class="content_ex"></div>
                    </div>

                    <%--名师风采--%>
                    <div class="ex_child">
                        <div class="title_ex">
                            名师风采
                            <a href="${APP_PATH}/pc/zyyuanding/msfc.jsp">更多></a>
                        </div>
                        <div class="content_ex"></div>
                    </div>

                    <%--学子风采--%>
                    <div class="ex_child">
                        <div class="title_ex">
                            学子风采
                            <a href="${APP_PATH}/pc/zyshaonian/xzfc.jsp">更多></a>
                        </div>
                        <div class="content_ex"></div>
                    </div>

                    <%--课题研究--%>
                    <div class="ex_child">
                        <div class="title_ex">
                            课题研究
                            <a href="${APP_PATH}/pc/zyxueyuan/ktyj.jsp">更多></a>
                        </div>
                        <div class="content_ex"></div>
                    </div>
                </div>
            </div>

            <%--校园风光--%>
            <div class="infor">
                <div class="mien">
                    <img src="${APP_PATH}/static/images/mien.png">
                    <div class="title_mien">
                        <img src="${APP_PATH}/static/images/school_fc.png">
                    </div>
                    <div class="banner-container mien_pic" id="banner03">
                        <div class="banner-wrapper">
                        </div>
                        <div class="pagination"></div>
                    </div>
                </div>
            </div>
        </div>

        <%--footer--%>
        <div class="footer">
            <div class="footer_con clearfix">
                <div class="footer_img">
                    <img src="${APP_PATH}/static/images/img_02.png">
                </div>
                <div class="footer_font">
                    <p style="line-height: 50px;padding-top: 20px;">Copyright@2018 <a href="${APP_PATH}/pc/homepage.jsp" style="color: #fff;">gydyxx.com</a> All Rights Reserved</p>
                    <p style="line-height: 30px;">学校地址：江苏省高邮市府前街4号 邮编：225600 <a href="${APP_PATH}/security/movetologin" class="loginBtn">用户登录</a></p>
                    <p style="line-height: 30px;"><a href="http://www.miibeian.gov.cn/" target="_blank" class="gxb">苏ICP备10084223号</a></p>
                    <div style="width:300px;margin:0 auto; padding:20px 0;">
                        <a target="_blank" href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=32108402000010" class="clearfix" style="display:inline-block;text-decoration:none;height:20px;line-height:20px;"><img src="${APP_PATH}/static/images/country.png" style="float:left;width:20px;"/><p style="float:left;height:20px;line-height:20px;margin: 0px 0px 0px 5px; color:#fff;">苏公网安备 32108402000010号</p></a>
                    </div>
                </div>
                <div class="footer_img">
                    <img src="${APP_PATH}/static/images/img_01.png">
                </div>
            </div>
        </div>
    </div>

</body>
<script>
    var path  = '<%=request.getContextPath()%>';
</script>
</html>
