package com.omad.lee.damo.Service.ServiceImp;

import com.omad.lee.damo.Model.DTO.UserDto;
import com.omad.lee.damo.Model.Entity.*;
import com.omad.lee.damo.Model.Resp.QuestionResp;
import com.omad.lee.damo.Model.Req.UserReq;
import com.omad.lee.damo.Model.Resp.VariantsResp;
import com.omad.lee.damo.Model.Responce.Utils;
import com.omad.lee.damo.Repository.QuestionRepository;
import com.omad.lee.damo.Repository.UserRepository;
import com.omad.lee.damo.Service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public  Utils utils;

    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public QuestionRepository questionRepository;


    @Override
    public UserDto getUser(String email) {
        UserEntity userEntity=userRepository.findUserByEmail(email);
        if (userEntity==null) throw new UsernameNotFoundException(email);
        UserDto returnValue=new UserDto();
        BeanUtils.copyProperties(userEntity, returnValue);
        return returnValue;
    }

    @Override
    public String putUserAndim(String userid, UserReq userDetailsRequestModel) {
        UserEntity userEntity = findUserbyId(userid);
        userEntity.setFirstName(userDetailsRequestModel.getFirsName());
        userEntity.setLastName(userDetailsRequestModel.getLastName());
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDetailsRequestModel.getPassword()));
        return userRepository.save(userEntity).getUserId();
    }

    @Override
    public String deleteUserAdmin(String userid) {
        UserEntity userEntity = findUserbyId(userid);
        userRepository.delete(userEntity);
        return userid;
    }

    @Override
    public List<QuestionResp> startTest() {
        Pageable pageable= PageRequest.of(0, 30);
        Page<Questions> pages= questionRepository.findAll(pageable);
        List<QuestionResp> list1=new ArrayList<>();
        for (Questions questions: pages.getContent()){
            QuestionResp questionResp=new QuestionResp();
            List<VariantsResp> list2=new ArrayList<>();
            for (Variants variants: questions.getVariants()){
                VariantsResp variantsResp=new VariantsResp();
                BeanUtils.copyProperties(variants, variantsResp);
                list2.add(variantsResp);
            }
            BeanUtils.copyProperties(questions, questionResp);
            questionResp.setVariantsRespList(list2);
            list1.add(questionResp);
        }
        return list1;
    }

    @Override
    public boolean findByEmail(String email) {
        UserEntity userEntity = userRepository.findUserByEmail(email);
        if (userEntity==null){
            return true;
        }
        return false;
    }


    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity=userRepository.findUserByEmail(email);
        if (userEntity==null) throw new UsernameNotFoundException(email);
        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), getAuthority(userEntity));
    }
    private Set<SimpleGrantedAuthority> getAuthority(UserEntity user) {
        Set<SimpleGrantedAuthority> authorities = user.getRole().getPriviliges().stream().map(priviliges ->
                new SimpleGrantedAuthority(priviliges.getName())).collect(Collectors.toSet());
        authorities.add(new SimpleGrantedAuthority("ROLE_"+user.getRole().getRoleName()));
        return authorities;
    }

    public UserEntity findUserbyId(String userid){
        UserEntity userEntity = userRepository.findUserEntityByUserId(userid);
        if (userEntity==null){
            return null;
        }
        return userEntity;
    }
}
