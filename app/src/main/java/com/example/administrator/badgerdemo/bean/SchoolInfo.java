package com.example.administrator.badgerdemo.bean;

/**
 * @description:  
 * @author:  toy
 * @time:  2017/12/15 21:52
 */
public class SchoolInfo {


    /**
     * ID : A492C252-65F3-4D1B-B2DF-D0CE9F812D22
     * Title : 雅礼国际早教
     * Address : null
     * MainUser : 刘先生
     * LinkUser : 刘先生
     * LinkMobile : 15800000000
     * Type : 全日制幼儿园
     */

    private String ID;
    private String Title;
    private String Address;
    private String MainUser;
    private String LinkUser;
    private String LinkMobile;
    private String Logo;
    private String Type;
    private String Province="";
    private String City="";
    private String Contents="";
    private String WorkMobile="";

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getContents() {
        return Contents;
    }

    public void setContents(String contents) {
        Contents = contents;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getMainUser() {
        return MainUser;
    }

    public void setMainUser(String MainUser) {
        this.MainUser = MainUser;
    }

    public String getLinkUser() {
        return LinkUser;
    }

    public void setLinkUser(String LinkUser) {
        this.LinkUser = LinkUser;
    }

    public String getLinkMobile() {
        return LinkMobile;
    }

    public void setLinkMobile(String LinkMobile) {
        this.LinkMobile = LinkMobile;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getLogo() {
        return Logo;
    }

    public void setLogo(String logo) {
        Logo = logo;
    }

    public String getWorkMobile() {
        return WorkMobile;
    }

    public void setWorkMobile(String workMobile) {
        WorkMobile = workMobile;
    }
}
