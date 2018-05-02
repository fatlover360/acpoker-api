package com.acpoker.acpokerapi.model;

import java.math.BigDecimal;

public class Action {
    private BigDecimal betAmount;
    private String userName;
    private ActionType actionType;

    public BigDecimal getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(BigDecimal betAmount) {
        this.betAmount = betAmount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }
}
