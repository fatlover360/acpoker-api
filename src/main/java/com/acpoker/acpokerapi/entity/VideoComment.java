package com.acpoker.acpokerapi.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "video_comment")
public class VideoComment {

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
    @Column(name = "likes")
    private int likes;
    @Column(name = "uid")
    private String uid;
    @Column(name = "username")
    private String username;
    @ManyToOne
    private Video Video;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public com.acpoker.acpokerapi.entity.Video getVideo() {
        return Video;
    }

    public void setVideo(com.acpoker.acpokerapi.entity.Video video) {
        Video = video;
    }

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

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
