package com.springboot.entity;
public class Page {
    private int currentPage=1;    //当前页数
    private int totalPages;       //总页数?
    private int totalUsers;       //记录总数?
    private int pageSize=9;    //每页记录数
    private int nextPage;        //下一页?
    private int prefPage;       //前一页?
    private String paixu;

    public String getPaixu() {
        return paixu;
    }

    public void setPaixu(String paixu) {
        this.paixu = paixu;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public void setPrefPage(int prefPage) {
        this.prefPage = prefPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

	public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        
        return totalPages;
    }

    public int getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(int totalUsers) {
        this.totalUsers = totalUsers;
        this.totalPages = totalUsers % pageSize == 0 ? totalUsers / pageSize : totalUsers / pageSize + 1;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getNextPage() {
        if (currentPage < totalPages) {
            nextPage = currentPage + 1;
        } else {
            nextPage = currentPage;
        }
        return nextPage;
    }

    public int getPrefPage() {
        if (currentPage > 1) {
            prefPage = currentPage - 1;
        } else {
            prefPage = currentPage;
        }
        return prefPage;
    }

}