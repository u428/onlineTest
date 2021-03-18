package com.omad.lee.damo.Service;

import com.omad.lee.damo.Model.DTO.UserDto;
import com.omad.lee.damo.Model.LoginModel.UserDetailsRequestModel;
import com.omad.lee.damo.Model.Resp.UserResp;

public interface AuthService {

    void save(UserDetailsRequestModel userDetail);

    UserResp findCurrentUserByEmail(String email);
}
