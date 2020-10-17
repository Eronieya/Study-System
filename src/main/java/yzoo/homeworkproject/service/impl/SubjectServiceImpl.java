package yzoo.homeworkproject.service.impl;

import org.springframework.stereotype.Service;
import yzoo.homeworkproject.dao.SubjectDao;
import yzoo.homeworkproject.domain.Subject;
import yzoo.homeworkproject.domain.SubjectSolutionMap;
import yzoo.homeworkproject.domain.WrongSubjectInfo;
import yzoo.homeworkproject.service.SubjectService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Resource
    SubjectDao subjectDao;

    @Override
    public List<Subject> findAllItems(String subjectName) {
        // 对题目所有题目进行查询
        List<Subject> subjects = null;
        subjects = subjectDao.findAll(subjectName+"subject");
        return subjects;
    }

    /*
    * @func checkSolution(String subjectName, List<SubjectSolutionMap> subjectSolutionMap) 对用户答案进行对改并且返回错题集
    * @param subjectName  数据库中以 j2eesubject j2eemap形式建表, name-subject name-map形式 所以只需要穿前缀就可了
    * @param subjectSolutionMap 用户的答案集合
    * @return 返回错题集
    * */
    @Override
    public List<WrongSubjectInfo> checkSolution(String subjectName, List<SubjectSolutionMap> subjectSolutionMap) {
        // 主要批改试卷答案并且返回错题集
        // List<Subject> subjects = null;
        // List<Integer> wrongSubjects = new ArrayList<Integer>();  // 用于错题id集的存储
        List<WrongSubjectInfo> list = new ArrayList<WrongSubjectInfo>();
        WrongSubjectInfo wrongSubjectInfo = new WrongSubjectInfo();

        // 循环检查答案并且找出错题id 处理错题信息 并且带着用户选择答案,正确答案返回
        for (SubjectSolutionMap subject : subjectSolutionMap) {
            if (subjectDao.checkIsCorrect(subjectName+"map", subject) == null) {
                // wrongSubjects.add(subject.getSid());  // 向错题id集添加新对象
                System.out.println("Service:"+subject);
                wrongSubjectInfo.setSubject( subjectDao.findOneById(subjectName+"subject", subject.getSid())); // 查询题目信息
                wrongSubjectInfo.setUserChoice( subject.getSolution());  // 存储用户所选
                wrongSubjectInfo.setRightChoice( subjectDao.findSolutionById(subjectName+"map", subject.getSid()));  // 存储正确答案
                list.add(wrongSubjectInfo);
                wrongSubjectInfo = new WrongSubjectInfo();  // 此处必须重新创建一个对象,否则会列表中上一个结果会被覆盖 列表里存储的是对象的地址,若不新创建一个对象,则一直添加同一个对象进列表中
            }
        }
        // 根据错题的id返回错题信息
        // subjects = subjectDao.findById(subjectName+"subject", wrongSubjects);
        return list;
    }

    /*
    *   @param subjectSolutionMap 结果集
    *   @param eachScore 每道题的分数
    *   @return 返回所得分数
    * */
    @Override
    public int calcScore(String subjectName, List<SubjectSolutionMap> subjectSolutionMap, int eachScore) {
        int result = 0;
        for (SubjectSolutionMap subject : subjectSolutionMap) {
            if (subjectDao.checkIsCorrect(subjectName + "map", subject) != null) {
                result += eachScore;
            }
        }
        return result;
    }
}
