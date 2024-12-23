package alter.alter_core;

import alter.alter_core.repository.CompanyRepository;
import alter.alter_core.repository.JpaCompanyRepository;
import alter.alter_core.repository.JpaMemberRepository;
import alter.alter_core.repository.MemberRepository;
import alter.alter_core.service.CompanyService;
import alter.alter_core.service.MemberService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private EntityManager em;

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new JpaMemberRepository(em);
    }

    @Bean
    public CompanyService companyService() {
        return new CompanyService(companyRepository());
    }

    @Bean
    public CompanyRepository companyRepository() {
        return new JpaCompanyRepository(em);
    }
}
