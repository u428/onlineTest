package com.omad.lee.damo.Model.Resp;

import java.util.List;

public class QuestionResp {

    private String questionid;
    private String question;
    private List<VariantsResp> variantsRespList;

    public QuestionResp() {
    }

    public String getQuestionid() {
        return questionid;
    }

    public void setQuestionid(String questionid) {
        this.questionid = questionid;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<VariantsResp> getVariantsRespList() {
        return variantsRespList;
    }

    public void setVariantsRespList(List<VariantsResp> variantsRespList) {
        this.variantsRespList = variantsRespList;
    }
}
