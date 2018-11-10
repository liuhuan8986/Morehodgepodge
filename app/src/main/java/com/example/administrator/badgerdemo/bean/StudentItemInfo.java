package com.example.administrator.badgerdemo.bean;

/**
 * 这个类对应登陆信息返回的  ParentItems 字段
 */
public class StudentItemInfo {

    /**
     *  "AwardCount": 0,
     "BirthDate": "",
     "ClassID": "EEEF325C-866F-41B0-895F-FD4DE8209745",
     "ClassTitle": "一(2)班",
     "ID": "0B3941CF-E70D-49E5-A227-27E31C606626",
     "IDNumber": "",
     "IsMember": 1,
     "Logo": "",
     "PunishCount": 0,
     "RealName": "张三",
     "SchoolID": "A492C252-65F3-4D1B-B2DF-D0CE9F812D22",
     "SchoolLogo": "http://ylimgs.test.upcdn.net/files/YL_Schools/1712061136552985667574.jpg!HW300",
     "SchoolTitle": "名校平台_测试",
     "Sex": "男",
     "ValidEndDate": ""
     */

    private int AwardCount;
    private String BirthDate;
    private String ClassID;
    private String ClassTitle;
    private String ID;
    private String IDNumber;
    private int IsMember;
    private String Logo;
    private String PunishCount;
    private String RealName;
    private String SchoolID;
    private String SchoolLogo;
    private String SchoolTitle;
    private String Sex;
    private String ValidEndDate;

    public int getAwardCount() {
        return AwardCount;
    }

    public void setAwardCount(int awardCount) {
        AwardCount = awardCount;
    }

    public String getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(String birthDate) {
        BirthDate = birthDate;
    }

    public String getClassID() {
        return ClassID;
    }

    public void setClassID(String classID) {
        ClassID = classID;
    }

    public String getClassTitle() {
        return ClassTitle;
    }

    public void setClassTitle(String classTitle) {
        ClassTitle = classTitle;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getIDNumber() {
        return IDNumber;
    }

    public void setIDNumber(String IDNumber) {
        this.IDNumber = IDNumber;
    }

    public int getIsMember() {
        return IsMember;
    }

    public void setIsMember(int isMember) {
        IsMember = isMember;
    }

    public String getLogo() {
        return Logo;
    }

    public void setLogo(String logo) {
        Logo = logo;
    }

    public String getPunishCount() {
        return PunishCount;
    }

    public void setPunishCount(String punishCount) {
        PunishCount = punishCount;
    }

    public String getRealName() {
        return RealName;
    }

    public void setRealName(String realName) {
        RealName = realName;
    }

    public String getSchoolID() {
        return SchoolID;
    }

    public void setSchoolID(String schoolID) {
        SchoolID = schoolID;
    }

    public String getSchoolLogo() {
        return SchoolLogo;
    }

    public void setSchoolLogo(String schoolLogo) {
        SchoolLogo = schoolLogo;
    }

    public String getSchoolTitle() {
        return SchoolTitle;
    }

    public void setSchoolTitle(String schoolTitle) {
        SchoolTitle = schoolTitle;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getValidEndDate() {
        return ValidEndDate;
    }

    public void setValidEndDate(String validEndDate) {
        ValidEndDate = validEndDate;
    }
}
