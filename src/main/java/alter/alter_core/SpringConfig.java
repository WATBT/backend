package alter.alter_core;

import alter.alter_core.repository.CompanyRepository;
import alter.alter_core.repository.JpaCompanyRepository;
import alter.alter_core.service.CompanyService;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private EntityManager em;



    @Bean
    public CompanyService companyService() {
        return new CompanyService(companyRepository());
    }

    @Bean
    public CompanyRepository companyRepository() {
        return new JpaCompanyRepository(em);
    }
}
