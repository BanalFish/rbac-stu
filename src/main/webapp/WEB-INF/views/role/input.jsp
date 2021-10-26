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
        function moveSelected(src, target) {
            console.log(src)
            // $(".selfRoles").append($(".allRoles > option:selected"))
            //
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
                $(".selfPermissions > option").prop("selected", true)
                $("#editForm").submit()
            })

            $("#admin").click(function () {
                // var checked = $("#admin").prop("checked");
                // if (checked){
                //     roleDiv = $("#role").detach();
                // }else {
                //     $("#adminDiv").after(roleDiv)
                // }
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
        $(function () {
            var arr = [];
            //获取右边下拉框中所有option的value属性值，存到数组
            $('.selfPermissions > option').each(function (i, domEle) {
                console.log(i, domEle);
                var id = $(domEle).val();
                arr.push(id);
                console.log(arr);
            });
            //遍历左边的下拉框中的option,若option value在上面数组存在，则删除对应option
            $('.allPermissions > option').each(function (i, domEle) {
                console.log(i, domEle);
                var id = $(domEle).val();
                if ($.inArray(id, arr) >= 0){
                    $(domEle).remove();
                }
            });
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
            <h1>角色编辑</h1>
        </section>
        <section class="content">
            <div class="box">
                <form class="form-horizontal" action="/role/saveOrUpdate" method="post" id="editForm">

                    <input type="hidden" value="${role.id}" name="id">
                    <div class="form-group"  style="margin-top: 10px;">
                        <label for="name" class="col-sm-2 control-label">角色名称：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="name" name="name" value="${role.name}" placeholder="请输入角色名称">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="sn" class="col-sm-2 control-label">角色编号：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="sn" name="sn" value="${role.sn}" placeholder="请输入角色编号">
                        </div>
                    </div>
                    <div class="form-group " id="role">
                        <label for="role" class="col-sm-2 control-label">分配权限：</label><br/>
                        <div class="row" style="margin-top: 10px">
                            <div class="col-sm-2 col-sm-offset-2">
                                <select multiple class="form-control allPermissions" size="15">
                                    <c:forEach items="${permissionList}" var="permission">
                                        <option value="${permission.id}">${permission.name}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="col-sm-1" style="margin-top: 60px;" align="center">
                                <div>

                                    <a type="button" class="btn btn-primary" style="margin-top: 10px" title="右移动"
                                       onclick="moveSelected('allPermissions', 'selfPermissions')">
                                        <span class="glyphicon glyphicon-menu-right"></span>
                                    </a>
                                </div>
                                <div>
                                    <a type="button" class="btn btn-primary " style="margin-top: 10px" title="左移动"
                                       onclick="moveSelected('selfPermissions', 'allPermissions')">
                                        <span class="glyphicon glyphicon-menu-left"></span>
                                    </a>
                                </div>
                                <div>
                                    <a type="button" class="btn btn-primary " style="margin-top: 10px" title="全右移动"
                                       onclick="moveAll('allPermissions', 'selfPermissions')">
                                        <span class="glyphicon glyphicon-forward"></span>
                                    </a>
                                </div>
                                <div>
                                    <a type="button" class="btn btn-primary " style="margin-top: 10px" title="全左移动"
                                       onclick="moveAll('selfPermissions', 'allPermissions')">
                                        <span class="glyphicon glyphicon-backward"></span>
                                    </a>
                                </div>
                            </div>
                            <div class="col-sm-2">
                                <select multiple class="form-control selfPermissions" size="15"  name="ids">
                                    <c:forEach items="${rolePermission}" var="permission">
                                        <option value="${permission.id}">${permission.name}</option>
                                    </c:forEach>
                                </select>
                            </div>

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
