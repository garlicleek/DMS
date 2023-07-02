package com.lihuo.common.vo;

public class Data {
    private String url;
    private String alt;
    private String href;
    private String poster;

    public Data(String url, String alt, String href, String poster) {
        this.url = url;
        this.alt = alt;
        this.href = href;
        this.poster = poster;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
