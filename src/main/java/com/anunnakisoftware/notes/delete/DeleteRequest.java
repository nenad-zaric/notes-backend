package com.anunnakisoftware.notes.delete;

public class DeleteRequest {
    private Long id;
    private String password;

    public DeleteRequest(Long id, String password) {
        this.id = id;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
}
