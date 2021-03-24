package com.omad.lee.damo.Service;

import com.omad.lee.damo.Model.Req.QuestionReq;
import com.omad.lee.damo.Model.Resp.FinishTestResp;
import com.omad.lee.damo.Model.Resp.QuestionResp;

import java.util.List;

public interface QuestionService {
    List<QuestionResp> getAll(int a);

    void changeQuestion(String questionid);

    void deleteQuestion(String questionid);

    void createQuestion(QuestionReq questionReq);

    List<FinishTestResp> finishTest();
}
