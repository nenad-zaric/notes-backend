package com.anunnakisoftware.notes.notebook;

import com.anunnakisoftware.notes.note.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotebookService {

    private final NotebookRepository repository;
    private final NoteService noteService;

    @Autowired
    public NotebookService(NotebookRepository repository, NoteService noteService) {
        this.repository = repository;
        this.noteService = noteService;
    }

    public List<Notebook> getNotebooks() {
        return repository.findAll();
    }

    public void createNotebook(Notebook notebook) {
        Optional<Notebook> sameTitle = repository.getNotebookByTitle(notebook.getTitle());
        if(sameTitle.isPresent() && sameTitle.get().getUserId() == notebook.getUserId()){
            throw new IllegalStateException("Notebook with same name already exists");
        }
        repository.save(notebook);
    }

    public void deleteNotebook(Long id) {
        Long userId = repository.findById(id).get().getUserId();
        int numberOfNotebooks = repository.getNumberOfNotebooksByUserId(userId);

        if(numberOfNotebooks == 1){
            throw new IllegalStateException("Can't delete last notebook");
        }
        repository.deleteById(id);
    }
    public void deleteNotebookByUserId(Long id){
        noteService.deleteNotesByNotebookId(id);
        repository.deleteNotebookByUserId(id);
    }

    public List<Notebook> getNotebookByUserId(Long userId) {
        return repository.getNotebooksByUserId(userId);
    }
}
