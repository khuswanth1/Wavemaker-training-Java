package com.Wm.Todo.pack;

public class usernameandpassword {
    private static String username;
    private String password;

    public static String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.username = email;
    }

}