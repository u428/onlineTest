package com.omad.lee.damo.Model.Resp;

import java.util.List;

public class QuestionHisResp {

    private String questionid;
    private String question;
    private List<VariantHisResp> variantHisRespList;

    public QuestionHisResp() {
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

    public List<VariantHisResp> getVariantHisRespList() {
        return variantHisRespList;
    }

    public void setVariantHisRespList(List<VariantHisResp> variantHisRespList) {
        this.variantHisRespList = variantHisRespList;
    }
}
