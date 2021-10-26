<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>员工管理</title>
    <%@include file="/WEB-INF/views/common/link.jsp"%>

    <script>
        function moveSelected(src, target) {
            console.log(src)
            // $(".selfRoles").append($(".allRoles > option:selected"))
            $("."+target).append($("."+src+"> option:selected"))
        }

        function moveAll(src, target) {
            $("."+target).append($("." + src + " > option"))
        }
        //表单提交
        var roleDiv
        $(function () {
            $("#submitBtn").click(function () {
                console.log(this)
                //提交之前将selfRoles中的option为选中状态
                $(".selfRoles > option").prop("selected", true)
                $("#editForm").submit()
            })

            $("#admin").click(function () {
                handAdminChecked()
            })

            handAdminChecked()
        })

        function handAdminChecked() {
            var checked = $("#admin").prop("checked");
            if (checked){
                roleDiv = $("#role").detach();
            }else {
                $("#adminDiv").after(roleDiv)
            }
        }

    </script>

</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <%@include file="/WEB-INF/views/common/navbar.jsp"%>
    <!--菜单回显-->
    <c:set var="currentMenu" value="employee"/>
    <%@include file="/WEB-INF/views/common/menu.jsp"%>
    <div class="content-wrapper">
        <section class="content-header">

            <c:choose>
                <c:when test="${empty employee}">
                    <h1>添加员工</h1>
                </c:when>
                <c:otherwise>
                    <h1>编辑员工</h1>
                </c:otherwise>
            </c:choose>

        </section>
        <section class="content">
            <div class="box">
                <form class="form-horizontal" action="/employee/saveOrUpdate" method="post" id="editForm">
                    <input type="hidden" value="${employee.id}" name="id" >
                    <div class="form-group" style="margin-top: 10px;">
                        <label for="name" class="col-sm-2 control-label">用户名：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="name" name="name" value="${employee.name}" placeholder="请输入用户名">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-sm-2 control-label">密码：</label>
                        <div class="col-sm-6">
                            <input type="password" class="form-control" id="password" name="password" value="${employee.password}" placeholder="请输入密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email" class="col-sm-2 control-label">电子邮箱：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="email" name="email" value="${employee.email}" placeholder="请输入邮箱">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="age" class="col-sm-2 control-label">年龄：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="age" name="age" value="${employee.age}" placeholder="请输入年龄">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="dept" class="col-sm-2 control-label">部门：</label>
                        <div class="col-sm-6">
                            <select class="form-control" id="dept" name="dept.id">
                                <c:if test="${not empty departments}">
                                    <c:forEach items="#{departments}" var="d">
                                        <option value="${d.id}">${d.name}</option>
                                    </c:forEach>
                                </c:if>
                            </select>
                        </div>
                    </div>

                    <script>
                        <c:if test="${not empty employee.dept}">
                            $("#dept").val(${employee.dept.id})
                        </c:if>
                    </script>

                    <div class="form-group" id="adminDiv">
                        <label for="admin" class="col-sm-2 control-label">超级管理员：</label>
                        <div class="col-sm-6"style="margin-left: 15px;">
                            <input type="checkbox" id="admin" name="admin" class="checkbox">
                            <c:if test="${employee.admin}">
                                <script>
                                    $("#admin").prop("checked", true);
                                </script>
                            </c:if>
                        </div>
                    </div>

                    <div class="form-group " id="role">
                        <label for="role" class="col-sm-2 control-label">分配角色：</label><br/>
                        <div class="row" style="margin-top: 10px">
                            <div class="col-sm-2 col-sm-offset-2">
                                <select multiple class="form-control allRoles" size="15">
                                    <c:forEach items="#{roleList}" var="role">
                                        <option value="${role.id}">${role.name}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="col-sm-1" style="margin-top: 60px;" align="center">
                                <div>
                                    <a type="button" class="btn btn-primary  " style="margin-top: 10px" title="右移动"
                                       onclick="moveSelected('allRoles', 'selfRoles')">
                                        <span class="glyphicon glyphicon-menu-right"></span>
                                    </a>
                                </div>
                                <div>
                                    <a type="button" class="btn btn-primary " style="margin-top: 10px" title="左移动"
                                       onclick="moveSelected('selfRoles', 'allRoles')">
                                        <span class="glyphicon glyphicon-menu-left"></span>
                                    </a>
                                </div>
                                <div>
                                    <a type="button" class="btn btn-primary " style="margin-top: 10px" title="全右移动"
                                       onclick="moveAll('allRoles', 'selfRoles')">
                                        <span class="glyphicon glyphicon-forward"></span>
                                    </a>
                                </div>
                                <div>
                                    <a type="button" class="btn btn-primary " style="margin-top: 10px" title="全左移动"
                                       onclick="moveAll('selfRoles', 'allRoles')">
                                        <span class="glyphicon glyphicon-backward"></span>
                                    </a>
                                </div>
                            </div>

                            <div class="col-sm-2">
                                <select multiple class="form-control selfRoles" size="15" name="ids">
                                    <c:if test="${not empty empRoleList}">
                                        <c:forEach items="#{empRoleList}" var="r">
                                            <option value="${r.id}">${r.name}</option>
                                        </c:forEach>
                                    </c:if>
                                </select>
                                <script>
                                    var ids = [];
                                    $(".selfRoles > option").each(function (i, ele) {
                                        console.log(ele)
                                        ids.push($(ele).val());
                                    })
                                    $(".allRoles > option").each(function (i, ele) {
                                        var id = $(ele).val();
                                        if ($.inArray(id, ids) != -1){
                                            $(ele).remove();
                                        }
                                    })
                                </script>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-offset-1 col-sm-6">
                            <button id="submitBtn" type="button" class="btn btn-primary">保存</button>
                            <button type="reset" class="btn btn-danger">重置</button>
                        </div>
                    </div>

                </form>

            </div>
        </section>
    </div>
    <%@include file="/WEB-INF/views/common/footer.jsp"%>
</div>
</body>
</html>
