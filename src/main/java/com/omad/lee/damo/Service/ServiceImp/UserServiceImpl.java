package com.omad.lee.damo.Service.ServiceImp;

import com.omad.lee.damo.Model.DTO.UserDto;
import com.omad.lee.damo.Model.Entity.Questions;
import com.omad.lee.damo.Model.Entity.UserEntity;
import com.omad.lee.damo.Model.Entity.Variants;
import com.omad.lee.damo.Model.Resp.QuestionResp;
import com.omad.lee.damo.Model.Resp.UserResp;
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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

//    @Override
//    public UserDto createUser(UserDto user) {
//        if (userRepository.findUserByEmail(user.getEmail())!=null)throw new RuntimeException("Record Already Exists");
//
//
//
//        List<Game_User> list=new ArrayList<>();
//        for (int i = 0; i < user.getAdresses().size(); i++) {
//            Game_User addressEntity=new Game_User();
//            BeanUtils.copyProperties(user.getAdresses().get(i), addressEntity);
//            list.add(addressEntity);
//        }
//
//
//        UserEntity userEntity=new UserEntity();
////        BeanUtils.copyProperties(user, userEntity);
//        ModelMapper modelMapper=new ModelMapper(); //berdanam yaxshi ishlamadi bi ModelMapper
//        userEntity=modelMapper.map(user, UserEntity.class);
//        String publicUserId=utils.generatedId(30);
//        userEntity.setUserId(publicUserId);
//        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        userEntity.setEmailVerificationStatus(false); // Bini ozim false atdim bolmasa ishlamadi
//        userEntity.setStatus(StatusMode.USER_ROLE);
//        UserEntity storedUserDetails =userRepository.save(userEntity);
//
//        UserDto returnValue=modelMapper.map(storedUserDetails, UserDto.class);
//
//        return returnValue;
//    }
//
    @Override
    public UserDto getUser(String email) {
        UserEntity userEntity=userRepository.findUserByEmail(email);
        if (userEntity==null) throw new UsernameNotFoundException(email);
        UserDto returnValue=new UserDto();
        BeanUtils.copyProperties(userEntity, returnValue);
        return returnValue;
    }

    @Override
    public void putUserAndim(String userid, UserResp userDetailsRequestModel) {
        UserEntity userEntity = findUserbyId(userid);
        userEntity.setFirstName(userDetailsRequestModel.getFirsName());
        userEntity.setLastName(userDetailsRequestModel.getLastName());
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDetailsRequestModel.getPassword()));
        userRepository.save(userEntity);
    }

    @Override
    public void deleteUserAdmin(String userid) {
        UserEntity userEntity = findUserbyId(userid);
        userRepository.delete(userEntity);
    }

    @Override
    public List<QuestionResp> startTest() {
        Pageable pageable= PageRequest.of(0, 12);
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
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity=userRepository.findUserByEmail(email);
        if (userEntity==null) throw new UsernameNotFoundException(email);
        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), getAuthority(userEntity));
    }

    private List<SimpleGrantedAuthority> getAuthority(UserEntity user) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            System.out.println(new SimpleGrantedAuthority(role.getRoleName()));
        });
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
