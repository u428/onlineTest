package com.omad.lee.damo.Controller;


import com.omad.lee.damo.Model.Req.FinishTest;
import com.omad.lee.damo.Model.Resp.TestingResp;
import com.omad.lee.damo.Model.Resp.QuestionResp;
import com.omad.lee.damo.Security.CurrentUser;
import com.omad.lee.damo.Service.QuestionService;
import com.omad.lee.damo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<QuestionResp>> startTest(){
        return ResponseEntity.ok(userService.startTest());
    }

    @PostMapping(path = "/finishTest")
    public ResponseEntity<TestingResp> finishTest(@RequestBody List<FinishTest> list, @CurrentUser String email){
        return ResponseEntity.ok(questionService.finishTest(list, email));
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
