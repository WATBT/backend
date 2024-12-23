package alter.alter_core.repository;

import alter.alter_core.domain.Company;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaCompanyRepository implements CompanyRepository{

    private final EntityManager em;

    public JpaCompanyRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Company save(Company company) {
        em.persist(company);
        return company;
    }

    @Override
    public Optional<Company> findById(UUID id) {
        Company company = em.find(Company.class, id);
        return Optional.ofNullable(company);
    }

    @Override
    public Optional<Company> findByCompanyNo(String companyNo) {
        List<Company> results = em.createQuery("select c from Company c where c.company_no = :company_no", Company.class)
                .setParameter("company_no", companyNo)
                .getResultList();
        return results.stream().findAny();
    }

    @Override
    public List<Company> findAll() {
        return em.createQuery("select c from Company c", Company.class)
                .getResultList();
    }
}
