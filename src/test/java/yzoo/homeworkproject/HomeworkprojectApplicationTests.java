package yzoo.homeworkproject;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import yzoo.homeworkproject.dao.ClassDao;
import yzoo.homeworkproject.dao.UserDao;
import yzoo.homeworkproject.domain.*;
import yzoo.homeworkproject.service.SubjectService;
import yzoo.homeworkproject.service.UserService;
import yzoo.homeworkproject.service.impl.UserServiceImpl;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class HomeworkprojectApplicationTests {
    //用@Resource来装配bean
    @Resource
    UserService service;

    @Resource
    SubjectService subjectService;

    // 模拟controller
    @Test
    void contextLoads() {
        User user = new User();
        user.setEmail("771764551@qq.com");
        user.setPassword("147896325a");
        user.setUsername("yzoo");
        user.setPhonecode("15219886793");

        boolean result = service.isUser(user);
        int id = service.findUserId(user.getUsername());
        System.out.println(id);
        System.out.println(result);
    }

    @Test
    void testSubjectFind() {
        // 对题目进行所有的查询    进入考试界面的时候调用
        List<Subject> list = subjectService.findAllItems("j2ee");
        for (Subject sj : list) {
            System.out.println(sj);
        }

        // 模拟赋值    点击提交之后调用此处
        SubjectSolutionMap map1 = new SubjectSolutionMap();SubjectSolutionMap map2 = new SubjectSolutionMap();SubjectSolutionMap map3 = new SubjectSolutionMap();SubjectSolutionMap map4 = new SubjectSolutionMap();SubjectSolutionMap map5 = new SubjectSolutionMap();
        map1.setSid(1);map1.setSolution("D");map2.setSid(2);map2.setSolution("A");map3.setSid(3);map3.setSolution("A");map4.setSid(4);map4.setSolution("C");map5.setSid(5);map5.setSolution("C");

        List<SubjectSolutionMap> answer = new ArrayList<SubjectSolutionMap>();
        answer.add(map1);answer.add(map2);answer.add(map3);answer.add(map4);answer.add(map5);
        List<WrongSubjectInfo> wroingList = subjectService.checkSolution("j2ee", answer);
        for (WrongSubjectInfo sj : wroingList) {
            System.out.println(sj);
        }

        // 计算所得分数
        int score = subjectService.calcScore("j2ee", answer, 2);
        System.out.println("所得分数为:"+score);
    }


    @Test
    void jsonTest(){
        // json字符串中提取数据
        int len = 0; // 存储字符串长度 用于筛选字符串最后一个字符
        String str = "[{'sid':'3','solution':'C'},{'sid':'4','solution':'B'},{'sid':'5','solution':'C'},{'sid':'26','solution':'D'}]";
        str = str.replaceAll("'", "");      // 双引号则需要转义字符 "\""
        str = str.replace("[", "");
        str = str.replace("]","");
        String[] result = str.split(",");
        String tmp = null;

        for (int i = 0; i<result.length; i++) {
            // 去除字符串中{}
            result[i] = result[i].replace("{","");
            result[i] = result[i].replace("}", "");
//            System.out.println(result[i]);
//            System.out.println(result[i].charAt(len));
            // 获取字符串最后一个字符
            len = result[i].length()-1;
            // 判断是否为字母, 若是则为答案 进行存储 在里面进行getter setter 在 if else 后 add进 list 中
            if(result[i].charAt(len) > 'A' && result[i].charAt(len) < 'Z') {
                System.out.println("solution:"+result[i].charAt(len));
            } else {
                // 当题号为两位数时的处理
                if(result[i].charAt(len-1) != ':'){
                    tmp = result[i].substring(len-1,len+1);
                    System.out.println("sid_tmp:"+tmp);
                } else {
                    // 当题号仅有一位数时
                    System.out.println("sid:"+result[i].charAt(len));
                }

            }
        }

//        System.out.println(json);
    }

    @Test
    void addTest() {
        User user = new User();
        user.setEmail("");
        user.setPassword("147896325a");
        user.setUsername("yuanyuan");
        user.setPhonecode("18312880857");

        boolean result = service.addUser(user);

        System.out.println(result);
    }

    @Resource
    ClassDao classDao;

    @Resource
    UserService userService;

    @Test
    void testFindClassInfo() {
        List<ClassInfo> list = new ArrayList<>();
        list = classDao.findUserClassById(1);
        for (ClassInfo info: list ) {
            System.out.println(info);
        }

        UserInfo userInfo = service.findUserInfo(1);
        System.out.println(userInfo);

        User user = userService.getUserAccount(1);
        System.out.println(user);
    }
}
