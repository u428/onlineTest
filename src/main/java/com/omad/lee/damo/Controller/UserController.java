package com.omad.lee.damo.Controller;


import com.omad.lee.damo.Model.Req.FinishTest;
import com.omad.lee.damo.Model.Resp.HistoryResp;
import com.omad.lee.damo.Model.Resp.QuestionResp;
import com.omad.lee.damo.Security.CurrentUser;
import com.omad.lee.damo.Service.QuestionService;
import com.omad.lee.damo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping(path = "/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping(path = "/nop")
    public String nop(){
        return "nop";
    }

    @GetMapping(path = "/startTest")
    public List<QuestionResp> startTest(){
        return userService.startTest();
    }

    @PostMapping(path = "/finishTest")
    public HistoryResp finishTest(@RequestBody List<FinishTest> list, @CurrentUser String email){
        return questionService.finishTest(list, email);
    }

    @GetMapping(path = "/histories")
    public String AllHistories(){
        return "historyies";
    }

    @GetMapping(path = "/history/{historyid}")
    public String OneHistory(@PathVariable String historyid){
        return historyid;
    }

}
