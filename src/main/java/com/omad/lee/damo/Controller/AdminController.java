package com.omad.lee.damo.Controller;


import com.omad.lee.damo.Model.LoginModel.UserDetailsRequestModel;
import com.omad.lee.damo.Model.Req.QuestionReq;
import com.omad.lee.damo.Model.Responce.OperationStatusModel;
import com.omad.lee.damo.Service.QuestionService;
import com.omad.lee.damo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    QuestionService questionService;

    @PutMapping(path = "/user/{userid}"
//            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE},
//            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public boolean putUser(@PathVariable String userid, @RequestBody UserDetailsRequestModel userDetailsRequestModel){
        OperationStatusModel model=new OperationStatusModel();
        try{
            userService.putUserAndim(userid, userDetailsRequestModel);
        }catch (Exception e){

        }
        return true;
    }

    @DeleteMapping(path = "/user/{userid}")
    public boolean deleteUser(@PathVariable String userid){
        userService.deleteUserAdmin(userid);
        return true;
    }

    @PostMapping(path = "/question")
    public boolean createQuestion(@RequestBody QuestionReq questionReq){
        questionService.createQuestion(questionReq);
        return true;
    }

    @GetMapping(path = "/questions")
    public List getAllQuestions(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "25") int limit){
        return questionService.getAll(page, limit);
    }

    @PutMapping(path = "/question/{questionid}")
    public boolean putQuestion(@PathVariable String questionid){

        questionService.changeQuestion(questionid);
        return true;
    }

    @DeleteMapping(path = "/question/{questionid}")
    public boolean deleteQuestion(@PathVariable String questionid){
        questionService.deleteQuestion(questionid);
        return true;
    }

}
