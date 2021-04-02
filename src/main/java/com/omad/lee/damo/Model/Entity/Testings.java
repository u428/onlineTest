package com.omad.lee.damo.Model.Entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "testing")
public class Testings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private Date datebegin;

    @Column()
    private Date dateend;

    @Column
    private int trues;

    @ManyToOne
    @JoinColumn(name = "history_id", referencedColumnName = "id")
    private History history;

    @OneToMany(mappedBy = "testings", cascade = CascadeType.ALL)
    private List<HistoryList> historyListList;

    public Testings() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public History getHistory() {
        return history;
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

    public int getTrues() {
        return trues;
    }

    public void setTrues(int trues) {
        this.trues = trues;
    }

    public void setHistory(History history) {
        this.history = history;
    }

    public List<HistoryList> getHistoryListList() {
        return historyListList;
    }

    public void setHistoryListList(List<HistoryList> historyListList) {
        this.historyListList = historyListList;
    }
}
