package com.omad.lee.damo.Service.ServiceImp;


import com.omad.lee.damo.Enams.StatusMode;
import com.omad.lee.damo.Model.Entity.Role;
import com.omad.lee.damo.Model.Entity.UserEntity;
import com.omad.lee.damo.Model.LoginModel.UserDetailsRequestModel;
import com.omad.lee.damo.Model.Resp.UserResp;
import com.omad.lee.damo.Model.Responce.Utils;
import com.omad.lee.damo.Repository.RoleRepository;
import com.omad.lee.damo.Repository.UserRepository;
import com.omad.lee.damo.Service.AuthService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected RoleRepository roleRepository;

    @Autowired
    BCryptPasswordEncoder encoder;


    @Override
    public void save(UserDetailsRequestModel userDetail) {
        UserEntity userEntity=new UserEntity();

        BeanUtils.copyProperties(userDetail, userEntity);
        userEntity.setEncryptedPassword(encoder.encode(userDetail.getPassword()));
        List<Role> list =new ArrayList<>();
        if (userDetail.getRole()==3){
            list.add(roleRepository.findRolebyName(StatusMode.ADMIN_ROLE));
            list.add(roleRepository.findRolebyName(StatusMode.MENEGER_ROLE));
            list.add(roleRepository.findRolebyName(StatusMode.USER_ROLE));
        }else if(userDetail.getRole()==2){
            list.add(roleRepository.findRolebyName(StatusMode.MENEGER_ROLE));
            list.add(roleRepository.findRolebyName(StatusMode.USER_ROLE));
        }else {
            list.add(roleRepository.findRolebyName(StatusMode.USER_ROLE));
        }
        userEntity.setRoles(list);
    }

    @Override
    public UserResp findCurrentUserByEmail(String email) {
        UserEntity userEntity = userRepository.findUserByEmail(email);
        UserResp userResp=new UserResp();
        BeanUtils.copyProperties(userEntity, userResp);
        return userResp;
    }
}
