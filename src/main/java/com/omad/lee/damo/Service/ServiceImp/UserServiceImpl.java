package com.omad.lee.damo.Service.ServiceImp;

import com.omad.lee.damo.Model.DTO.UserDto;
import com.omad.lee.damo.Model.Entity.UserEntity;
import com.omad.lee.damo.Model.Responce.Utils;
import com.omad.lee.damo.Repository.UserRepository;
import com.omad.lee.damo.Service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Utils utils;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

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
//
//    @Override
//    public UserDto getUserbyUserId(String userId) {
//        UserEntity userEntity=userRepository.findUserEntityByUserId(userId);
//        if (userEntity==null) throw new UsernameNotFoundException(userId);
//        UserDto returnValue=new UserDto();
//        BeanUtils.copyProperties(userEntity, returnValue);
//        return returnValue;
//    }
//
//    @Override
//    public UserDto updateUser(String userId, UserDto userDto) {
//        UserEntity userEntity=userRepository.findUserEntityByUserId(userId);
//        if (userEntity==null) throw new UserServiceException(ErrorMessage.NO_RECORD_FOUND.getErrorMessages());
//        UserDto returnValue=new UserDto();
//
//        userEntity.setFirstName(userDto.getFirstName());
//        userEntity.setLastName(userDto.getLastName());
//
//        UserEntity userEntity1=userRepository.save(userEntity);
//        BeanUtils.copyProperties(userEntity1, returnValue);
//        return returnValue;
//    }
//
//    @Override
//    public void deleteUser(String userId) {
//        UserEntity userEntity=userRepository.findUserEntityByUserId(userId);
//        if (userEntity==null) throw new UserServiceException(ErrorMessage.NO_RECORD_FOUND.getErrorMessages());
//
//        userRepository.delete(userEntity);
//    }
//
//    @Override
//    public List<UserDto> getUsers(int page, int limit) {
//        if (page>0) --page;
//        List<UserDto> returnValue= new ArrayList<>();
//        Pageable pageable= PageRequest.of(page, limit);
//        Page<UserEntity> page1= userRepository.findAll(pageable);
//        List<UserEntity> list=page1.getContent();
//
//        for (UserEntity userEntity : list) {
//            UserDto userDto=new UserDto();
//            BeanUtils.copyProperties(userEntity, userDto);
//            returnValue.add(userDto);
//        }
//        return returnValue;
//    }

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
        });
        return authorities;
    }
}
