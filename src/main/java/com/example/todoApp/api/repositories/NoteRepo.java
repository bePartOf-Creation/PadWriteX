package com.example.todoApp.api.repositories;

import com.example.todoApp.api.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepo extends JpaRepository<Note, Long> {
    List<Note> findByOrderByDateCreatedDesc();
    Optional<Note> findById(Long id);




}
