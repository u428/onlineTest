package com.omad.lee.damo.Service.ServiceImp;


import com.omad.lee.damo.Model.Entity.Priviliges;
import com.omad.lee.damo.Model.Entity.Role;
import com.omad.lee.damo.Model.Entity.UserEntity;
import com.omad.lee.damo.Model.LoginModel.UserDetailsRequestModel;
import com.omad.lee.damo.Model.Req.UserReq;
import com.omad.lee.damo.Model.Responce.ApplicationUserRole;
import com.omad.lee.damo.Model.Responce.Utils;
import com.omad.lee.damo.Repository.PriviligesRepository;
import com.omad.lee.damo.Repository.RoleRepository;
import com.omad.lee.damo.Repository.UserRepository;
import com.omad.lee.damo.Service.AuthService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public RoleRepository roleRepository;

    @Autowired
    private PriviligesRepository priviligesRepository;

    @Autowired
    public BCryptPasswordEncoder encoder;

    @Autowired
    public Utils utils;


    @Override
    public void save(UserDetailsRequestModel userDetail) {
        UserEntity userEntity=new UserEntity();

        BeanUtils.copyProperties(userDetail, userEntity);
        userEntity.setEncryptedPassword(encoder.encode(userDetail.getPassword()));
        Role role= new Role();
        role.setRoleName(ApplicationUserRole.ADMIN.name());
        Set<Priviliges> set=new HashSet<>();
        for (String string: ApplicationUserRole.ADMIN.getGrantedAuthorities()){
            Priviliges priviliges=new Priviliges();
            priviliges.setName(string);
            priviligesRepository.save(priviliges);
            set.add(priviliges);

        }

        System.out.println(ApplicationUserRole.ADMINTRAINEE.getGrantedAuthorities2());
        System.out.println(ApplicationUserRole.ADMINTRAINEE.getGrantedAuthorities());
        role.setPriviliges(set);
        for (Priviliges priviliges: role.getPriviliges()){
            priviliges.setRole(role);
        }
        role.setPriviliges(set);
        userEntity.setUserId(utils.generatedId(30));
        roleRepository.save(role);
        userEntity.setRole(role);
        role.setUserEntity(userEntity);
        userRepository.save(userEntity);
    }

    @Override
    public UserReq findCurrentUserByEmail(String email) {
        UserEntity userEntity = userRepository.findUserByEmail(email);
        UserReq userReq =new UserReq();
        BeanUtils.copyProperties(userEntity, userReq);
        return userReq;
    }
}
