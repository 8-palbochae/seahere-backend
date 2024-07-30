package com.seahere.backend.user.service;

import com.seahere.backend.user.request.BrokerSignupReq;
import com.seahere.backend.user.request.CeoSignupReq;
import com.seahere.backend.user.request.CustomerSignupReq;

public interface UserService {
    Long signupCustomer(CustomerSignupReq customerSignupReq);
    Long signupBroker(BrokerSignupReq brokerSignupReq);

    Long signupCeo(CeoSignupReq ceoSignupReq);
    Boolean validateEmail(String email);
}
