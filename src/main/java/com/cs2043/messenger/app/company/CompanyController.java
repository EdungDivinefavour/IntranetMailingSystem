package com.cs2043.messenger.app.company;

import com.cs2043.messenger.app.company.models.Company;
import com.cs2043.messenger.app.company.models.CompanyCreationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("api/v1/company")
public record CompanyController(CompanyService companyService) {
    @PostMapping
    public void createCompany(@RequestBody CompanyCreationRequest companyCreationRequest) {
        log.info("new company creation {}", companyCreationRequest);
        companyService.createCompany(companyCreationRequest);
    }

    @GetMapping(path = "{id}")
    public Company getCompany(@PathVariable("id") Long companyId) {
        log.info("company from id requested {}", companyId);
        return companyService.getCompany(companyId);
    }

    @GetMapping
    public List<Company> getCompanies() {
        log.info("list of companies requested");
        return companyService.getAllCompanies();
    }
}
