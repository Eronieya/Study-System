package yzoo.homeworkproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import yzoo.homeworkproject.domain.User;
import yzoo.homeworkproject.domain.UserInfo;
import yzoo.homeworkproject.service.UserService;

import javax.annotation.Resource;

@Controller
public class SystemController {
    @Resource
    UserService userService;

    @RequestMapping("/personalPage")
    public ModelAndView personcalInfo(@SessionAttribute("id") int id){
        ModelAndView mv = new ModelAndView();

        UserInfo userInfo = userService.findUserInfo(id);
        User user = userService.getUserAccount(id);

        mv.addObject("userinfo", userInfo);
        mv.addObject("user", user);

        mv.setViewName("userPage");
        System.out.println(userInfo);

        return mv;
    }
}
