package com.anunnakisoftware.notes.security.text;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface TextRepository extends JpaRepository<Text, Long> {

    @Query(value = "SELECT * FROM texts WHERE note_id = ?1", nativeQuery = true)
    List<Text> getTextsByNoteId(Long noteId);

    @Modifying
    @Query(value = "DELETE FROM texts WHERE note_id = ?1", nativeQuery = true)
    void  deleteTextsByNoteId(Long noteId);

    @Query(value = "SELECT * FROM texts", nativeQuery = true)
    List<Text> getTexts();
}

