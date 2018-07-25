package com.acpoker.acpokerapi.model;

import java.math.BigDecimal;
import java.util.List;

public class Game {
    private String pokerHouse;
    private List<Seat> seats;
    private int buttonSeat;
    private String flop;
    private String turn;
    private String river;
    private BigDecimal smallBlind;
    private BigDecimal bigBlind;
    private BigDecimal ante;
    private String myHand;
    private String myNick;
    private List<Action> preFlopActions;
    private List<Action> flopActions;
    private List<Action> turnActions;
    private List<Action> riverActions;
    private BigDecimal finalPot;
    private List<String> collectors;

    public BigDecimal getFinalPot() {
        return finalPot;
    }

    public void setFinalPot(BigDecimal finalPot) {
        this.finalPot = finalPot;
    }

    public List<String> getCollectors() {
        return collectors;
    }

    public void setCollectors(List<String> collectors) {
        this.collectors = collectors;
    }

    public String getPokerHouse() {
        return pokerHouse;
    }

    public void setPokerHouse(String pokerHouse) {
        this.pokerHouse = pokerHouse;
    }

    public String getMyNick() {
        return myNick;
    }

    public void setMyNick(String myNick) {
        this.myNick = myNick;
    }

    public BigDecimal getSmallBlind() {
        return smallBlind;
    }

    public void setSmallBlind(BigDecimal smallBlind) {
        this.smallBlind = smallBlind;
    }

    public BigDecimal getBigBlind() {
        return bigBlind;
    }

    public void setBigBlind(BigDecimal bigBlind) {
        this.bigBlind = bigBlind;
    }

    public BigDecimal getAnte() {
        return ante;
    }

    public void setAnte(BigDecimal ante) {
        this.ante = ante;
    }

    public String getMyHand() {
        return myHand;
    }

    public void setMyHand(String myHand) {
        this.myHand = myHand;
    }

    public List<Action> getPreFlopActions() {
        return preFlopActions;
    }

    public void setPreFlopActions(List<Action> preFlopActions) {
        this.preFlopActions = preFlopActions;
    }

    public List<Action> getFlopActions() {
        return flopActions;
    }

    public void setFlopActions(List<Action> flopActions) {
        this.flopActions = flopActions;
    }

    public List<Action> getTurnActions() {
        return turnActions;
    }

    public void setTurnActions(List<Action> turnActions) {
        this.turnActions = turnActions;
    }

    public List<Action> getRiverActions() {
        return riverActions;
    }

    public void setRiverActions(List<Action> riverActions) {
        this.riverActions = riverActions;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public int getButtonSeat() {
        return buttonSeat;
    }

    public void setButtonSeat(int buttonSeat) {
        this.buttonSeat = buttonSeat;
    }

    public String getFlop() {
        return flop;
    }

    public void setFlop(String flop) {
        this.flop = flop;
    }

    public String getTurn() {
        return turn;
    }

    public void setTurn(String turn) {
        this.turn = turn;
    }

    public String getRiver() {
        return river;
    }

    public void setRiver(String river) {
        this.river = river;
    }
}
