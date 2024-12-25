package alter.alter_core.service;

import alter.alter_core.domain.Company;
import alter.alter_core.repository.CompanyRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class CompanyService {
    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    // 회사 등록
    public Company createCompany(Company company) {
        /*
            같은 회사 사업자 번호를 가진 회사는 등록 x (company_no 중복 불가)
            회사명은 중복 가능함
            회사명과 사업자 번호는 입력 필수 사항
        */

        try {
            validateCompany(company);
            companyRepository.save(company);

            return company;
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            throw new IllegalStateException("Failed to create company");
        }
    }

    private void validateCompany(Company company) {
        if (company.getCompanyNo() == null || company.getCompanyNo().isEmpty()) {
            throw new IllegalStateException("Company Number is null");
        }

        if (company.getName() == null || company.getName().isEmpty()) {
            throw new IllegalStateException("Company Name is null");
        }

        companyRepository.findByCompanyNo(company.getCompanyNo())
                .ifPresent(existingCompany -> {
                    throw new IllegalStateException("Company already exists");
                });
    }

    // 전체 회사 조회
    public List<Company> listCompanies() {
        return companyRepository.findAll();
    }

    // 회사 ID로 조회
    public Optional<Company> findCompanyById(UUID companyId) {
        return companyRepository.findById(companyId);
    }

    // 회사 사업 번호로 조회 (companyNo)
    public Optional<Company> findByCompanyNo(String companyNo) {
        return companyRepository.findByCompanyNo(companyNo);
    }

    // 회사명으로 조회
    public List<Company> findByCompanyName(String name) {
        return companyRepository.findByCompanyName(name);
    }


}
