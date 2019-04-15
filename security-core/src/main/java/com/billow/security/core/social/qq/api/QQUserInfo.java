package com.billow.security.core.social.qq.api;

/**
 * qq 返回的用户信息
 *
 * @author LiuYongTao
 * @date 2019/3/22 9:36
 */
public class QQUserInfo {

    /**
     * 返回码
     */
    private String ret;
    /**
     * 如果ret<0，会有相应的错误信息提示，返回数据全部用UTF-8编码。
     */
    private String msg;
    /**
     *
     */
    private String openId;
    /**
     * 不知道什么东西，文档上没写，但是实际api返回里有。
     */
    private String is_lost;
    /**
     * 省(直辖市)
     */
    private String province;
    /**
     * 市(直辖市区)
     */
    private String city;
    /**
     * 出生年月
     */
    private String year;
    /**
     * 用户在QQ空间的昵称。
     */
    private String nickname;
    /**
     * 大小为30×30像素的QQ空间头像URL。
     */
    private String figureurl;
    /**
     * 大小为50×50像素的QQ空间头像URL。
     */
    private String figureurl_1;
    /**
     * 大小为100×100像素的QQ空间头像URL。
     */
    private String figureurl_2;
    /**
     * 大小为40×40像素的QQ头像URL。
     */
    private String figureurl_qq_1;
    /**
     * 大小为100×100像素的QQ头像URL。需要注意，不是所有的用户都拥有QQ的100×100的头像，但40×40像素则是一定会有。
     */
    private String figureurl_qq_2;
    /**
     * 性别。 如果获取不到则默认返回”男”
     */
    private String gender;
    /**
     * 标识用户是否为黄钻用户（0：不是；1：是）。
     */
    private String is_yellow_vip;
    /**
     * 标识用户是否为黄钻用户（0：不是；1：是）
     */
    private String vip;
    /**
     * 黄钻等级
     */
    private String yellow_vip_level;
    /**
     * 黄钻等级
     */
    private String level;
    /**
     * 标识是否为年费黄钻用户（0：不是； 1：是）
     */
    private String is_yellow_year_vip;

    public String getRet() {
        return ret;
    }

    public QQUserInfo setRet(String ret) {
        this.ret = ret;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public QQUserInfo setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public String getOpenId() {
        return openId;
    }

    public QQUserInfo setOpenId(String openId) {
        this.openId = openId;
        return this;
    }

    public String getIs_lost() {
        return is_lost;
    }

    public QQUserInfo setIs_lost(String is_lost) {
        this.is_lost = is_lost;
        return this;
    }

    public String getProvince() {
        return province;
    }

    public QQUserInfo setProvince(String province) {
        this.province = province;
        return this;
    }

    public String getCity() {
        return city;
    }

    public QQUserInfo setCity(String city) {
        this.city = city;
        return this;
    }

    public String getYear() {
        return year;
    }

    public QQUserInfo setYear(String year) {
        this.year = year;
        return this;
    }

    public String getNickname() {
        return nickname;
    }

    public QQUserInfo setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getFigureurl() {
        return figureurl;
    }

    public QQUserInfo setFigureurl(String figureurl) {
        this.figureurl = figureurl;
        return this;
    }

    public String getFigureurl_1() {
        return figureurl_1;
    }

    public QQUserInfo setFigureurl_1(String figureurl_1) {
        this.figureurl_1 = figureurl_1;
        return this;
    }

    public String getFigureurl_2() {
        return figureurl_2;
    }

    public QQUserInfo setFigureurl_2(String figureurl_2) {
        this.figureurl_2 = figureurl_2;
        return this;
    }

    public String getFigureurl_qq_1() {
        return figureurl_qq_1;
    }

    public QQUserInfo setFigureurl_qq_1(String figureurl_qq_1) {
        this.figureurl_qq_1 = figureurl_qq_1;
        return this;
    }

    public String getFigureurl_qq_2() {
        return figureurl_qq_2;
    }

    public QQUserInfo setFigureurl_qq_2(String figureurl_qq_2) {
        this.figureurl_qq_2 = figureurl_qq_2;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public QQUserInfo setGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getIs_yellow_vip() {
        return is_yellow_vip;
    }

    public QQUserInfo setIs_yellow_vip(String is_yellow_vip) {
        this.is_yellow_vip = is_yellow_vip;
        return this;
    }

    public String getVip() {
        return vip;
    }

    public QQUserInfo setVip(String vip) {
        this.vip = vip;
        return this;
    }

    public String getYellow_vip_level() {
        return yellow_vip_level;
    }

    public QQUserInfo setYellow_vip_level(String yellow_vip_level) {
        this.yellow_vip_level = yellow_vip_level;
        return this;
    }

    public String getLevel() {
        return level;
    }

    public QQUserInfo setLevel(String level) {
        this.level = level;
        return this;
    }

    public String getIs_yellow_year_vip() {
        return is_yellow_year_vip;
    }

    public QQUserInfo setIs_yellow_year_vip(String is_yellow_year_vip) {
        this.is_yellow_year_vip = is_yellow_year_vip;
        return this;
    }
}