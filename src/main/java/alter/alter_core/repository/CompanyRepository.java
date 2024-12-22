package alter.alter_core.repository;

import alter.alter_core.domain.Company;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository {
    Company save(Company company);
    Optional<Company> findById(UUID id);
    Optional<Company> findByCompanyNo(String companyNo);
    List<Company> findAll();
}
