package com.example.todoApp.api.repositories;

import com.example.todoApp.api.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepo  extends JpaRepository<Note,Long> {
    Note findNoteByTitle(String title);

}
