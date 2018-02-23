package com.acpoker.acpokerapi.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "uid")
    private String uid;
    @Column(name = "date")
    private Date date;
    @Column(name = "cash")
   /* @OneToMany
    private List<Cash> cash;
    @Column(name = "cashout")
    @OneToMany
    private List<Cashout> cashout;
    @Column(name = "deposit")
    @OneToMany
    private List<Deposit> deposit;
*/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

 /*  public List<Cash> getCash() {
        return cash;
    }

    public void setCash(List<Cash> cash) {
        this.cash = cash;
    }

    public List<Cashout> getCashout() {
        return cashout;
    }

    public void setCashout(List<Cashout> cashout) {
        this.cashout = cashout;
    }

    public List<Deposit> getDeposit() {
        return deposit;
    }

    public void setDeposit(List<Deposit> deposit) {
        this.deposit = deposit;
    }*/
}
