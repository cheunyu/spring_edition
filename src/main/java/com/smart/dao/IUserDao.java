package com.smart.dao;


import com.smart.domain.User;

public interface IUserDao {

    /**
     * @description:根据用户名和密码获取匹配的用户数
     * @param:[userName, password]
     * @return:int
     */
    public int getMatchCount(String userName, String password);

    /**
     * @description:根据用户名查询用户
     * @param:[userName]
     * @return:smart.domain.User
     */
    public User findUserByUserName(String userName);

    /**
     * @description:更新用户访问数据
     * @param:[user]
     * @return:void
     */
    public void updateLoginInfo(User user);
}
