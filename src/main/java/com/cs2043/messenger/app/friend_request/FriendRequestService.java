package com.cs2043.messenger.app.friend_request;

import com.cs2043.messenger.app.friend_list.FriendListService;
import com.cs2043.messenger.app.friend_list.models.FriendListAddOrRemoveRequest;
import com.cs2043.messenger.app.friend_request.models.FriendRequest;
import com.cs2043.messenger.app.friend_request.models.SendOrAcceptFriendRequestRequest;
import com.cs2043.messenger.app.user.UserRepository;
import com.cs2043.messenger.app.user.models.User;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public record FriendRequestService(
        FriendRequestRepository friendRequestRepository,
        UserRepository userRepository,
        FriendListService friendListService
) {

    public void sendRequest(SendOrAcceptFriendRequestRequest sendOrAcceptFriendRequestRequest) {
        Optional<FriendRequest> existingRequest = friendRequestRepository.getAlreadyExistingRequest(
                sendOrAcceptFriendRequestRequest.senderId(),
                sendOrAcceptFriendRequestRequest.receiverId()
        );
        if(existingRequest.isPresent()) {
            throw new EntityExistsException("A request from this user already exists");
        }

        User sender = userRepository.findById(sendOrAcceptFriendRequestRequest.senderId()).orElseThrow(() -> new EntityNotFoundException("User not found"));
        User receiver = userRepository.findById(sendOrAcceptFriendRequestRequest.receiverId()).orElseThrow(() -> new EntityNotFoundException("User not found"));

        FriendRequest friendRequest = FriendRequest.builder().
                sender(sender).
                receiver(receiver).
                build();

        friendRequestRepository.save(friendRequest);
    }

    public void acceptRequest(SendOrAcceptFriendRequestRequest sendOrAcceptFriendRequestRequest) {
        friendListService.addFriend(new FriendListAddOrRemoveRequest(
                sendOrAcceptFriendRequestRequest.receiverId(),
                sendOrAcceptFriendRequestRequest.senderId()
        ));

        friendListService.addFriend(new FriendListAddOrRemoveRequest(
                sendOrAcceptFriendRequestRequest.senderId(),
                sendOrAcceptFriendRequestRequest.receiverId()
        ));
    }

    public List<FriendRequest> getAllReceivedRequestsForUser(Long receiverId) {
        return friendRequestRepository.findAllByReceiverId(receiverId);
    }

    public List<FriendRequest> getAllSentRequestsForUser(Long senderId) {
        return friendRequestRepository.findAllBySenderId(senderId);
    }
}
