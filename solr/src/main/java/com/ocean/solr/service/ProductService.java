package com.ocean.solr.service;

import com.ocean.solr.dao.ProductDao;
import com.ocean.solr.pojo.ResultModel;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商品查询Service
 * <p>
 * Title: ProductService
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: www.itcast.cn
 * </p>
 * 
 * @version 1.0
 */
@Service
public class ProductService {
	
	private static final int PAGE_SIZE = 60;

	@Autowired
	private ProductDao productDao;

	public ResultModel queryProduct(String queryString, String caltalog_name, String price, String sort, Integer page)
			throws Exception {
		// 1、根据参数创建查询对象
		SolrQuery query = new SolrQuery();
		//设置查询条件
		if (null != queryString && !"".equals(queryString)) {
			query.setQuery(queryString);
		} else {
			query.setQuery("*:*");
		}
		//商品分类过滤
		if (null != caltalog_name && !"".equals(caltalog_name)) {
			query.addFilterQuery("product_catalog_name:" + caltalog_name);
		}
		//价格区间过滤
		if (null != price && !"".equals(price)) {
			String[] strings = price.split("-");
			query.addFilterQuery("product_price:["+strings[0]+" TO "+strings[1]+"]");
		}
		//排序条件
		if ("1".equals(sort)) {
			query.setSort("product_price", ORDER.desc);
		} else {
			query.setSort("product_price", ORDER.asc);
		}
		//分页处理
		if (page == null) page = 1;
		query.setStart((page-1) * PAGE_SIZE);
		query.setRows(PAGE_SIZE);
		//默认搜索域
		query.set("df", "product_keywords");
		//设置高亮
		query.setHighlight(true);
		query.addHighlightField("product_name");
		query.setHighlightSimplePre("<em style=\"color:red\">");
		query.setHighlightSimplePost("</em>");
		// 2、调用dao执行查询。
		ResultModel resultModel = productDao.search(query);
		// 3、根据总记录数计算总页数。
		Long recordCount = resultModel.getRecordCount();
		long pageCount = recordCount / PAGE_SIZE;
		if (recordCount % PAGE_SIZE > 0) {
			pageCount++;
		}
		resultModel.setPageCount((int) pageCount);
		resultModel.setCurPage(page);
		//返回结果
		return resultModel;
	}
}
