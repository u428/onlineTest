package com.omad.lee.damo.Controller;


import com.omad.lee.damo.Enams.RequestOperationName;
import com.omad.lee.damo.Enams.RequestOperationStatus;
import com.omad.lee.damo.Model.Req.QuestionReq;
import com.omad.lee.damo.Model.Resp.QuestionResp;
import com.omad.lee.damo.Model.Req.UserReq;
import com.omad.lee.damo.Enams.OperationStatusModel;
import com.omad.lee.damo.Service.QuestionService;
import com.omad.lee.damo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('user:write')")
    public OperationStatusModel putUser(@PathVariable String userid, @RequestBody UserReq userReq){
        OperationStatusModel operationStatusModel =new OperationStatusModel();
        try{
            userService.putUserAndim(userid, userReq);
            operationStatusModel.setOperationName(RequestOperationName.CHANGE.name());
            operationStatusModel.setOperationResult(RequestOperationStatus.SUCCESS.name());
        }catch(Exception e){
            operationStatusModel.setOperationName(RequestOperationName.CHANGE.name());
            operationStatusModel.setOperationResult(RequestOperationStatus.ERROR.name());
        }
        return operationStatusModel;
    }

    @DeleteMapping(path = "/user/{userid}")
    @PreAuthorize("hasAuthority('user:write')")
    public OperationStatusModel deleteUser(@PathVariable String userid){
        OperationStatusModel operationStatusModel =new OperationStatusModel();
        try{
            userService.deleteUserAdmin(userid);
            operationStatusModel.setOperationName(RequestOperationName.DELETE.name());
            operationStatusModel.setOperationResult(RequestOperationStatus.SUCCESS.name());
        }catch(Exception e){
            operationStatusModel.setOperationName(RequestOperationName.DELETE.name());
            operationStatusModel.setOperationResult(RequestOperationStatus.ERROR.name());
        }
        return operationStatusModel;
    }

    @PostMapping(path = "/question")
    @PreAuthorize("hasAuthority('question:write')")
    public OperationStatusModel createQuestion(@RequestBody QuestionReq questionReq){
        OperationStatusModel operationStatusModel =new OperationStatusModel();
        try{
            questionService.createQuestion(questionReq);
            operationStatusModel.setOperationName(RequestOperationName.CREATE.name());
            operationStatusModel.setOperationResult(RequestOperationStatus.SUCCESS.name());
        }catch(Exception e){
            operationStatusModel.setOperationName(RequestOperationName.CREATE.name());
            operationStatusModel.setOperationResult(RequestOperationStatus.ERROR.name());
        }
        return operationStatusModel;
    }

    @PutMapping(path = "/question/questionid")
    @PreAuthorize("hasAuthority('question:write')")
    public OperationStatusModel changeQuestion(@PathVariable String questionid, @RequestBody QuestionReq questionReq){
        OperationStatusModel operationStatusModel =new OperationStatusModel();
        try{
            questionService.changeQuestion(questionid, questionReq);
            operationStatusModel.setOperationName(RequestOperationName.CREATE.name());
            operationStatusModel.setOperationResult(RequestOperationStatus.SUCCESS.name());
        }catch(Exception e){
            operationStatusModel.setOperationName(RequestOperationName.CREATE.name());
            operationStatusModel.setOperationResult(RequestOperationStatus.ERROR.name());
        }
        return operationStatusModel;
    }


    @GetMapping(path = "/questions")
    @PreAuthorize("hasAuthority('qusetion:read')")
    public List<QuestionResp> getAllQuestions(
            @RequestParam(value = "page", defaultValue = "0") int page){
        return questionService.getAll(page);
    }

    @GetMapping(path = "/question/{questionid")
    @PreAuthorize("hasAuthority('question:read')")
    public QuestionResp getOneQuestion(@PathVariable String questionid){
        return questionService.getOneQuestion(questionid);
    }

    @DeleteMapping(path = "/question/{questionid}")
    public OperationStatusModel deleteQuestion(@PathVariable String questionid){
        OperationStatusModel operationStatusModel =new OperationStatusModel();
        try{
            questionService.deleteQuestion(questionid);
            operationStatusModel.setOperationName(RequestOperationName.DELETE.name());
            operationStatusModel.setOperationResult(RequestOperationStatus.SUCCESS.name());
        }catch(Exception e){
            operationStatusModel.setOperationName(RequestOperationName.DELETE.name());
            operationStatusModel.setOperationResult(RequestOperationStatus.ERROR.name());
        }
        return operationStatusModel;
    }
}
