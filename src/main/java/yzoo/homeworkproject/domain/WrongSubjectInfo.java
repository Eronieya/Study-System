package yzoo.homeworkproject.domain;

public class WrongSubjectInfo{
    private String userChoice;
    private String rightChoice;
    private Subject subject;

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getUserChoice() {
        return userChoice;
    }

    public void setUserChoice(String userChoice) {
        this.userChoice = userChoice;
    }

    public String getRightChoice() {
        return rightChoice;
    }

    public void setRightChoice(String rightChoice) {
        this.rightChoice = rightChoice;
    }

    @Override
    public String toString() {
        return "WrongSubjectInfo{" +
                "userChoice='" + userChoice + '\'' +
                ", rightChoice='" + rightChoice + '\'' +
                ", subject=" + subject +
                '}';
    }
}
