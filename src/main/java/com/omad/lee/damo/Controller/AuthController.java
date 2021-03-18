package com.omad.lee.damo.Controller;


import com.omad.lee.damo.Enams.RequestOperationName;
import com.omad.lee.damo.Enams.RequestOperationStatus;
import com.omad.lee.damo.Model.LoginModel.UserDetailsRequestModel;
import com.omad.lee.damo.Model.Responce.OperationStatusModel;
import com.omad.lee.damo.Model.Resp.UserResp;
import com.omad.lee.damo.Security.CurrentUser;
import com.omad.lee.damo.Service.AuthService;
import com.omad.lee.damo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Controller
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
    public OperationStatusModel createUser(@RequestBody UserDetailsRequestModel userDetail){
        OperationStatusModel operationStatusModel =new OperationStatusModel();
        try {
           authService.save(userDetail);
            operationStatusModel.setOperationName(RequestOperationName.CREATE.name());
            operationStatusModel.setOperationResult(RequestOperationStatus.SUCCESS.name());
       }catch(Exception e){
            operationStatusModel.setOperationName(RequestOperationName.CREATE.name());
            operationStatusModel.setOperationResult(RequestOperationStatus.ERROR.name());
        }
        return operationStatusModel;
    }


    @GetMapping(path="/checkEmailAvailability"
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public boolean findEmail(@RequestParam(value = "email") String email){
        System.out.println(email);
        boolean returnValue = userService.findByEmail(email);
        return returnValue;
    }


    @GetMapping(path ="/getCurrentUser")
    public UserResp getCurrentUser(@CurrentUser String email){

        return authService.findCurrentUserByEmail(email);

    }

}
