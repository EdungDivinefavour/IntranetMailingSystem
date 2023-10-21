package com.cs2043.messenger.app.friend_request;

import com.cs2043.messenger.app.friend_request.models.FriendRequest;
import com.cs2043.messenger.app.friend_request.models.SendOrAcceptFriendRequestRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("api/v1/friendrequest")
public record FriendRequestController(FriendRequestService friendRequestService) {
    @PostMapping()
    public void sendRequest(@RequestBody SendOrAcceptFriendRequestRequest sendOrAcceptFriendRequestRequest) {
        log.info("wants to send request with body {}", sendOrAcceptFriendRequestRequest);
        friendRequestService.sendRequest(sendOrAcceptFriendRequestRequest);
    }

    @PostMapping(path = "/accept")
    public void acceptRequest(@RequestBody SendOrAcceptFriendRequestRequest sendOrAcceptFriendRequestRequest) {
        log.info("wants to accept request with body {}", sendOrAcceptFriendRequestRequest);
        friendRequestService.acceptRequest(sendOrAcceptFriendRequestRequest);
    }

    @GetMapping(path = "/received/{receiverId}")
    public List<FriendRequest> getAllReceivedRequestsForUser(@PathVariable("receiverId") Long receiverId) {
        log.info("list of friend requests requested");
        return friendRequestService.getAllReceivedRequestsForUser(receiverId);
    }

    @GetMapping(path = "/sent/{senderId}")
    public List<FriendRequest> getAllSentRequestsForUser(@PathVariable("senderId") Long senderId) {
        log.info("list of sent requests requested");
        return friendRequestService.getAllSentRequestsForUser(senderId);
    }
}
