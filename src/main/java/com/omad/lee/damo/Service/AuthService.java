package com.omad.lee.damo.Service;

import com.omad.lee.damo.Model.LoginModel.UserDetailsRequestModel;
import com.omad.lee.damo.Model.Req.UserReq;

public interface AuthService {

    void save(UserDetailsRequestModel userDetail);

    UserReq findCurrentUserByEmail(String email);
}
