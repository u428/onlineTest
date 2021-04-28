package com.omad.lee.damo.Service;

import com.omad.lee.damo.Model.Req.QuestionReq;
import com.omad.lee.damo.Model.Req.FinishTest;
import com.omad.lee.damo.Model.Resp.TestingResp;
import com.omad.lee.damo.Model.Resp.QuestionResp;

import java.util.List;

public interface QuestionService {
    List<QuestionResp> getAll(int a);

    QuestionResp changeQuestion(String questionid, QuestionReq questionReq);

    String deleteQuestion(String questionid);

    QuestionResp createQuestion(QuestionReq questionReq);

    TestingResp finishTest(List<FinishTest> list, String email);

    QuestionResp getOneQuestion(String questionid);
}
