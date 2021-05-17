package com.example.todoApp.api.services;

import com.example.todoApp.api.cotrollers.dto.NoteDTO;
import com.example.todoApp.api.exceptions.NoteException;
import com.example.todoApp.api.models.Note;
import com.example.todoApp.api.repositories.NoteRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class NoteServiceImplTest {
    private NoteDTO noteDTO;
    private Note note;

    @Mock
    private NoteRepo noteRepo;

    @InjectMocks
    private NoteServiceImpl noteService;

    @BeforeEach
    void setUp() {
       MockitoAnnotations.openMocks(this);
       noteDTO = NoteDTO
                .builder()
                .title("My First SpringBoot Test")
                .noteBody("On I3th Of May. Mr Ayodele Taught me SpringBoot Testing..")
                .dateCreated(LocalDateTime.now())
                .build();

       note = Note
                .builder()
                .id(1L)
                .title("My First SpringBoot Test")
                .noteBody("On I3th Of May. Mr Ayodele Taught me SpringBoot Testing..")
                .dateCreated(LocalDateTime.now())
                .build();

       List<Note> notes = List.of(note);

       when(noteRepo.save(any())).thenReturn(note);
       when(noteRepo.findAll()).thenReturn(notes);
    }
    @AfterEach
    void tearDown() {
    }
@DisplayName("Implementation To save A Note")
@Test
    void testToSaveANote() {
        try {
            noteService.createANewNote(noteDTO);

            when(noteRepo.findById(any())).thenReturn(java.util.Optional.ofNullable(note));
            Note foundNote = noteService.findNoteById(1L);

            assertEquals(note, foundNote);
        }catch(NoteException noteException){
            System.err.print(noteException.getMessage());
        }
}
@DisplayName("Implementation To Find A Note By Id")
@Test
    void testToFindNoteById() {
        try {
            when(noteRepo.findById(any())).thenReturn(java.util.Optional.ofNullable(note));
            Note foundNote = noteService.findNoteById(1L);
            assertEquals(note,foundNote);
        }catch (NoteException noteException){
            System.err.print(noteException.getMessage());
        }
}
@DisplayName("Implementation to List All Notes")
@Test
    void testToGetALlNotes(){
        noteService.createANewNote(noteDTO);
        List<NoteDTO> allNotes = noteService.getAllNotes();
        assertEquals(1,allNotes.size());
}
@DisplayName("Implementation To Delete A Note By Id")
@Test
    void testToDeleteANoteById() throws NoteException {
        when(noteRepo.findById(any())).thenReturn(java.util.Optional.ofNullable(note));
        noteService.deleteById(1L);
        verify(noteRepo).delete(any());
    //assertEquals(0, noteService.getAllNotes().size());
}
@DisplayName("Implementation To Update A Note By Title")
@Test
    void testToUpdateANoteByTitle() throws NoteException {
        when(noteRepo.findById(any())).thenReturn(java.util.Optional.ofNullable(note));
        noteService.updateByTitle(1L,"My System Modelling");
        assertEquals("My System Modelling",note.getTitle());
}
@DisplayName("Implementation To Update A Note")
@Test
    void testToUpdateANote() throws NoteException {
        when(noteRepo.findById(any())).thenReturn(java.util.Optional.ofNullable(note));
        noteDTO.setNoteBody("My System Modelling");
        noteService.updateANote(1L,noteDTO);
        ArgumentCaptor<Note> noteArgumentCaptor = ArgumentCaptor.forClass(Note.class);
        verify(noteRepo).save(noteArgumentCaptor.capture());
        Note capturedNote = noteArgumentCaptor.getValue();
        assertEquals("My System Modelling", capturedNote.getNoteBody());
    }
}