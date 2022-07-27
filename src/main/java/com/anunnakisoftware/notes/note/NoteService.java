package com.anunnakisoftware.notes.note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
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
        noteRepository.deleteById(id);
    }

    public void deleteNotesByNotebookId(Long id ){
        System.out.println("Usao u servis klasu");
        noteRepository.deleteNotesByNotebookId(id);
    }
}
