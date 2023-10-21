package com.cs2043.messenger.app.user;

import com.cs2043.messenger.app.branch.BranchRepository;
import com.cs2043.messenger.app.branch.models.Branch;
import com.cs2043.messenger.app.user.models.User;
import com.cs2043.messenger.app.user.models.UserCreationRequest;
import com.cs2043.messenger.app.user.models.UserLoginRequest;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public record UserService(
        UserRepository userRepository,
        BranchRepository branchRepository
) {

    public void createUser(UserCreationRequest userCreationRequest) {
        Optional<User> existingUser = userRepository.findByEmail(userCreationRequest.email());
        if(existingUser.isPresent()) {
            throw new EntityExistsException("A user with this email address already exists");
        }

        User user = User.builder().
                name(userCreationRequest.name()).
                email(userCreationRequest.email()).
                password(userCreationRequest.password()).
                phoneNumber(userCreationRequest.phoneNumber()).
                address(userCreationRequest.address()).
                jobTitle(userCreationRequest.jobTitle()).
                profilePhoto(userCreationRequest.profilePhoto()).
                dateOfBirth(userCreationRequest.dateOfBirth()).
                build();


        userRepository.save(user);
    }

    public Optional<User> loginUser(UserLoginRequest userLoginRequest) {
        User user = userRepository.findByEmail(userLoginRequest.email()).orElseThrow(() -> new EntityNotFoundException("A user with this email address does not exists"));
        boolean detailsMatch = Objects.equals(userLoginRequest.email(), user.getEmail()) && Objects.equals(userLoginRequest.password(), user.getPassword());

        if(!detailsMatch) {
            return Optional.empty();
        }

        return Optional.of(user);
    }

    public User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new EntityNotFoundException("The requested user with id: " + userId + " does not exist"));
    }

    public void updateUser(
            Long userId,
            String address,
            String jobTitle,
            String profilePhoto,
            Long branchId
    ) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new EntityNotFoundException("Branch not found"));

        Optional.ofNullable(address).ifPresent(user::setAddress);
        Optional.ofNullable(jobTitle).ifPresent(user::setJobTitle);
        Optional.ofNullable(profilePhoto).ifPresent(user::setProfilePhoto);
        user.setBranch(branch);

        userRepository.save(user);
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
