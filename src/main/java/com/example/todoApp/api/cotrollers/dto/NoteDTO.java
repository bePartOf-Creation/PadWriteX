package com.example.todoApp.api.cotrollers.dto;

import com.example.todoApp.api.exceptions.NoteException;
import com.example.todoApp.api.models.Note;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;

@Builder
@Data
@AllArgsConstructor
public class NoteDTO {


    private String title;


    private String noteBody;


    private LocalDateTime dateCreated;


    public static Note unPackNoteDTO(NoteDTO noteDTO) {
        return Note
                .builder()
                .title(noteDTO.getTitle())
                .noteBody(noteDTO.getNoteBody())
                .dateCreated(LocalDateTime.now())
                .build();
    }

    public static NoteDTO packNoteDTO(Note note) {
        return NoteDTO
                .builder()
                .title(note.getTitle())
                .noteBody(note.getNoteBody())
                .dateCreated(note.getDateCreated())
                .build();
    }

    private static void noteDTOIsEmpty(NoteDTO noteDTO) throws NoteException {
        if (Objects.equals(noteDTO, null))
            throw new NoteException("Cannot Creat An Empty Note");
    }
}
