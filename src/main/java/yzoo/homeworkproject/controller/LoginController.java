package yzoo.homeworkproject.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import yzoo.homeworkproject.domain.User;
import yzoo.homeworkproject.service.UserService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@SessionAttributes(names = {"id"})
public class LoginController {
    @Resource
    UserService userService;

    @RequestMapping("/login")
    public ModelAndView checkUser(@ModelAttribute("username") String username,
                                  @ModelAttribute("password") String password){
        // 使用了thymeleaf之后，可以直接访问服务器static里面的网页
        // 而templates文件夹里面的页面通过controller来进行跳转，不能直接访问
//        System.out.println("进入服务器");
//        System.out.println(username+password);
        ModelAndView mv =  new ModelAndView();

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        if (userService.isUser(user)) {
            mv.addObject("id", userService.findUserId(user.getUsername()));
            mv.setViewName("userClass");
        } else {
            mv.addObject("reason", "输入用户名或密码不正确");
            mv.setViewName("../static/login");
        }
        return mv;
    }

    @RequestMapping("/sign")
    public ModelAndView addUser(User user) {
        System.out.println("进入sign");
//        System.out.println(user);
//        System.out.println("添加用户");
        ModelAndView mv = new ModelAndView();
        if (userService.addUser(user)){
            System.out.println("跳转success");
            mv.setViewName("success");
        } else {
            System.out.println("跳转defeat");
            mv.setViewName("defeat");
        }
        return mv;
    }

    // Ajax异步请求判断账号密码
    @ResponseBody
    @RequestMapping("/checkpassword")
    public Map userCheck(@RequestParam("username") String username, String password) {
        System.out.println(username + password);
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        Map<String, String> map = new HashMap<>();
        if (userService.isUser(user)) {
            map.put("message", "true");
        } else {
            map.put("message", "false");
            map.put("reason", "用户名或密码错误!");
        }
        return map;
    }


    // Ajax异步请求测试
    // @ResponseBody 注解
    @ResponseBody
    @RequestMapping("/test")
    public Map test(String aaa) {
        System.out.println(aaa);
        Map<String, String> map = new HashMap<>();
        map.put("message", aaa);
        return map;
    }
}
