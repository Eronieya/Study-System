package yzoo.homeworkproject.domain;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ClassInfo {
    private Integer cuid;
    private Integer classId;
    private String className;
    private String classCreator;
    private Integer checkNum;
    private Date startDate;
    private Date endDate;
    private String joinClassDate;
    private boolean classState;
    private Integer creatorId;
    private Date createDate;

    public Integer getCuid() {
        return cuid;
    }

    public void setCuid(Integer cuid) {
        this.cuid = cuid;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getCheckNum() {
        return checkNum;
    }

    public void setCheckNum(Integer checkNum) {
        this.checkNum = checkNum;
    }

    public String getJoinClassDate() {
        return joinClassDate;
    }

    public void setJoinClassDate(String joinClassDate) {
        this.joinClassDate = joinClassDate;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassCreator() {
        return classCreator;
    }

    public void setClassCreator(String classCreator) {
        this.classCreator = classCreator;
    }

    public boolean isClassState() {
        return classState;
    }

    public void setClassState(boolean classState) {
        this.classState = classState;
    }

    @Override
    public String toString() {
        return "ClassInfo{" +
                "cuid=" + cuid +
                ", classId=" + classId +
                ", checkNum=" + checkNum +
                ", className='" + className + '\'' +
                ", joinClassDate=" + joinClassDate +
                ", classCreator='" + classCreator + '\'' +
                ", classState=" + classState +
                '}';
    }
}
