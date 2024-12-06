package com.example.usersData.model;

public class Tag {
    private String role;
    private String level;

    public Tag(String level, String role) {
        this.level = level;
        this.role = role;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
