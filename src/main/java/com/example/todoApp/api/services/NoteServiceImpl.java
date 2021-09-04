package com.example.todoApp.api.services;

import com.example.todoApp.api.cotrollers.dto.NoteDTO;
import com.example.todoApp.api.exceptions.NoteException;
import com.example.todoApp.api.models.Note;
import com.example.todoApp.api.repositories.NoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepo noteRepo;

    @Autowired
    public NoteServiceImpl(NoteRepo noteRepo) {
        this.noteRepo = noteRepo;
    }

    @Override
    public void createANewNote(NoteDTO noteDTO) {
        Note createdNote = NoteDTO.unPackNoteDTO(noteDTO);
        saveNote(createdNote);
    }

    @Override
    public void deleteById(Long id) throws NoteException {
        Note savedNote = findNoteById(id);
        deleteNote(savedNote);
    }

    @Override
    @Transactional
    public void updateByTitle(Long id, String title) throws NoteException {
        Note noteToBeUpdated = findNoteById(id);
        if (Objects.equals(noteToBeUpdated.getId(), id) &&
                !Objects.equals(noteToBeUpdated.getTitle(), title)) {
            noteToBeUpdated.setTitle(title);
            saveNote(noteToBeUpdated);
        } else if (Objects.equals(noteToBeUpdated.getTitle(), title)) {
            throw new NoteException("Note Already Exist");
        } else throw new NoteException("Note Does NOt Exist");
    }

    @Override
    public void updateANote(Long id, NoteDTO noteDTO) throws NoteException {
        Note noteToBeUpdated = findNoteById(id);
        noteToBeUpdated = NoteDTO.unPackNoteDTO(noteDTO);
        saveNote(noteToBeUpdated);
    }

    @Override
    public Note findNoteById(Long id) throws NoteException {
        Optional<Note> foundNote = noteRepo.findById(id);
        if (foundNote.isPresent())
            return foundNote.get();
        else {
            throw new NoteException("Note NOT Found");
        }
    }

    @Override
    public List<NoteDTO> getAllNotes() {
        List<NoteDTO> allNotesDTO = new ArrayList<>();
        for (Note note : getNotes()) {
            allNotesDTO.add(NoteDTO.packNoteDTO(note));
        }
        return allNotesDTO;
    }

    @Override
    public List<Note> findNotesInDescOrder() {
        return noteRepo.findByOrderByDateCreatedDesc();
    }

    private List<Note> getNotes() {
        return noteRepo.findAll();
    }

    private void saveNote(Note note) {
        noteRepo.save(note);
    }

    private void deleteNote(Note note) {
        noteRepo.delete(note);
    }
}
