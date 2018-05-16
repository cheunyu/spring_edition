package com.smart.web;

import com.smart.domain.User;
import com.smart.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author:cheunyu
 * @date:2018/5/16 21:20
 */
@Controller //标注成为一个Spring MVC的Controller
public class LoginController {

    private IUserService userServiceImpl;

    @Autowired
    public void setUserServiceImpl(IUserService userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @RequestMapping(value = "/index.html")  //负责处理/index.html的请求
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = "/loginCheck.html") //负责处理/loginCheck.html的请求
    public ModelAndView loginCheck(HttpServletRequest request, LoginCommand loginCommand) {
        boolean isValidUser = userServiceImpl.hasMatchUser(loginCommand.getUserName(), loginCommand.getPassword());
        if (!isValidUser) {
            return new ModelAndView("login", "error", "用户名或密码错误");
        } else {
            User user = userServiceImpl.findUserByUsername(loginCommand.getUserName());
            user.setLastIp(request.getLocalAddr());
            user.setLastVisit(new Date());
            userServiceImpl.loginSuccess(user);
            request.getSession().setAttribute("user", user);
            return new ModelAndView("main");
        }
    }
}
