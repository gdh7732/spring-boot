package com.ocean.solr.pojo;

import java.util.List;

public class ResultModel {
    // 商品列表
    private List<ProductModel> productList;
    // 商品总数
    private Long recordCount;
    // 总页数
    private int pageCount;
    // 当前页
    private int curPage;

    public List<ProductModel> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductModel> productList) {
        this.productList = productList;
    }

    public Long getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(Long recordCount) {
        this.recordCount = recordCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

}