package com.acpoker.acpokerapi.model;

import java.util.List;

public class Game {
    private List<Seat> seats;
    private int buttonSeat;
    private String flop;
    private String turn;
    private String river;

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
