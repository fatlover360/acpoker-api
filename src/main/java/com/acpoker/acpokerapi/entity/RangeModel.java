package com.acpoker.acpokerapi.entity;


import javax.persistence.*;

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
