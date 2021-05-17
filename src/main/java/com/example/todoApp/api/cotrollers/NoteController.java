package com.example.todoApp.api.cotrollers;

import com.example.todoApp.api.cotrollers.apiResponse.APIResponse;
import com.example.todoApp.api.cotrollers.apiResponse.APIResponseMessage;
import com.example.todoApp.api.cotrollers.dto.NoteDTO;
import com.example.todoApp.api.exceptions.NoteException;
import com.example.todoApp.api.models.Note;
import com.example.todoApp.api.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping("new_Note/")
    public ResponseEntity<?> createANewNote(@RequestBody NoteDTO noteDTO) throws NoteException{
            noteService.createANewNote(noteDTO);
            return new ResponseEntity<>(
                    APIResponse.builder()
                            .isSuccessful(true)
                            .message(APIResponseMessage.NOTE_CREATED_SUCCESSFULLY.getMessage())
                            .responseDTO(noteDTO)
                            .build(),
                    HttpStatus.CREATED
            );
        }
    @GetMapping("search/{id}")
    public ResponseEntity<?> findASavedNoteById(@PathVariable Long id) throws NoteException{
        Note note = noteService.findNoteById(id);
        return new ResponseEntity<>(
                APIResponse.builder()
                    .isSuccessful(true)
                    .message(APIResponseMessage.NOTE_FOUND.getMessage())
                    .responseDTO(NoteDTO.packNoteDTO(note))
                    .build(),
                HttpStatus.FOUND
        );
    }
   @GetMapping("all_notes/")
    public ResponseEntity<?> viewAllNotes(){
         List<NoteDTO> noteDTOList = noteService.getAllNotes();
        return new ResponseEntity<>(
                APIResponse.builder()
                        .isSuccessful(true)
                        .message(APIResponseMessage.ALL_NOTE_FOUND.getMessage())
                        .responseDTO(noteDTOList)
                        .build(),
                HttpStatus.FOUND
        );
   }
   @PatchMapping("{id}/")
   public ResponseEntity<?> updateNoteByTitle(@PathVariable Long id, @RequestBody String title) throws NoteException {
        noteService.updateByTitle(id,title);
        return new ResponseEntity<>(
                APIResponse.builder()
                        .isSuccessful(true)
                        .message(APIResponseMessage.NOTE_TITLE_UPDATED_SUCCESSFULLY.getMessage())
                        .build(),
                HttpStatus.OK);
   }
   @DeleteMapping("{id}/delete_a_note")
    public ResponseEntity<?> deleteANote(@PathVariable Long id) throws NoteException {
        noteService.deleteById(id);
        return new ResponseEntity<>(
                APIResponse.builder()
                        .isSuccessful(true)
                        .message(APIResponseMessage.NOTE_DELETED_SUCCESSFULLY.getMessage())
                        .build(),
                HttpStatus.OK
        );
   }
   @PutMapping("updateNote/{id}")
    public ResponseEntity<?> updateANote(@PathVariable Long id, @RequestBody NoteDTO noteDTO) throws NoteException {
        noteService.updateANote(id,noteDTO);
        return new ResponseEntity<>(
                APIResponse.builder()
                        .isSuccessful(true)
                        .message(APIResponseMessage.NOTE_TITLE_UPDATED_SUCCESSFULLY.getMessage())
                        .responseDTO(noteDTO)
                .build(),
                HttpStatus.CREATED
        );
   }
}
