package yzoo.homeworkproject.domain;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserInfo {
    private Integer uid;
    private String uAccount;
    private String uName;
    private String uSex;
    private String uBirthday;
    private String uIdentity;
    private String uSchool;
    private String uMajor;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getuAccount() {
        return uAccount;
    }

    public void setuAccount(String uAccount) {
        this.uAccount = uAccount;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuSex() {
        return uSex;
    }

    public void setuSex(String uSex) {
        this.uSex = uSex;
    }

    public String getuBirthday() {
        return uBirthday;
    }

    public void setuBirthday(String uBirthday) {
        this.uBirthday = uBirthday;
    }

    public String getuIdentity() {
        return uIdentity;
    }

    public void setuIdentity(String uIdentity) {
        this.uIdentity = uIdentity;
    }

    public String getuSchool() {
        return uSchool;
    }

    public void setuSchool(String uSchool) {
        this.uSchool = uSchool;
    }

    public String getuMajor() {
        return uMajor;
    }

    public void setuMajor(String uMajor) {
        this.uMajor = uMajor;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "uid=" + uid +
                ", uAccount='" + uAccount + '\'' +
                ", uName='" + uName + '\'' +
                ", uSex='" + uSex + '\'' +
                ", uBirthday=" + uBirthday +
                ", uIdentity='" + uIdentity + '\'' +
                ", uSchool='" + uSchool + '\'' +
                ", uMajor='" + uMajor + '\'' +
                '}';
    }
}
