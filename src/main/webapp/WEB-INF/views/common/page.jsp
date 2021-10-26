<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<div style="text-align: center;">
    <ul id="pagination" class="pagination"></ul>
</div>
<script>
    //分页
    $(function(){
        $("#pagination").twbsPagination({
            totalPages: ${result.pages},
            <%--startPage: ${result.currentPage > result.totalPage ? result.totalPage : result.currentPage},--%>
            startPage: ${result.pageNum},
            visiblePages:5,
            first:"首页",
            prev:"上页",
            next:"下页",
            last:"尾页",
            initiateStartPageClick:false,
            onPageClick:function(event,page){
                $("#currentPage").val(page);
                $("#searchForm").submit();
            }
        });
    })
</script>
</html>