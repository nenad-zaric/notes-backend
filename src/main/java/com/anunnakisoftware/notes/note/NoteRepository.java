package com.anunnakisoftware.notes.note;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface NoteRepository extends JpaRepository<Note,Long> {
    @Query(value = "SELECT * FROM notes WHERE user_id = ?1", nativeQuery = true)
    List<Note> getNotesByUserId(Long id);

    @Modifying
    @Query(value = "DELETE FROM notes WHERE user_id = ?1", nativeQuery = true)
    void deleteNotesByUserId(Long id);


}
