package alter.alter_core.service;

import alter.alter_core.domain.Company;
import alter.alter_core.domain.Member;
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

        companyRepository.findByCompanyNo(company.getCompanyNo())
                .ifPresent(company1 -> {
                    throw new IllegalStateException("이미 존재하는 기업입니다. 동일한 사업자 번호 기업이 존재하니다.");
                });

        companyRepository.save(company);
        return company;
    }

    // 전체 회사 조회
    public List<Company> findCompanies() {
        return companyRepository.findAll();
    }

    // 회사 ID로 조회
    public Optional<Company> findCompanyById(UUID companyId) {
        return companyRepository.findById(companyId);
    }


}
