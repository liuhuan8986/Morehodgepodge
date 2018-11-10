package com.example.administrator.badgerdemo.bean;


import com.example.administrator.badgerdemo.db.convert.ClassItemInfoConvert;
import com.example.administrator.badgerdemo.db.convert.ClassItemInfoListConvert;
import com.example.administrator.badgerdemo.db.convert.SchoolInfoConvert;
import com.example.administrator.badgerdemo.db.convert.SchoolInfoListConvert;
import com.example.administrator.badgerdemo.db.convert.StudentItemInfoConvert;
import com.example.administrator.badgerdemo.db.convert.StudentItemInfoListConvert;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.ArrayList;
import java.util.List;
//@Id 标明主键，括号里可以指定是否自增
//@Property 用于设置属性在数据库中的列名（默认不写就是保持一致）
//@NotNull 非空
//@Transient 标识这个字段是自定义的不会创建到数据库表里
//@Unique 添加唯一约束
//@ToOne 是将自己的一个属性与另一个表建立关联
//@JoinProperty 对于更复杂的关系，可以使用这个注解标明目标属性的源属性。
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class UserInfo {

    /**
     * UserID : 93771A5E-F8D6-4860-9D4C-0755FF47F120
     * RealName : 何爸爸
     * Mobile : 18188497823
     * PersonType : parent|teacher
     * Logo :
     * MailingAddress :
     * UserToken : 874D8243-8F66-40AE-BA1F-0C52187E1977
     * DataArea : A492C252-65F3-4D1B-B2DF-D0CE9F812D22
     * Schools : [{"ID":"A492C252-65F3-4D1B-B2DF-D0CE9F812D22","Title":"雅礼国际早教_测试"}]
     * ParentItems : [{"ID":"b5974b7a-56ee-4714-a267-777a5967bdd9","Logo":"http://ylimgs.test.upcdn.net/images/YL_Students/1712152149395848652835.jpg!HW300","RealName":"何学生A","SchoolID":"A492C252-65F3-4D1B-B2DF-D0CE9F812D22","SchoolTitle":"雅礼国际早教","ClassID":"d090b024-ea06-499a-b4fd-b6e072326197","ClassTitle":"三（1）班","IDNumber":"身份证信息","BirthDate":"2010-01-02","AwardCount":0,"PunishCount":0,"ValidEndDate":"2018-03-30","IsMember":1}]
     * TeacherItems : [{"SchoolID":"A492C252-65F3-4D1B-B2DF-D0CE9F812D22","SchoolTitle":"雅礼国际早教","ClassID":"CCC12D65-02E3-4FBB-B5BA-DC64D7BA17DC","ClassTitle":"一(1)班"},{"SchoolID":"A492C252-65F3-4D1B-B2DF-D0CE9F812D22","SchoolTitle":"雅礼国际早教","ClassID":"DAAEF787-604F-4E8C-8665-8DA3768883AB","ClassTitle":"一(3)班"},{"SchoolID":"A492C252-65F3-4D1B-B2DF-D0CE9F812D22","SchoolTitle":"雅礼国际早教","ClassID":"F78178D6-CE04-415D-AB1D-CC732F9E1453","ClassTitle":"一(2)班"}]
     */
    @Id
    private String UserID;
    private String RealName;
    private String Mobile;
    private String Gender;
    private String PersonType;
    private String Logo;
    private String MailingAddress;
    private String UserToken;
    private String DataArea;
    private String IDNumber;

    @Convert(converter = SchoolInfoListConvert.class,columnType = String.class)
    private List<SchoolInfo> Schools=new ArrayList<>();

    @Convert(converter = StudentItemInfoListConvert.class,columnType = String.class)
    private List<StudentItemInfo> ParentItems = new ArrayList<>();//学生

    @Convert(converter = ClassItemInfoListConvert.class,columnType = String.class)
    private List<ClassItemInfo> TeacherItems = new ArrayList<>();//班级

    //当前选择的学校
    @Convert(converter = SchoolInfoConvert.class,columnType = String.class)
    private SchoolInfo currentSchoolInfo;
    //当前选择的班级
    @Convert(converter = ClassItemInfoConvert.class,columnType = String.class)
    private ClassItemInfo currentClass;
    //当前选择的学生
    @Convert(converter = StudentItemInfoConvert.class,columnType = String.class)
    private StudentItemInfo currentStudent;
    //当前选择的身份
    private String currentUserType;

    @Generated(hash = 1918684957)
    public UserInfo(String UserID, String RealName, String Mobile, String Gender, String PersonType, String Logo, String MailingAddress, String UserToken, String DataArea, String IDNumber, List<SchoolInfo> Schools, List<StudentItemInfo> ParentItems, List<ClassItemInfo> TeacherItems, SchoolInfo currentSchoolInfo, ClassItemInfo currentClass, StudentItemInfo currentStudent, String currentUserType) {
        this.UserID = UserID;
        this.RealName = RealName;
        this.Mobile = Mobile;
        this.Gender = Gender;
        this.PersonType = PersonType;
        this.Logo = Logo;
        this.MailingAddress = MailingAddress;
        this.UserToken = UserToken;
        this.DataArea = DataArea;
        this.IDNumber = IDNumber;
        this.Schools = Schools;
        this.ParentItems = ParentItems;
        this.TeacherItems = TeacherItems;
        this.currentSchoolInfo = currentSchoolInfo;
        this.currentClass = currentClass;
        this.currentStudent = currentStudent;
        this.currentUserType = currentUserType;
    }

    @Generated(hash = 1279772520)
    public UserInfo() {
    }

    public String getIDNumber() {
        return IDNumber;
    }

    public void setIDNumber(String IDNumber) {
        this.IDNumber = IDNumber;
    }


    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }


    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public String getRealName() {
        return RealName;
    }

    public void setRealName(String RealName) {
        this.RealName = RealName;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String Mobile) {
        this.Mobile = Mobile;
    }

    public void setPersonType(String PersonType) {
        this.PersonType = PersonType;
    }

    public String getLogo() {
        return Logo;
    }

    public void setLogo(String Logo) {
        this.Logo = Logo;
    }

    public String getMailingAddress() {
        return MailingAddress;
    }

    public void setMailingAddress(String MailingAddress) {
        this.MailingAddress = MailingAddress;
    }

    public String getUserToken() {
        return UserToken;
    }

    public void setUserToken(String UserToken) {
        this.UserToken = UserToken;
    }

    public String getDataArea() {
        return DataArea;
    }

    public void setDataArea(String DataArea) {
        this.DataArea = DataArea;
    }

    public List<SchoolInfo> getSchools() {
        return Schools;
    }

    public void setSchools(List<SchoolInfo> Schools) {
        this.Schools = Schools;
    }

    public List<StudentItemInfo> getParentItems() {
        return ParentItems;
    }

    public void setParentItems(List<StudentItemInfo> parentItems) {
        ParentItems = parentItems;
    }

    public List<ClassItemInfo> getTeacherItems() {
        return TeacherItems;
    }

    public void setTeacherItems(List<ClassItemInfo> teacherItems) {
        TeacherItems = teacherItems;
    }


    public SchoolInfo getCurrentSchoolInfo() {
        return currentSchoolInfo;
    }

    public void setCurrentSchoolInfo(SchoolInfo currentSchoolInfo) {
        this.currentSchoolInfo = currentSchoolInfo;
    }

    public ClassItemInfo getCurrentClass() {
        return currentClass;
    }

    public void setCurrentClass(ClassItemInfo currentClass) {
        this.currentClass = currentClass;
    }

    public StudentItemInfo getCurrentStudent() {
        return currentStudent;
    }

    public void setCurrentStudent(StudentItemInfo currentStudent) {
        this.currentStudent = currentStudent;
    }

    public String getCurrentUserType() {
        return currentUserType;
    }

    public void setCurrentUserType(String currentUserType) {
        this.currentUserType = currentUserType;
    }

    public String getPersonType() {
        return PersonType;
    }
}
