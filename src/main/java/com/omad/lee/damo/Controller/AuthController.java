package com.omad.lee.damo.Controller;


import com.omad.lee.damo.Enams.RequestOperationName;
import com.omad.lee.damo.Enams.RequestOperationStatus;
import com.omad.lee.damo.Model.LoginModel.UserDetailsRequestModel;
import com.omad.lee.damo.Enams.OperationStatusModel;
import com.omad.lee.damo.Model.Req.UserReq;
import com.omad.lee.damo.Model.Resp.UserResp;
import com.omad.lee.damo.Security.CurrentUser;
import com.omad.lee.damo.Service.AuthService;
import com.omad.lee.damo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserService userService;
    @Autowired
    AuthService authService;

    @PostMapping( path = "/signUp"
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserResp> createUser(@RequestBody UserDetailsRequestModel userDetail) throws Exception {
           return ResponseEntity.ok(authService.save(userDetail));
    }


    @GetMapping(path="/checkEmailAvailability"
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Boolean> findEmail(@RequestParam(value = "email") String email){
        return ResponseEntity.ok(userService.findByEmail(email));
    }


    @GetMapping(path ="/getCurrentUser")
    public ResponseEntity<UserReq> getCurrentUser(@CurrentUser String email){
        return ResponseEntity.ok(authService.findCurrentUserByEmail(email));
    }
    @GetMapping
    public ResponseEntity<List<String>> getRoles(@CurrentUser String email){
        return ResponseEntity.ok(authService.getRoles(email));
    }

}
