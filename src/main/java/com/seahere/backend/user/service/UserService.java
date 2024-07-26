package com.seahere.backend.user.service;

import com.seahere.backend.user.request.SignupReq;

public interface UserService {
    Long signupUser(SignupReq signupReq);
}
