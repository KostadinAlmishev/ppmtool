package io.agileinteligence.ppmtool.exceptions;

public class UsernameAlreadyExistsExcepionResponse {

    private String username;

    public UsernameAlreadyExistsExcepionResponse(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
