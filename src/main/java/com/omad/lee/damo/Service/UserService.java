package com.omad.lee.damo.Service;

import com.omad.lee.damo.Model.DTO.UserDto;
import com.omad.lee.damo.Model.Resp.QuestionResp;
import com.omad.lee.damo.Model.Req.UserReq;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService extends UserDetailsService {

    public boolean findByEmail(String email);

    @Override
    UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;

    UserDto getUser(String userName);

    String putUserAndim(String userid, UserReq userDetailsRequestModel);

    String deleteUserAdmin(String userid);


    List<QuestionResp> startTest();
}
