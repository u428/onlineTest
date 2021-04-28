package com.omad.lee.damo.Service;

import com.omad.lee.damo.Model.LoginModel.UserDetailsRequestModel;
import com.omad.lee.damo.Model.Req.UserReq;
import com.omad.lee.damo.Model.Resp.UserResp;

import java.util.List;

public interface AuthService {

    UserResp save(UserDetailsRequestModel userDetail) throws Exception;

    UserReq findCurrentUserByEmail(String email);

    List<String> getRoles(String email);
}
