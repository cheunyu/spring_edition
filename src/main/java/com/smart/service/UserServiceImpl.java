package com.smart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.smart.dao.ILoginLogDao;
import com.smart.dao.IUserDao;
import com.smart.domain.LoginLog;
import com.smart.domain.User;

/**
 * @author:cheunyu
 * @date:2018/5/16 20:20
 */
@Service    //将UserServiceImpl标注为一个服务层的Bean
public class UserServiceImpl implements IUserService{

    private IUserDao userDaoImpl;
    private ILoginLogDao loginLogDaoImpl;

    @Autowired
    public void setLoginLogDaoImpl(ILoginLogDao loginLogDaoImpl) {
        this.loginLogDaoImpl = loginLogDaoImpl;
    }

    @Autowired
    public void setUserDaoImpl(IUserDao userDaoImpl) {
        this.userDaoImpl = userDaoImpl;
    }

    public boolean hasMatchUser(String userName, String password) {
        int matchCount = userDaoImpl.getMatchCount(userName, password);
        return matchCount > 0;
    }

    public User findUserByUsername(String userName) {
        return userDaoImpl.findUserByUserName(userName);
    }

    @Transactional
    public void loginSuccess(User user) {
        user.setCredits(5 + user.getCredits());
        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(user.getUserId());
        loginLog.setIp(user.getLastIp());
        loginLog.setLoginDate(user.getLastVisit());
        userDaoImpl.updateLoginInfo(user);
        loginLogDaoImpl.insertLoginLog(loginLog);
    }
}
