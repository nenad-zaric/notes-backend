package com.anunnakisoftware.notes.security.text;

import com.anunnakisoftware.notes.component.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "texts")
public class Text extends Component {
    private String content;

    public Text() {
    }

    public Text(Long id, Long noteId, String content) {
        super(id, noteId);
        this.content = content;
    }

    public Text(Long noteId, String content) {
        super(noteId);
        this.content = content;
    }

    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Text{" +
                "content='" + content + '\'' +
                '}';
    }
}
