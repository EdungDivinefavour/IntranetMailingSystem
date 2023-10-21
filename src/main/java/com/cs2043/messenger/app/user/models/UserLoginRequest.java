package com.cs2043.messenger.app.user.models;

public record UserLoginRequest(
        String email,
        String password
) {
}
