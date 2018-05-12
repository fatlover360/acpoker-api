package com.acpoker.acpokerapi.model;

import java.math.BigDecimal;

public class Seat {
    private int number;
    private String user;
    private String cardOne;
    private String cardTwo;
    private BigDecimal chips;

    public String getCardOne() {
        return cardOne;
    }

    public void setCardOne(String cardOne) {
        this.cardOne = cardOne;
    }

    public String getCardTwo() {
        return cardTwo;
    }

    public void setCardTwo(String cardTwo) {
        this.cardTwo = cardTwo;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public BigDecimal getChips() {
        return chips;
    }

    public void setChips(BigDecimal chips) {
        this.chips = chips;
    }
}