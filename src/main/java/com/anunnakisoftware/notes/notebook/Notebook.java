package com.anunnakisoftware.notes.notebook;

import javax.persistence.*;

@Entity
@Table(name = "notebooks")
public class Notebook {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Id;

    private String title;
    private Long userId;
    private String colorCode;

    public Notebook() {
    }

    public Notebook(Long id, String title, Long userId, String colorCode) {
        Id = id;
        this.title = title;
        this.userId = userId;
        this.colorCode = colorCode;
    }

    public Notebook(String title, Long userId, String colorCode) {
        this.title = title;
        this.userId = userId;
        this.colorCode = colorCode;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "user_id")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Column(name = "color_code")
    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }
    @Override
    public String toString() {
        return "Notebook{" +
                "Id=" + Id +
                ", title='" + title + '\'' +
                ", userId=" + userId +
                '}';
    }


}
