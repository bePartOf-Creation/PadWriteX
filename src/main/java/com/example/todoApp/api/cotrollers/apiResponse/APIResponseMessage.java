package com.example.todoApp.api.cotrollers.apiResponse;

import lombok.Getter;

@Getter
public enum APIResponseMessage {

    NOTE_CREATED_SUCCESSFULLY("New Note Created Successfully"),
    NOTE_NOT_CREATED("Cannot Create An Empty Note"),
    NOTE_TITLE_UPDATED_SUCCESSFULLY("Note Title Updated"),
    NOTE_DELETED_SUCCESSFULLY("Note deleted Successfully"),
    NOTE_FOUND("NOTE FOUND"),
    ALL_NOTE_FOUND("ALL NOTE FOUND"),
    NOTE_DOES_NOT_EXIST("Note Not Found");

    private final String message;

    APIResponseMessage(String message) {
        this.message = message;
    }

}
