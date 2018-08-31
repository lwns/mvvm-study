package com.study.data.bean;

/**
 * 软件列表
 *
 * @author FireAnt（http://my.oschina.net/LittleDY）
 * @created 2015年1月20日 下午3:34:52
 */
public class SoftwareDec extends Entity {
    private String name;
    private String description;
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
