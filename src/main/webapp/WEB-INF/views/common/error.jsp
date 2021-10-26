<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<title>叩丁狼客户管理系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="/css/error_css.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="/js/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="/js/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="/js/Ionicons/css/ionicons.min.css">
<link rel="stylesheet" href="/js/adminlte/css/AdminLTE.min.css">
<link rel="stylesheet" href="/js/adminlte/css/skins/_all-skins.min.css">
<link rel="stylesheet" href="/js/adminlte/css/fonts.googleapis.com.css">
<script src="/js/jquery/jquery.min.js"></script>
<script src="/js/bootstrap/js/bootstrap.js"></script>
<script src="/js/adminlte/js/adminlte.min.js"></script>



</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <%@include file="/WEB-INF/views/common/navbar.jsp" %>
    <%@include file="/WEB-INF/views/common/menu.jsp" %>
    <div class="content-wrapper">
        <section class="content-header">
            <h1>错误操作</h1>
        </section>
        <section class="content">
            <div class="box">
                <div style="margin: 10px;">
                    <div class="row col-sm-10" >
                        <div id="login_center">
                            <div id="login_area">
                                <div id="login_box">
                                    <div id="login_form">
                                        <H2>出错啦！</H2>
                                        <span>执行过程中发生了异常：</span>
                                        <span class="error">${errorMsg}</span>
                                        <span>请联系管理员解决！</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </section>
    </div>
    <#include "../common/footer.ftl">
</div>
</body>
</html>
