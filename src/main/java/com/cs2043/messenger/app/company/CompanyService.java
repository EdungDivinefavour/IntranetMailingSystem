package com.cs2043.messenger.app.company;

import com.cs2043.messenger.app.company.models.Company;
import com.cs2043.messenger.app.company.models.CompanyCreationRequest;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public record CompanyService(
        CompanyRepository companyRepository
) {

    public void createCompany(CompanyCreationRequest companyCreationRequest) {
        Optional<Company> existingCompany = companyRepository.findByName(companyCreationRequest.name());
        if(existingCompany.isPresent()) {
            throw new EntityExistsException("A company with this name already exists");
        }

        Company company = Company.builder().
                name(companyCreationRequest.name()).
                address(companyCreationRequest.address()).
                build();

        companyRepository.save(company);
    }

    public Company getCompany(Long companyId) {
        return companyRepository.findById(companyId).orElseThrow(() ->
                new EntityNotFoundException("The requested company with id: " + companyId + " does not exist"));
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }
}
