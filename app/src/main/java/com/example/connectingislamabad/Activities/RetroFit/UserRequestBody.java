package com.example.connectingislamabad.Activities.RetroFit;

public class UserRequestBody {

    private User user;

    public UserRequestBody(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
