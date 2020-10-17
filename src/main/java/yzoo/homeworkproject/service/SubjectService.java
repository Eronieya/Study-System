package yzoo.homeworkproject.service;

import yzoo.homeworkproject.domain.Subject;
import yzoo.homeworkproject.domain.SubjectSolutionMap;
import yzoo.homeworkproject.domain.WrongSubjectInfo;

import java.util.List;

public interface SubjectService {
    // 不同科目的题库在不同的数据表 因此需要传入一个科目名称
    List<Subject> findAllItems(String subjectName);

    // 传回一个错题集
    List<WrongSubjectInfo> checkSolution(String subjectName, List<SubjectSolutionMap> subjectSolutionMap);

    // 计算分数并返回
    int calcScore(String subjectName, List<SubjectSolutionMap> subjectSolutionMap, int eachScore);
}
