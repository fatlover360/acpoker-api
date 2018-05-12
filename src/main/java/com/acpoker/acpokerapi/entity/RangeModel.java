package com.acpoker.acpokerapi.entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "rangemodel")
public class RangeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "percentage")
    private double percentage;
    @Column(name = "value")
    private String value;
    @Column(name = "kind")
    private String kind;
    @Column(name = "type")
    private String type;
    @Column(name = "position")
    private String position;
    @Column(name = "blind")
    private String blind;
    @Column(name = "gametype")
    private String gameType;
    @Column(name = "color")
    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public String getBlind() {
        return blind;
    }

    public void setBlind(String blind) {
        this.blind = blind;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "RangeModel{" +
                "percentage=" + percentage +
                ", value='" + value + '\'' +
                ", kind=" + kind +
                ", type=" + type +
                '}';
    }
}
