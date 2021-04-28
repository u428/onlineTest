package com.omad.lee.damo.Model.Resp;

import java.util.List;

public class AddTestingResp {

    private QuestionResp questionResps;

    private VariantsResp variantsResp;

    private boolean trueOrNot;

    public AddTestingResp() {
    }

    public QuestionResp getQuestionResps() {
        return questionResps;
    }

    public void setQuestionResps(QuestionResp questionResps) {
        this.questionResps = questionResps;
    }

    public VariantsResp getVariantsResp() {
        return variantsResp;
    }

    public void setVariantsResp(VariantsResp variantsResp) {
        this.variantsResp = variantsResp;
    }

    public boolean isTrueOrNot() {
        return trueOrNot;
    }

    public void setTrueOrNot(boolean trueOrNot) {
        this.trueOrNot = trueOrNot;
    }
}
