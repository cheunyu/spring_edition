package com.smart.service;


import com.smart.domain.User;

public interface IUserService {

    public boolean hasMatchUser(String userName, String password);

    public User findUserByUsername(String userName);

    public void loginSuccess(User user);
}
