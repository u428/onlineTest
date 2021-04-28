package com.omad.lee.damo.Controller;


import com.omad.lee.damo.Model.Req.QuestionReq;
import com.omad.lee.damo.Model.Resp.QuestionResp;
import com.omad.lee.damo.Model.Req.UserReq;
import com.omad.lee.damo.Service.QuestionService;
import com.omad.lee.damo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> putUser(@PathVariable String userid, @RequestBody UserReq userReq){
            return ResponseEntity.ok(userService.putUserAndim(userid, userReq));
    }

    @GetMapping(path = "/hello")
    @PreAuthorize("hasAuthority('user:read')")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("hello");
    }

    @DeleteMapping(path = "/user/{userid}")
    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<String> deleteUser(@PathVariable String userid){
           return ResponseEntity.ok(userService.deleteUserAdmin(userid));
    }

    @PostMapping(path = "/question")
    @PreAuthorize("hasAuthority('question:write')")
    public ResponseEntity<QuestionResp> createQuestion(@RequestBody QuestionReq questionReq){
            return ResponseEntity.ok(questionService.createQuestion(questionReq));
    }

    @PutMapping(path = "/question/{questionid}")
    @PreAuthorize("hasAuthority('question:write')")
    public ResponseEntity<QuestionResp> changeQuestion(@PathVariable("questionid") String questionid, @RequestBody QuestionReq questionReq){
            return ResponseEntity.ok(questionService.changeQuestion(questionid, questionReq));
    }


    @GetMapping(path = "/questions")
    @PreAuthorize("hasAuthority('qusetion:read')")
    public ResponseEntity<List<QuestionResp>> getAllQuestions(
            @RequestParam(value = "page", defaultValue = "0") int page){
        return ResponseEntity.ok(questionService.getAll(page));
    }

    @GetMapping(path = "/question/{questionid}")
    @PreAuthorize("hasAuthority('question:read')")
    public ResponseEntity<QuestionResp> getOneQuestion(@PathVariable("questionid") String questionid){
        return ResponseEntity.ok(questionService.getOneQuestion(questionid));
    }

    @DeleteMapping(path = "/question/{questionid}")
    public ResponseEntity<String> deleteQuestion(@PathVariable String questionid){
           return ResponseEntity.ok(questionService.deleteQuestion(questionid));
    }
}
