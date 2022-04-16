package com.example.projectOne.DTO;

public class LoginResponse {
    private boolean isError;
    private String authToken;
    private String message;

    public LoginResponse(boolean isError, String authToken, String message) {
        this.isError = isError;
        this.authToken = authToken;
        this.message = message;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
