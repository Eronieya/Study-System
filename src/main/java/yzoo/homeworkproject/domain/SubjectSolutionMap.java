package yzoo.homeworkproject.domain;

import org.springframework.stereotype.Component;

@Component
public class SubjectSolutionMap {
    private Integer sid;
    private String solution;


    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    @Override
    public String toString() {
        return "SubjectSolutionMap{" +
                "sid=" + sid +
                ", solution='" + solution + '\'' +
                '}';
    }
}
