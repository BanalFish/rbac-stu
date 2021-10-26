<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>角色管理</title>
    <%@include file="/WEB-INF/views/common/link.jsp"%>

    <script>
        $(function () {
            //删除单个员工信息
            $(".btn-delete").click(function () {
                var id = $(this).data('id');//获取id参数
                $.messager.confirm('警告', '确认要删除吗?', function () {
                    $.get("/role/delete",{id:id},function (data) {
                        if (data.success){
                            window.location.reload();//重新加载当前页面并且会携带参数
                        }else {
                            $.messager.popup(data.msg);
                        }
                    })
                    // $.get("/department/delete.do",{id:id},handlerMessage)
                })
            })
        })
    </script>

</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <%@include file="/WEB-INF/views/common/navbar.jsp"%>
    <!--菜单回显-->
    <c:set var="currentMenu" value="role"/>
    <%@include file="/WEB-INF/views/common/menu.jsp"%>
    <div class="content-wrapper">
        <section class="content-header">
            <h1>角色管理</h1>
        </section>
        <section class="content">
            <div class="box">
                <!--高级查询--->
                <div style="margin: 10px;">
                    <!--高级查询--->
                    <form class="form-inline" id="searchForm" action="/role/list" method="post">
                        <input type="hidden" name="currentPage" id="currentPage" value="1">
                        <a href="/role/input" class="btn btn-success btn_redirect"><span class="glyphicon glyphicon-plus"></span> 添加</a>
                    </form>

                    <table class="table table-striped table-hover" >
                        <thead>
                        <tr>
                            <th>编号</th>
                            <th>角色名称</th>
                            <th>角色编号</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <c:forEach items="${roles}" var="role" varStatus="vs">
                           <tr>
                               <td>${vs.count}</td>
                               <td>${role.name}</td>
                               <td>${role.sn}</td>
                               <td>
                                   <a href="/role/input?id=${role.id}" class="btn btn-info btn-xs btn_redirect">
                                       <span class="glyphicon glyphicon-pencil"></span> 编辑
                                   </a>
                                   <a href="/role/delete?id=${role.id}" class="btn btn-danger btn-xs btn_delete" >
                                       <span class="glyphicon glyphicon-trash"></span> 删除
                                   </a>
                               </td>
                           </tr>
                        </c:forEach>
                    </table>
                    <!--分页-->
                    <%@include file="/WEB-INF/views/common/page.jsp"%>
                </div>
            </div>
        </section>
    </div>
    <%@include file="/WEB-INF/views/common/footer.jsp"%>
</div>
</body>
</html>
