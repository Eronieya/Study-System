package yzoo.homeworkproject.service.impl;

import org.springframework.stereotype.Service;
import yzoo.homeworkproject.dao.UserDao;
import yzoo.homeworkproject.domain.User;
import yzoo.homeworkproject.domain.UserInfo;
import yzoo.homeworkproject.service.UserService;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserDao userDao;

    @Override
    public User findUser(User user) {
        // 通过用户名来查找用户
        User result = userDao.findByUsername(user);
        result.setPassword(null); // 不返回密码
        System.out.println(result);
        return result;
    }

    @Override
    public boolean isUser(User user) {
        boolean result = false;

        // 通过用户名来查找用户
        System.out.println(userDao.findByUsername(user));
        if (userDao.findByUsername(user) != null) {
            result = true;
        }
        return result;
    }

    @Override
    public int findUserId(String username) {
        int id = userDao.findUserId(username);
        return id;
    }

    @Override
    public boolean addUser(User user) {
        try {
            //在添加用户的时候，如果添加失败，mybatis会抛出一个异常，我们只需要通过是否有异常抛出，可以判断是否添加成功
            System.out.println(user.getPhonecode());
            System.out.println(user.getEmail());
            System.out.println(user.getUsername());
            System.out.println(user.getPassword());
            userDao.addUser(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public UserInfo findUserInfo(int id) {
        UserInfo userInfo = userDao.findInformationById(id);
        return userInfo;
    }

    @Override
    public User getUserAccount(int id) {
        User user = new User();

        user = userDao.getUserAccount(id);
        user.setId(id);

        return user;
    }
}
