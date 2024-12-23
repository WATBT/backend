package alter.alter_core.controller;

import alter.alter_core.domain.Company;
import alter.alter_core.dto.CompanyDTO;
import alter.alter_core.dto.ResultDTO;
import alter.alter_core.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/company")
public class CompanyController {
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/join")
    public ResponseEntity<ResultDTO<Company>> createCompany(@RequestBody CompanyDTO companyDTO) {
        // DTO -> Entity 변환
        Company company = new Company();
        company.setName(companyDTO.getName());
        company.setCompanyNo(companyDTO.getCompanyNo());
        company.setPhoneNumber(companyDTO.getPhoneNumber());

        // Service 호출
        Company createdCompany = companyService.createCompany(company);

        return ResponseEntity.ok(ResultDTO.success(createdCompany));

    }
}
