package com.anunnakisoftware.notes.note;


import com.anunnakisoftware.notes.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/notes")
public class NoteController {

    private final NoteService noteService;
    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;

    }

    @GetMapping
    public List<Note> getNotes(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getPrincipal());
        String username = (String) authentication.getPrincipal();
        // You can access userDetails.getUsername() to get the username from the JWT.
        return noteService.getNotesByUsername(username);
    }

    @PostMapping
    public void createNote(@RequestBody Note note){
        noteService.createNote(note);
    }

    @DeleteMapping(path = "{noteId}")
    public void deleteNote(Long id){
        noteService.deleteNote(id);
    }
}
