package yzoo.homeworkproject.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import yzoo.homeworkproject.domain.Subject;
import yzoo.homeworkproject.domain.SubjectSolutionMap;

import java.util.List;

@Repository
@Mapper
public interface SubjectDao {
    // 查询所有题目   #{subjectName} ==> 'string'    ${subjectName} ==> string
    @Select("select * from ${subjectName}")
    List<Subject> findAll(@Param("subjectName") String subjectName);

    // 通过id集合来寻找题目
    @Select("<script> " +
                "select * from ${subjectName} where id in " +
                "<foreach collection='ids' item='item' open='(' separator=',' close=')'>" +
                "#{item}" +
                "</foreach>" +
            "</script>")
    List<Subject> findById(@Param("subjectName") String subjectName,@Param("ids") List<Integer> ids);

    // 根据题号获取一道题的详细信息
    @Select("select * from ${subjectName} where id = ${id}")
    Subject findOneById(@Param("subjectName") String subjectName, @Param("id") int id);

    // 根据题号id获取正确答案的值
    @Select("select solution from ${subjectName} where sid = ${sid}")
    String findSolutionById(@Param("subjectName") String subjectName, @Param("sid") int sid);

    // 判断题目是否正确 通过id和答案匹配
    @Select("select * from ${subjectName} where sid = #{solutionMap.sid} and solution = #{solutionMap.solution}")
    SubjectSolutionMap checkIsCorrect(@Param("subjectName") String subjectName, SubjectSolutionMap solutionMap);
}
