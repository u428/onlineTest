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
    public UserRepository userRepository;

    @Autowired
    public RoleRepository roleRepository;

    @Autowired
    public BCryptPasswordEncoder encoder;

    @Autowired
    public Utils utils;


    @Override
    public void save(UserDetailsRequestModel userDetail) {
        UserEntity userEntity=new UserEntity();

        BeanUtils.copyProperties(userDetail, userEntity);
        userEntity.setEncryptedPassword(encoder.encode(userDetail.getPassword()));
        List<Role> list =new ArrayList<>();
        Role role=new Role();
        role.setRoleName(String.valueOf(StatusMode.USER_ROLE));
        list.add(role);
        userEntity.setRoles(list);
        userEntity.setUserId(utils.generatedId(30));
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
