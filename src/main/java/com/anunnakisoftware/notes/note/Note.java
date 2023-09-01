package com.anunnakisoftware.notes.note;

import com.anunnakisoftware.notes.user.User;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private String title;
    private String content;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate created;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate lastEdit;

    public Note() {
    }

    public Note(User user, String title, String content, LocalDate created, LocalDate lastEdit) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.created = created;
        this.lastEdit = lastEdit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public LocalDate getLastEdit() {
        return lastEdit;
    }

    public void setLastEdit(LocalDate lastEdit) {
        this.lastEdit = lastEdit;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", user=" + user +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", created=" + created +
                ", lastEdit=" + lastEdit +
                '}';
    }
}
