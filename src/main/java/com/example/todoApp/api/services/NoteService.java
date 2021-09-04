package com.example.todoApp.api.services;

import com.example.todoApp.api.cotrollers.dto.NoteDTO;
import com.example.todoApp.api.exceptions.NoteException;
import com.example.todoApp.api.models.Note;

import java.util.List;

public interface NoteService {
    void createANewNote(NoteDTO noteDTO);

    void deleteById(Long id) throws NoteException;

    Note findNoteById(Long id) throws NoteException;

    void updateByTitle(Long id, String title) throws NoteException;

    void updateANote(Long id, NoteDTO noteDTO) throws NoteException;

    List<Note> findNotesInDescOrder();

    List<NoteDTO> getAllNotes();
}
