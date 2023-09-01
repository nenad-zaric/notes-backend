package com.anunnakisoftware.notes.note;


import com.anunnakisoftware.notes.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    private final UserService userService;


    @Autowired
    public NoteService(NoteRepository noteRepository, UserService userService) {
        this.noteRepository = noteRepository;
        this.userService = userService;
    }

    public List<Note> getNotes(){
        return noteRepository.findAll();
    }

    public List<Note> getNotesByUserId(Long notebookId) {
        return noteRepository.getNotesByUserId(notebookId);
    }

    public List<Note> getNotesByUsername(String username){
        Long userId = userService.getIdByUsername(username);
        return noteRepository.getNotesByUserId(userId);
    }
    public void createNote(Note note){
        noteRepository.save(note);
    }

    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }

    public void deleteNotesByUserId(Long id ){
        noteRepository.deleteNotesByUserId(id);
    }
}
