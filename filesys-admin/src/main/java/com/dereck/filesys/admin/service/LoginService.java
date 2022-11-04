package com.dereck.filesys.admin.service;

import com.dereck.filesys.common.entity.R;
import com.dereck.filesys.common.entity.User;

public interface LoginService {
    R login(User user);

    R register(User user);

}
