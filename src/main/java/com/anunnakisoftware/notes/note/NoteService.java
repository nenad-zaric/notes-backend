package com.anunnakisoftware.notes.note;

import com.anunnakisoftware.notes.text.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepository;
    private final TextService textService;

    @Autowired
    public NoteService(NoteRepository noteRepository, TextService textService) {
        this.noteRepository = noteRepository;
        this.textService = textService;
    }

    public List<Note> getNotes(){
        return noteRepository.findAll();
    }

    public List<Note> getNotesByNotebookId(Long id){
        return noteRepository.getNotesByNotebookId(id);
    }

    public void createNote(Note note){
        noteRepository.save(note);
    }

    public void deleteNote(Long id) {
        textService.deleteTextsByNoteId(id);
        noteRepository.deleteById(id);
    }

    public void deleteNotesByNotebookId(Long id ){
        List<Note> allNotesByNotebook = noteRepository.getNotesByNotebookId(id);

        for (Note note:allNotesByNotebook) {
            Long noteId = note.getId();
            textService.deleteTextsByNoteId(noteId);
        }
        noteRepository.deleteNotesByNotebookId(id);
    }
}
