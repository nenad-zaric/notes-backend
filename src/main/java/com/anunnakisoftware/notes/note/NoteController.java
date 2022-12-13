package com.anunnakisoftware.notes.note;


import com.anunnakisoftware.notes.security.text.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/notes")
public class NoteController {

    private final NoteService noteService;
    private final TextService textService;

    @Autowired
    public NoteController(NoteService noteService, TextService textService) {
        this.noteService = noteService;
        this.textService = textService;
    }

    @GetMapping
    public List<Note> getNotes(){
        return noteService.getNotes();
    }

    @GetMapping(path = "{notebookId}")
    public List<Note> getNotesByNotebookId(@PathVariable Long id){
        return noteService.getNotesByNotebookId(id);
    }

    @PostMapping
    public void createNote(@RequestBody Note note){
        noteService.createNote(note);
    }

    @DeleteMapping(path = "{noteId}")
    public void deleteNote(Long id){
        textService.deleteTextsByNoteId(id);
        noteService.deleteNote(id);
    }
}
