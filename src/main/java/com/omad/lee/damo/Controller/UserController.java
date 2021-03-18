package com.omad.lee.damo.Controller;


import com.omad.lee.damo.Model.Resp.FinishTestResp;
import com.omad.lee.damo.Model.Resp.QuestionResp;
import com.omad.lee.damo.Service.QuestionService;
import com.omad.lee.damo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    QuestionService questionService;


    @GetMapping(path = "/startTest")
    public List<QuestionResp> startTest(){
        return userService.startTest();
    }

    @PostMapping(path = "/finishTest")
    public List<FinishTestResp> finishTest(){
        return questionService.finishTest();
    }






//
//    @GetMapping(produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
//    public List<UserRest> getUsers(@RequestParam(value = "page", defaultValue = "0") int page,
//                                   @RequestParam(value = "limit", defaultValue = "25") int limit){
//
//        List<UserRest> returnValue=new ArrayList<>();
//        List<UserDto> users=userService.getUsers(page, limit);
//        for (UserDto userDto: users){
//            UserRest userRest=new UserRest();
//            BeanUtils.copyProperties(userDto , userRest);
//            returnValue.add(userRest);
//        }
//        return returnValue;
//
//    }
//
//
//    @GetMapping(path="/{userId}/addresses/",
//            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
//    public List<AddressRest> getUserAddresses(@PathVariable String userId){
//        ModelMapper modelMapper=new ModelMapper();
//        List<AddressRest> returnValue= new ArrayList<>();
//        List<GameUserDTO> addressesDTO=addressService.getAddresses(userId);
//        if (addressesDTO!=null&&!addressesDTO.isEmpty()) {
//            Type listType = new TypeToken<List<AddressRest>>() {
//            }.getType();
//            returnValue.add(modelMapper.map(addressesDTO, listType));
//        }
//
//        return returnValue;
//    }
//
//    @GetMapping(path="/{userId}/addresses/{addressId}",
//            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
//    public AddressRest getUserAddress(@PathVariable String addressId){
//        GameUserDTO gameUSerDTO =addressService.getAddress(addressId);
//
//        ModelMapper modelMapper=new ModelMapper();
//
//        return modelMapper.map(gameUSerDTO, AddressRest.class);
//    }

}
