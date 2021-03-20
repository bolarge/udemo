package com.arc.udemo.api;

public interface IUser<User> {

    String getEmail();

    void setEmail(String email);

    String getPassword();

    void setPassword(String password);
}
