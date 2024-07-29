package com.seahere.backend.user.service;

import com.seahere.backend.user.request.CustomerSignupReq;

public interface UserService {
    Long signupCustomer(CustomerSignupReq customerSignupReq);
}
