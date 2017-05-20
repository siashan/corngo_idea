package com.corngo.base.support.orm;

public class Page {
	/**
     * 页码，从1开始
     */
    private int pageNum;
    /**
     * 页面大小
     */
    private int length;
    /**
     * 起始行
     */
    private int begin;
    /**
     * 末行
     */
    private int end;
    /**
     * 总数
     */
    private int total;
    /**
     * 总页数
     */
    private int pages;
    /**
     * 排序字段
     */
    private String orderBy;
    /**
     * 顺序、倒序
     */
    private String asc;
    
    /**
     * 计算起止行号
     */
    private void calculateStartAndEndRow() {
        this.begin = this.pageNum > 0 ? (this.pageNum - 1) * this.length : 0;
        this.end = this.begin + this.length * (this.pageNum > 0 ? 1 : 0);
    }
    
    public Page(int pageNum, int length, int total){
    	this.pageNum = pageNum;
        this.length = length;
        this.total = total;
        calculateStartAndEndRow();
    }
    
    public Page(int pageNum, int length){
    	this.pageNum = pageNum;
        this.length = length;
        this.total = 0;
        calculateStartAndEndRow();
    }

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
        if (length > 0) {
            pages = (int) (total / length + ((total % length == 0) ? 0 : 1));
        } else {
            pages = 0;
        }
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getAsc() {
		return asc;
	}

	public void setAsc(String asc) {
		this.asc = asc;
	}
}
