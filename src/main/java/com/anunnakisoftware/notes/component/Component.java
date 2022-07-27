package com.anunnakisoftware.notes.component;


import javax.persistence.*;

@Entity
public class Component {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Long noteId;

    public Component() {
    }

    public Component(Long id, Long noteId) {
        this.id = id;
        this.noteId = noteId;
    }

    public Component(Long noteId) {
        this.noteId = noteId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "note_id")
    public Long getNoteId() {
        return noteId;
    }

    public void setNoteId(Long noteId) {
        this.noteId = noteId;
    }

    @Override
    public String toString() {
        return "Component{" +
                "id=" + id +
                ", noteId=" + noteId +
                '}';
    }
}
