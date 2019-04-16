package com.billow.security.core.social.wechat.api;

import java.util.Arrays;

/**
 * 微信返回用户信息对象
 *
 * @author liuyongtao
 * @create 2019-04-16 15:47
 */
public class WechatUserInfo {

    /**
     * 普通用户的标识，对当前开发者帐号唯一
     */
    private String openid;
    /**
     * 普通用户昵称
     */
    private String nickname;
    /**
     * 语言
     */
    private String language;
    /**
     * 普通用户性别，1为男性，2为女性
     */
    private String sex;
    /**
     * 普通用户个人资料填写的省份
     */
    private String province;
    /**
     * 普通用户个人资料填写的城市
     */
    private String city;
    /**
     * 国家，如中国为CN
     */
    private String country;
    /**
     * 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空
     */
    private String headimgurl;
    /**
     * 用户特权信息，json数组，如微信沃卡用户为（chinaunicom）
     */
    private String[] privilege;
    /**
     * 用户统一标识。针对一个微信开放平台帐号下的应用，同一用户的unionid是唯一的。
     */
    private String unionid;

    public String getOpenid() {
        return openid;
    }

    public WechatUserInfo setOpenid(String openid) {
        this.openid = openid;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public WechatUserInfo setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getLanguage() {
        return language;
    }

    public WechatUserInfo setLanguage(String language) {
        this.language = language;
        return this;
    }

    public String getSex() {
        return sex;
    }

    public WechatUserInfo setSex(String sex) {
        this.sex = sex;
        return this;
    }

    public String getProvince() {
        return province;
    }

    public WechatUserInfo setProvince(String province) {
        this.province = province;
        return this;
    }

    public String getCity() {
        return city;
    }

    public WechatUserInfo setCity(String city) {
        this.city = city;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public WechatUserInfo setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public WechatUserInfo setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
        return this;
    }

    public String[] getPrivilege() {
        return privilege;
    }

    public WechatUserInfo setPrivilege(String[] privilege) {
        this.privilege = privilege;
        return this;
    }

    public String getUnionid() {
        return unionid;
    }

    public WechatUserInfo setUnionid(String unionid) {
        this.unionid = unionid;
        return this;
    }

    @Override
    public String toString() {
        return "WechatUserInfo{" +
                "openid='" + openid + '\'' +
                ", nickname='" + nickname + '\'' +
                ", language='" + language + '\'' +
                ", sex='" + sex + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", headimgurl='" + headimgurl + '\'' +
                ", privilege=" + Arrays.toString(privilege) +
                ", unionid='" + unionid + '\'' +
                '}';
    }
}
