package com.study.data.bean.request;

/**
 * User: tangpeng.yang
 * Date: 25/07/2018
 * Description:
 * FIXME
 */
public class PageBeanRequest {

  private String catalog;
  private String pageToken;

  public PageBeanRequest(String catalog, String pageToken) {
    this.catalog = catalog;
    this.pageToken = pageToken;
  }

  public String getCatalog() {
    return catalog;
  }

  public void setCatalog(String catalog) {
    this.catalog = catalog;
  }

  public String getPageToken() {
    return pageToken;
  }

  public void setPageToken(String pageToken) {
    this.pageToken = pageToken;
  }
}
