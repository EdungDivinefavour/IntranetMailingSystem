package com.cs2043.messenger.app.user.models;

import java.time.LocalDateTime;

public record UserCreationRequest(
        String name,
        String email,
        String password,
        String phoneNumber,
        String address,
        String jobTitle,
        String profilePhoto,
        LocalDateTime dateOfBirth
) {
}
