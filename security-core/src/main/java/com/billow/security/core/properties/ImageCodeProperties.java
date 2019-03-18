package com.billow.security.core.properties;

/**
 * 图片验证码配置
 *
 * @author liuyongtao
 * @create 2019-03-15 11:20
 */
public class ImageCodeProperties {

    private int length = 4;
    private int width = 67;
    private int height = 23;
    private int expireIn = 30;
    private String urls;

    public int getLength() {
        return length;
    }

    public ImageCodeProperties setLength(int length) {
        this.length = length;
        return this;
    }

    public int getWidth() {
        return width;
    }

    public ImageCodeProperties setWidth(int width) {
        this.width = width;
        return this;
    }

    public int getHeight() {
        return height;
    }

    public ImageCodeProperties setHeight(int height) {
        this.height = height;
        return this;
    }

    public int getExpireIn() {
        return expireIn;
    }

    public ImageCodeProperties setExpireIn(int expireIn) {
        this.expireIn = expireIn;
        return this;
    }

    public String getUrls() {
        return urls;
    }

    public ImageCodeProperties setUrls(String urls) {
        this.urls = urls;
        return this;
    }
}
