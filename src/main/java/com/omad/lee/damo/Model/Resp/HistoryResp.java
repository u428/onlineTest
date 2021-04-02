package com.omad.lee.damo.Model.Resp;

import com.omad.lee.damo.Model.Entity.Variants;

import java.util.Date;
import java.util.List;

public class HistoryResp {

    private Date datebegin;

    private Date dateend;

    private int trues;

    private List<QuestionResp> questionResps;

    private VariantsResp variantsResp;

    public HistoryResp() {
    }

    public int getTrues() {
        return trues;
    }

    public void setTrues(int trues) {
        this.trues = trues;
    }

    public List<QuestionResp> getQuestionResps() {
        return questionResps;
    }

    public void setQuestionResps(List<QuestionResp> questionResps) {
        this.questionResps = questionResps;
    }

    public VariantsResp getVariantsResp() {
        return variantsResp;
    }

    public void setVariantsResp(VariantsResp variantsResp) {
        this.variantsResp = variantsResp;
    }

    public Date getDatebegin() {
        return datebegin;
    }

    public void setDatebegin(Date datebegin) {
        this.datebegin = datebegin;
    }

    public Date getDateend() {
        return dateend;
    }

    public void setDateend(Date dateend) {
        this.dateend = dateend;
    }
}
