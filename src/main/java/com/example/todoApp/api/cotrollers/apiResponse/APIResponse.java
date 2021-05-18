package com.example.todoApp.api.cotrollers.apiResponse;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Builder
@Data
public class APIResponse {
    @NonNull
    private boolean isSuccessful;

    @NonNull
    private String message;
    private Object responseDTO;

}
