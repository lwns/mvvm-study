package com.study.data.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SoftwareList implements Serializable {

    public final static String PREF_READED_SOFTWARE_LIST = "readed_software_list.pref";

    public final static String CATALOG_RECOMMEND = "recommend";
    public final static String CATALOG_TIME = "time";
    public final static String CATALOG_VIEW = "view";
    public final static String CATALOG_LIST_CN = "list_cn";

    private int softwarecount;

    private int pagesize;

    private List<SoftwareDec> softwarelist = new ArrayList<>();

    public int getSoftwarecount() {
        return softwarecount;
    }

    public void setSoftwarecount(int softwarecount) {
        this.softwarecount = softwarecount;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public List<SoftwareDec> getSoftwarelist() {
        return softwarelist;
    }

    public void setSoftwarelist(List<SoftwareDec> softwarelist) {
        this.softwarelist = softwarelist;
    }

    public List<SoftwareDec> getList() {
        return softwarelist;
    }
}
