package com.omad.lee.damo.Service;

import com.omad.lee.damo.Model.Req.QuestionReq;
import com.omad.lee.damo.Model.Resp.FinishTestResp;

import java.util.List;

public interface QuestionService {
    List getAll(int a, int b);

    void changeQuestion(String questionid);

    void deleteQuestion(String questionid);

    void createQuestion(QuestionReq questionReq);

    List<FinishTestResp> finishTest();
}
