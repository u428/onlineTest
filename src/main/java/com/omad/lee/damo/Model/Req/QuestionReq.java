package com.omad.lee.damo.Model.Req;

import java.util.List;

public class QuestionReq {

    private String question;

    private List<VariantsReq> variantsReqs;

    public QuestionReq() {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<VariantsReq> getVariantsReqs() {
        return variantsReqs;
    }

    public void setVariantsReqs(List<VariantsReq> variantsReqs) {
        this.variantsReqs = variantsReqs;
    }
}
