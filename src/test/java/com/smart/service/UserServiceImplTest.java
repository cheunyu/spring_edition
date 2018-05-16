package com.smart.service;

import com.smart.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author:cheunyu
 * @date:2018/5/16 20:42
 */
@ContextConfiguration("classpath*:/spring-context.xml") //启动Spring容器
public class UserServiceImplTest extends AbstractTransactionalTestNGSpringContextTests {

    private IUserService userServiceImpl;

    @Autowired
    public void setUserServiceImpl(IUserService userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @Test
    public void hasMatchUser() {
        boolean b1 = userServiceImpl.hasMatchUser("admin", "123456");
        boolean b2 = userServiceImpl.hasMatchUser("admin", "111111");
        assertTrue(b1);
        assertTrue(!b2);
    }

    @Test
    public void findUserByUserName() {
        User user = userServiceImpl.findUserByUsername("admin");
        assertEquals(user.getUserName(), "admin");
    }
}
