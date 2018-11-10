package com.example.administrator.badgerdemo.bean;

/**
 * 这个类对应 登陆信息返回的 TeacherItems 字段
 */
public class ClassItemInfo {
    /**
     * "ClassID": "CCC12D65-02E3-4FBB-B5BA-DC64D7BA17DC",
     "ClassTitle": "二(1)班",
     "SchoolID": "A492C252-65F3-4D1B-B2DF-D0CE9F812D22",
     "SchoolLogo": "http://ylimgs.test.upcdn.net/files/YL_Schools/1712061136552985667574.jpg!HW300",
     "SchoolTitle": "名校平台_测试"
              "SendAlbum": 1,
                "SendClassNotice": 1,
                "SendExam": 1,
                "SendGradeNotice": 0,
                "SendHonor": 1,
                "SendTask": 1,
                "SendWeibo": 1
                "AcceptNotice":1
     */
    private String ClassID;
    private String ClassTitle;
    private String SchoolID;
    private String SchoolLogo;
    private String SchoolTitle;

    private int SendAlbum;//能否添加相册
    private int SendClassNotice;//能否发送班级通知
    private int SendGradeNotice;//能否发送年级通知
    private int SendExam;//成绩权限,不知道在手机端有什么用
    private int SendHonor;//能否奖励或惩罚
    private int SendTask;//能否发布作业
    private int SendWeibo;//能否发布班级动态
    private int AcceptNotice;//能否接收通知
    private int SendChecking;//能否处理考勤
    private int GradeChecking;//能否查看考勤管理

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

    public int getSendAlbum() {
        return SendAlbum;
    }

    public void setSendAlbum(int sendAlbum) {
        SendAlbum = sendAlbum;
    }

    public int getSendClassNotice() {
        return SendClassNotice;
    }

    public void setSendClassNotice(int sendClassNotice) {
        SendClassNotice = sendClassNotice;
    }

    public int getSendGradeNotice() {
        return SendGradeNotice;
    }

    public void setSendGradeNotice(int sendGradeNotice) {
        SendGradeNotice = sendGradeNotice;
    }

    public int getSendExam() {
        return SendExam;
    }

    public void setSendExam(int sendExam) {
        SendExam = sendExam;
    }

    public int getSendHonor() {
        return SendHonor;
    }

    public void setSendHonor(int sendHonor) {
        SendHonor = sendHonor;
    }

    public int getSendTask() {
        return SendTask;
    }

    public void setSendTask(int sendTask) {
        SendTask = sendTask;
    }

    public int getSendWeibo() {
        return SendWeibo;
    }

    public void setSendWeibo(int sendWeibo) {
        SendWeibo = sendWeibo;
    }

    public int getAcceptNotice() {
        return AcceptNotice;
    }

    public void setAcceptNotice(int acceptNotice) {
        AcceptNotice = acceptNotice;
    }

    public int getSendChecking() {
        return SendChecking;
    }

    public void setSendChecking(int sendChecking) {
        SendChecking = sendChecking;
    }

    public int getGradeChecking() {
        return GradeChecking;
    }

    public void setGradeChecking(int gradeChecking) {
        GradeChecking = gradeChecking;
    }
}
