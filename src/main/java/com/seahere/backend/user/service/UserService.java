package com.seahere.backend.user.service;

import com.seahere.backend.user.controller.response.UserInfoRes;
import com.seahere.backend.user.request.BrokerSignupReq;
import com.seahere.backend.user.request.CeoSignupReq;
import com.seahere.backend.user.request.CustomerSignupReq;
import com.seahere.backend.user.request.OAuthSignupReq;

public interface UserService {
    Long signupCustomer(CustomerSignupReq customerSignupReq);
    Long signupBroker(BrokerSignupReq brokerSignupReq);
    Long signupCeo(CeoSignupReq ceoSignupReq);
    Long signupOauth(OAuthSignupReq oauthSignupReq);
    Boolean validateEmail(String email);
    void approveEmployee(String ceoEmail, String employeeEmail);
    void deleteEmployee(Long userId);
    UserInfoRes getUser(Long userId);
}
