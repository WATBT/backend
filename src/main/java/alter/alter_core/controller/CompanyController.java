package alter.alter_core.controller;

import alter.alter_core.domain.Company;
import alter.alter_core.dto.CompanyDTO;
import alter.alter_core.dto.ResultDTO;
import alter.alter_core.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/company")
public class CompanyController {
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello Company");
    }

    @GetMapping("/list-all")
    public ResponseEntity listCompany() {
        // Service 호출
        List<Company> companyList = companyService.listCompanies();
        return ResponseEntity.ok(ResultDTO.success(200,"Company lists all", companyList));
    }

    @GetMapping("/find/name")
    public ResponseEntity findCompanyByName(@RequestParam String name) {
        List<Company> companyList = companyService.findByCompanyName(name);

        if (!companyList.isEmpty()) {
            return ResponseEntity.ok(ResultDTO.success(200,"find company by company name", companyList));
        } else {
            String message = String.format("There's no company include %s", name);
            return ResponseEntity.ok(ResultDTO.success(200,message, null));
        }
    }

    @GetMapping("/find/company-no")
    public ResponseEntity findCompanyByCompanyNo(@RequestParam String companyNo) {
        Optional<Company> company = companyService.findByCompanyNo(companyNo);

        if (company.isPresent()) {
            return ResponseEntity.ok(ResultDTO.success(200, "find company by company id", company));
        } else {
            return ResponseEntity.ok(ResultDTO.success(200, "no company by company number", null));
        }
    }

    @PostMapping("/join")
    public ResponseEntity createCompany(@RequestBody CompanyDTO companyDTO) {
        System.out.println("Received CompanyDTO: " + companyDTO.toString());

        // DTO -> Entity 변환
        Company company = new Company();
        company.setName(companyDTO.getName());
        company.setCompanyNo(companyDTO.getCompanyNo());
        company.setPhoneNumber(companyDTO.getPhoneNumber());

        // Service 호출
        Company createdCompany = companyService.createCompany(company);

        return ResponseEntity.ok(ResultDTO.success(201,"Company create successfully", createdCompany));
    }
}
