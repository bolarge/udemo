package com.arc.udemo.service;

import com.arc.udemo.domain.users.User;

public interface UserContext {

    User getCurrentUser();
    void setCurrentUser(final User user);
}
