package com.omad.lee.damo.Model.Resp;


import java.util.Date;
import java.util.List;

public class TestingResp {

    private Date datebegin;

    private Date dateend;

    private int trues;

    private List<AddTestingResp> addTestingResp;

    public TestingResp() {
    }

    public List<AddTestingResp> getAddTestingResp() {
        return addTestingResp;
    }

    public void setAddTestingResp(List<AddTestingResp> addTestingResp) {
        this.addTestingResp = addTestingResp;
    }

    public int getTrues() {
        return trues;
    }

    public void setTrues(int trues) {
        this.trues = trues;
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
