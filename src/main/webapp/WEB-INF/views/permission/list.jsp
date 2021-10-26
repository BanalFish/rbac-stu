<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>权限管理</title>
    <%@include file="/WEB-INF/views/common/link.jsp"%>
    <script>
        $(function () {
            // $(".btn-delete").click(function () {
            //     var id = $(this).data('id');//获取id参数
            //     $.messager.confirm('警告', '确认要删除吗?', function () {
            //         $.get("/permission/delete",{id:id},function (data) {
            //             if (data.success){
            //                 window.location.reload();//重新加载当前页面并且会携带参数
            //             }else {
            //                 $.messager.popup(data.msg);
            //             }
            //         })
            //         // $.get("/department/delete.do",{id:id},handlerMessage)
            //     })
            // })

            $(".btn_reload").click(function () {
                $.messager.confirm('警告', '确认要重新加载吗?', function () {
                    $.get("/permission/reload.do",function (data) {
                        if (data.success){
                            window.location.reload();//重新加载当前页面并且会携带参数
                        }else {
                            $.messager.popup(data.msg);
                        }
                    })
                })

            })

        })
    </script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <%@include file="/WEB-INF/views/common/navbar.jsp"%>
    <!--菜单回显-->
    <c:set var="currentMenu" value="permission"/>
    <%@include file="/WEB-INF/views/common/menu.jsp"%>
    <div class="content-wrapper">
        <section class="content-header">
            <h1>权限管理</h1>
        </section>
        <section class="content">
            <div class="box" >
                <!--高级查询--->
                <form class="form-inline" id="searchForm" action="/permission/list" method="post">
                    <input type="hidden" name="currentPage" id="currentPage" value="1">
                    <a href="javascript:;" class="btn btn-success btn_reload" style="margin: 10px;">
                        <span class="glyphicon glyphicon-repeat"></span>  重新加载
                    </a>
                </form>

                <table class="table table-striped table-hover" >
                    <thead>
                    <tr>
                        <th>编号</th>
                        <th>权限名称</th>
                        <th>权限表达式</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <c:forEach items="${permissions}" var="permission" varStatus="vs">
                       <tr>
                           <td>${vs.count}</td>
                           <td>${permission.name}</td>
                           <td>${permission.expression}</td>
                           <td>
                               <a href="/permission/delete?id=${permission.id}" class="btn btn-danger btn-xs" >
                                   <span class="glyphicon glyphicon-trash"></span> 删除
                               </a>
                           </td>
                       </tr>
                    </c:forEach>
                    </table>
                <!--分页-->
                <%@include file="/WEB-INF/views/common/page.jsp"%>
            </div>
        </section>
    </div>
    <%@include file="/WEB-INF/views/common/footer.jsp"%>
</div>
</body>
</html>
