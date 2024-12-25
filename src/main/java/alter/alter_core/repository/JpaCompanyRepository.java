package alter.alter_core.repository;

import alter.alter_core.domain.Company;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

@Repository
public class JpaCompanyRepository implements CompanyRepository{

    @PersistenceContext
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
        List<Company> company = em.createQuery("select c from Company c where c.companyNo = :companyNo", Company.class)
                .setParameter("companyNo", companyNo)
                .getResultList();
        return company.stream().findAny();
    }

    @Override
    public List<Company> findByCompanyName(String name) {
        return em.createQuery("select c from Company c where c.name LIKE :name", Company.class)
                .setParameter("name", "%" + name + "%")
                .getResultList();
    }

    @Override
    public List<Company> findAll() {
        return em.createQuery("select c from Company c", Company.class)
                .getResultList();
    }

    @Override
    public UUID deleteById(UUID id) {
        Company company = em.find(Company.class, id);
        if (company != null) {
            em.remove(company);
        }
        return company.getId();
    }
}
