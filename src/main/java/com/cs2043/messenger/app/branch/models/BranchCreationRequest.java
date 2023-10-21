package com.cs2043.messenger.app.branch.models;

public record BranchCreationRequest(
        Long companyId,
        String name,
        String address
) {
}
