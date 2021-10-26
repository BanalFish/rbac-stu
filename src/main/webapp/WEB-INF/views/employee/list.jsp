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
        $(function () {

            //删除单个员工信息
            $(".btn-delete").click(function () {
                var id = $(this).data('id');//获取id参数
                $.messager.confirm('警告', '确认要删除吗?', function () {
                    $.get("/employee/delete",{id:id},function (data) {
                        if (data.success){
                            window.location.reload();//重新加载当前页面并且会携带参数
                        }else {
                            $.messager.popup(data.msg);
                        }
                    })
                    // $.get("/department/delete.do",{id:id},handlerMessage)
                })
            })

            //checkbok全选或者反选
            $("#selEmployees").click(function () {
                var isChecked = $("#selEmployees").prop("checked");
                $("input[name='id']").prop('checked', isChecked);
                console.log("isChecked", isChecked)
                console.log($("input[name='id']"))

            });

            //批量删除员工信息
            $("#btn_batch_delete").click(function () {
                var ck = $("input:checked[name='id']");
                if(ck.length == 0){
                    alert("请选择,然后进行删除");
                    return;
                }
                var ids = [];
                ck.each(function (i, ele) {
                    ids.push($(ele).data("id"))
                })
                console.log("ids==>", ids);

                //发起请求。url  参数  请求成功回调
                $.ajax({
                    url:"/employee/batchDeleteByIds",
                    type:"get",
                    data:{"ids" : ids},
                    dataType:"json",
                    traditional:true,
                    success:function (result) {
                        console.log(result.success);
                        if (result.success){
                            window.location.reload();
                        }
                    }
                });

            });

            $(".tdPage").find("a").click(function () {
                var currentPage = $(this).attr("data");
                $("#searchForm").find("input[name='currentPage']").val(currentPage);
                $("#searchForm").submit();
            });

        })

        //选中员工状态是否和当前员工数量是否相等。如果相等，全选 按钮置于选中状态否则取消状态
        function selEmpOnchange() {
            var checkboxs = $("input[name='id']:checked")
            checkboxs = $("input:checked[name='id']");
            var isEql = checkboxs.length == $("input[name='id']").length;
            //设置选中员工checkbox状态
            $("#selEmployees").prop("checked", isEql);

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
            <h1>员工管理</h1>
        </section>
        <section class="content">
            <div class="box">
                <!--高级查询--->
                <div style="margin: 10px;">
                    <form class="form-inline" id="searchForm" action="/employee/list" method="post">
                        <input type="hidden" name="currentPage" id="currentPage" value="1">
                        <div class="form-group">
                            <label for="keyword">关键字:</label>
                            <input type="text" class="form-control" id="keyword" name="keyword" value="${qo.keyword}" placeholder="请输入姓名/邮箱">
                        </div>
                        <div class="form-group">
                            <label for="dept"> 部门:</label>
                            <select class="form-control" id="dept" name="deptId">
                                <option value="-1">全部</option>
                                <c:forEach items="${departments}" var="d">
                                    <option value="${d.id}">${d.name}</option>
                                </c:forEach>
                            </select>
                            <script>
                                $("#dept").val(${qo.deptId})
                            </script>
                        </div>
                        <button id="btn_query" class="btn btn-primary"><span class="glyphicon glyphicon-search"></span> 查询</button>
                        <a href="/employee/input" class="btn btn-success btn_redirect">
                            <span class="glyphicon glyphicon-plus"></span> 添加
                        </a>
                        <button id="btn_batch_delete" class="btn btn-danger btn-primary" type="button">
                            <span class="glyphicon glyphicon-trash"></span> 批量删除</button>
                    </form>
                </div>
                <table class="table table-hover table-bordered">
                    <thead>
                    <tr>
                        <th><input type="checkbox" id="selEmployees"></th>
                        <th>编号</th>
                        <th>名称</th>
                        <th>email</th>
                        <th>年龄</th>
                        <th>部门</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <c:forEach items="${employees}" var="employee" varStatus="vs">
                        <tr>
                            <td><input id="selEmp" type="checkbox" class="checkbox" data-id="${employee.id}" name="id" onchange="selEmpOnchange()"></td>
                            <td>${vs.count}</td>
                            <td>${employee.name}</td>
                            <td>${employee.email}</td>
                            <td>${employee.age}</td>
                            <td>${employee.dept.name}</td>
                            <td>
                                <a href="/employee/input?id=${employee.id}" class="btn btn-info btn-xs btn_redirect">
                                    <span class="glyphicon glyphicon-pencil"></span> 编辑
                                </a>
                                <a href="/employee/delete?id=${employee.id}" data-id="${employee.id}" class="btn btn-danger btn-xs btn-delete">
                                    <span class="glyphicon glyphicon-trash"></span> 删除
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
<%--                    <tr align="center">--%>
<%--                        <td colspan="9" class="tdPage">--%>
<%--                            <a href="javascript:void(0)" data="1">首页</a>--%>
<%--                            <a href="javascript:void(0)" data="${pageResult.prevPage}">上一页</a>--%>
<%--                            <a href="javascript:void(0)" data="${pageResult.nextPage}">下一页</a>--%>
<%--                            <a href="javascript:void(0)" data="${pageResult.totalPage}">尾页</a>--%>
<%--                            当前${pageResult.currentPage} / ${pageResult.totalPage}--%>
<%--                            当前总共${pageResult.totalCount}条数--%>
<%--                        </td>--%>
<%--                    </tr>--%>
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
