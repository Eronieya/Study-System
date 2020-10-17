package yzoo.homeworkproject.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import yzoo.homeworkproject.domain.Subject;
import yzoo.homeworkproject.domain.SubjectSolutionMap;
import yzoo.homeworkproject.domain.WrongSubjectInfo;
import yzoo.homeworkproject.service.SubjectService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SubjectController {
    @Resource
    SubjectService subjectService;

    @RequestMapping("/exam")
    public ModelAndView getPaper(String subjectName){
        System.out.println(subjectName);
        ModelAndView mv = new ModelAndView();
        List<Subject> list = subjectService.findAllItems(subjectName);
        for (Subject sj : list) {
            System.out.println(sj);
        }
        mv.addObject("subjectName", subjectName);
        mv.addObject("subject", list);
        mv.setViewName("exm");
        return mv;
    }

    @RequestMapping(value="/check")
    public ModelAndView getResult(@RequestParam String solutionMaps) {
        ModelAndView mv = new ModelAndView();
        System.out.println("/check");
        System.out.println(solutionMaps);

        SubjectSolutionMap solutionMap = new SubjectSolutionMap();
        List<SubjectSolutionMap> list = new ArrayList<>();

        // json字符串中提取数据
        int len = 0; // 存储字符串长度 用于筛选字符串最后一个字符
        solutionMaps = solutionMaps.replaceAll("\"", "");      // 双引号则需要转义字符 "\""
        solutionMaps = solutionMaps.replace("[", "");
        solutionMaps = solutionMaps.replace("]","");
        String[] result = solutionMaps.split(",");
        String tmp = null;
        for (int i = 0; i<result.length; i++) {
            // 去除字符串中{}
            result[i] = result[i].replace("{","");
            result[i] = result[i].replace("}", "");
            // 获取字符串最后一个字符
            len = result[i].length()-1;
            // 判断是否为字母, 若是则为答案 进行存储 在里面进行getter setter 在 if else 后 add进 list 中
            if(result[i].charAt(len) >= 'A' && result[i].charAt(len) <= 'Z') {
                System.out.println("solution="+result[i].charAt(len));
                solutionMap.setSolution(String.valueOf(result[i].charAt(len))); // 要以String类型进行传递
                list.add(solutionMap); // 添加
                solutionMap = new SubjectSolutionMap(); //初始化
            } else {
                // 当题号为两位数时的处理
                if(result[i].charAt(len-1) != ':'){
                    tmp = result[i].substring(len-1,len+1);
                    solutionMap.setSid(Integer.parseInt(tmp)); // 要以Int类型进行传递参数
                } else {
                    System.out.println("sid="+result[i].charAt(len));
                    // 当题号仅有一位数时
                    solutionMap.setSid(Integer.parseInt(String.valueOf(result[i].charAt(len))));
                }
            }
        }

        List<WrongSubjectInfo> wrongList = subjectService.checkSolution("j2ee", list);
        for (WrongSubjectInfo wrongSubjectInfo: wrongList) {
            System.out.println(wrongSubjectInfo);
        }

        int score = subjectService.calcScore("j2ee", list, 2);
        System.out.println("所得分数为:"+score);

        mv.addObject("score", score);
        mv.addObject("wrongSubject", wrongList);
        mv.setViewName("results");
        return mv;
    }

    @RequestMapping("/index")
    public ModelAndView backToIndex() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("myclass");
        return mv;
    }
}
