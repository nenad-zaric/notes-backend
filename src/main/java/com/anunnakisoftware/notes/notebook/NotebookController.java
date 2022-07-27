package com.anunnakisoftware.notes.notebook;

import com.anunnakisoftware.notes.note.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/notebooks")
public class NotebookController {
    public final NotebookService notebookService;
    public final NoteService noteService;

    @Autowired
    public NotebookController(NotebookService notebookService, NoteService noteService) {
        this.notebookService = notebookService;
        this.noteService = noteService;
    }

    @GetMapping
    public List<Notebook> getNotebooks(){
        return notebookService.getNotebooks();
    }

    @GetMapping(path = "{userId}")
    public List<Notebook> getNotebooksByUserId(@PathVariable Long userId){
        return notebookService.getNotebookByUserId(userId);
    }

    @PostMapping
    public void createNotebook(@RequestBody Notebook notebook){
        notebookService.createNotebook(notebook);
    }

    @DeleteMapping(path = "{notebookId}")
    public void deleteNotebook(@PathVariable("notebookId") Long id){
        noteService.deleteNotesByNotebookId(id);
        notebookService.deleteNotebook(id);
    }
}
