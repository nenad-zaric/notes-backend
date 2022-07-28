package com.anunnakisoftware.notes.notebook;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface NotebookRepository extends JpaRepository<Notebook,Long> {
    @Query(value = "SELECT * FROM notebooks n WHERE n.user_id = ?1", nativeQuery = true)
    List<Notebook> getNotebooksByUserId(Long userId);

    @Modifying
    @Query(value = "DELETE FROM notebooks WHERE user_id = ?1", nativeQuery = true)
    void deleteNotebooksByUserId(Long userId);


    @Query(value = "SELECT COUNT(*) FROM notebooks WHERE user_id = ?1", nativeQuery = true)
    int getNumberOfNotebooksByUserId(Long userId);

    @Query(value = "SELECT * FROM notebooks WHERE title = ?1", nativeQuery = true)
    Optional<Notebook> getNotebookByTitle(String title);

}
