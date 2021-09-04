package com.example.todoApp.api.repositories;

import com.example.todoApp.api.models.Note;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class NoteRepoTest {

    @Autowired
    NoteRepo noteRepo;

    @BeforeEach
    void setUp() {
        Note newNote = new Note();
        newNote.setTitle("My Love Note");
        newNote.setNoteBody("I love Jesus Christ and He Loves me Too");

        log.info("Create Note is --> {}", newNote);
    }

    @Test
    void testThatAllNotesDisplayInDescendingOrder() {
        List<Note> allNotes = noteRepo.findByOrderByDateCreatedDesc();
        assertThat(allNotes).isNotNull();

        assertTrue(allNotes.get(1).getDateCreated().isBefore(allNotes.get(0).getDateCreated()));
        allNotes.forEach(note -> {
            log.info("Note Dated --->  {}", note.getDateCreated());
        });
    }
}