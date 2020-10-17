package yzoo.homeworkproject.service;

import org.springframework.stereotype.Service;
import yzoo.homeworkproject.domain.User;
import yzoo.homeworkproject.domain.UserInfo;

@Service
public interface UserService {
    User findUser(User user);
    boolean addUser(User user);
    boolean isUser(User user);
    UserInfo findUserInfo(int id);
    int findUserId(String username);
    User getUserAccount(int id);
}
