package com.acpoker.acpokerapi.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "range_description")
public class RangeDescription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "content", length = 2000)
    private String content;
    @Column(name = "date")
    private Date date;
    @Column(name = "editDate")
    private Date editDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }
}
