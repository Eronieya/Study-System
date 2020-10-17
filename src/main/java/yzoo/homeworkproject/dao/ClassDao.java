package yzoo.homeworkproject.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import yzoo.homeworkproject.domain.ClassInfo;

import java.util.List;

@Repository
@Mapper
public interface ClassDao {
    @Select("select * from classinfo where cuid = ${id}")
    @Results(id="info", value = {
            @Result(property = "cuid", column = "cuid"),
            @Result(property = "classId", column = "classid"),
            @Result(property = "className", column = "classname"),
            @Result(property = "joinClassDate", column = "joinclassdate")
    })
    List<ClassInfo> findClassByUid(@Param("id") int id);

    @Select("select classid from classinfo where cuid = ${id}")
    List<Integer> findClassId(@Param("id") int id); //

    @Select(" select cuid, allclass.classid, allclass.classname, classcreator, checknum, end, start, joinclassdate, classstate" +
            " from allclassinfo, allclass, classinfo where " +
            "allclass.classid = classinfo.classid and " +
            "allclass.classid = id and " +
            "cuid = #{id}")
    @Results(id="userClass", value = {
            @Result(property = "cuid", column = "cuid"),
            @Result(property = "classId", column = "classid"),
            @Result(property = "className", column = "classname"),
            @Result(property = "classCreator", column = "classcreator"),
            @Result(property = "checkNum", column = "checknum"),
            @Result(property = "startDate", column = "start"),
            @Result(property = "endDate", column = "end"),
            @Result(property = "joinClassDate", column = "joinclassdate"),
            @Result(property = "classState", column = "classstate")
    })
    List<ClassInfo> findUserClassById(@Param("id") int id); // 将个人页面所需的课程信息查询出来
}
