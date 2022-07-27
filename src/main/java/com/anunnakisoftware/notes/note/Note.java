package com.anunnakisoftware.notes.note;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "notes")
public class Note {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Long notebookId;
    private String title;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate created;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate lastEdit;

    public Note() {
    }

    public Note(Long id, Long notebookId, String title, LocalDate created, LocalDate lastEdit) {
        this.id = id;
        this.notebookId = notebookId;
        this.title = title;
        this.created = created;
        this.lastEdit = lastEdit;
    }

    public Note(Long notebookId, String title, LocalDate created, LocalDate lastEdit) {
        this.notebookId = notebookId;
        this.title = title;
        this.created = created;
        this.lastEdit = lastEdit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "notebook_id")
    public Long getNotebookId() {
        return notebookId;
    }

    public void setNotebookId(Long notebookId) {
        this.notebookId = notebookId;
    }

    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "created", nullable = false)
    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    @Column(name = "last_edit", nullable = false)
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
                ", notebookId=" + notebookId +
                ", title='" + title + '\'' +
                ", created=" + created +
                ", lastEdit=" + lastEdit +
                '}';
    }
}
