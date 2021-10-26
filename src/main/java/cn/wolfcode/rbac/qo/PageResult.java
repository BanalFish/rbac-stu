package cn.wolfcode.rbac.qo;

import java.util.List;

public class PageResult<T> {
    //从页面上传入
    private int currentPage; //当前页
    private int pageSize; //每页显示的条数
    //数据库里面查询出来
    private int totalCount; //数据总条数
    private List<T> data; //当前页的数据列表
    //通过程序计算出来
    private int totalPage; //总页数
    private int prevPage;//上一页
    private int nextPage;//下一页

    public PageResult(int currentPage, int pageSize, int totalCount, List<T> data) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.data = data;

        //计算三个数据
        if (this.totalCount % this.pageSize ==0){
            this.totalPage = this.totalCount / this.pageSize;
        }else{
            this.totalPage = this.totalCount / this.pageSize + 1;
        }

        if (this.currentPage ==1 ){
            this.prevPage = 1;
        }else{
            this.prevPage = this.currentPage - 1;
        }

        if (this.currentPage == this.totalPage){
            this.nextPage = this.totalPage;
        }else{
            this.nextPage = this.currentPage + 1;
        }
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPrevPage() {
        return prevPage;
    }

    public void setPrevPage(int prevPage) {
        this.prevPage = prevPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }
}
