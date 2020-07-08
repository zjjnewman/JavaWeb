package com.atguigu.pojo;


import java.util.List;

/**
 * page是分页模型对象
 * @param <T> 具体的 JavaBean类
 */
public class Page <T> {

    public final static Integer PAGE_SIZE = 2;

    /**
     * 当前页码
     */
    private Integer pageNo;

    /**
     * 总页码
     */
    private Integer pageTotal;

    /**
     * 当前页显示数量
     */
    private Integer pageSize = PAGE_SIZE;

    /**
     * 总记录数
     */
    private Integer pageTotalCount;

    /**
     * 当前页数据
     */
    private List<T> items;

    public Page() {
    }

    public Page(Integer pageNo, Integer pageTotal, Integer pageSize, Integer pageTotalCount, List<T> items) {
        pageNo = pageNo < 1 ? 1 : pageNo;
        pageNo = pageNo > pageTotal ? pageTotal : pageNo;
        this.pageNo = pageNo;
        this.pageTotal = pageTotal;
        this.pageSize = pageSize;
        this.pageTotalCount = pageTotalCount;
        this.items = items;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        pageNo = pageNo < 1 ? 1 : pageNo;
        pageNo = pageNo > pageTotal ? pageTotal : pageNo;
        this.pageNo = pageNo;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", pageSize=" + pageSize +
                ", pageTotalCount=" + pageTotalCount +
                ", items=" + items +
                '}';
    }

}
