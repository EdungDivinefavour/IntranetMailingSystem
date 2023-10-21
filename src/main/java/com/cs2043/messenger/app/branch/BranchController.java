package com.cs2043.messenger.app.branch;

import com.cs2043.messenger.app.branch.models.Branch;
import com.cs2043.messenger.app.branch.models.BranchCreationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("api/v1/branch")
public record BranchController(BranchService branchService) {
    @PostMapping
    public void createBranch(@RequestBody BranchCreationRequest branchCreationRequest) {
        log.info("new branch creation {}", branchCreationRequest);
        branchService.createBranch(branchCreationRequest);
    }

    @GetMapping(path = "{id}")
    public Branch getBranch(@PathVariable("id") Long branchId) {
        log.info("branch from id requested {}", branchId);
        return branchService.getBranch(branchId);
    }

    @GetMapping
    public List<Branch> getBranches() {
        log.info("list of users requested");
        return branchService.getAllBranches();
    }
}
