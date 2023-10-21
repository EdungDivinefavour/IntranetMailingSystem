package com.cs2043.messenger.app.branch;

import com.cs2043.messenger.app.branch.models.Branch;
import com.cs2043.messenger.app.branch.models.BranchCreationRequest;
import com.cs2043.messenger.app.company.CompanyRepository;
import com.cs2043.messenger.app.company.models.Company;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public record BranchService(
        BranchRepository branchRepository,
        CompanyRepository companyRepository
) {

    public void createBranch(BranchCreationRequest branchCreationRequest) {
        Optional<Branch> existingBranch = branchRepository.findByName(branchCreationRequest.name());
        if(existingBranch.isPresent()) {
            throw new EntityExistsException("A branch with this name already exists");
        }

        Company company = companyRepository.findById(branchCreationRequest.companyId()).orElseThrow(() -> new EntityNotFoundException("Company not found"));
        Branch branch = Branch.builder().
                name(branchCreationRequest.name()).
                address(branchCreationRequest.address()).
                company(company).
                build();

        branchRepository.save(branch);
    }

    public Branch getBranch(Long branchId) {
        return branchRepository.findById(branchId).orElseThrow(() ->
                new EntityNotFoundException("The requested branch with id: " + branchId + " does not exist"));
    }

    public List<Branch> getAllBranches() {
        return branchRepository.findAll();
    }
}
