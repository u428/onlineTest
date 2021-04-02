package com.omad.lee.damo.Service;

import com.omad.lee.damo.Model.Req.QuestionReq;
import com.omad.lee.damo.Model.Req.FinishTest;
import com.omad.lee.damo.Model.Resp.HistoryResp;
import com.omad.lee.damo.Model.Resp.QuestionResp;

import java.util.List;

public interface QuestionService {
    List<QuestionResp> getAll(int a);

    void changeQuestion(String questionid, QuestionReq questionReq);

    void deleteQuestion(String questionid);

    void createQuestion(QuestionReq questionReq);

    HistoryResp finishTest(List<FinishTest> list, String email);

    QuestionResp getOneQuestion(String questionid);
}
