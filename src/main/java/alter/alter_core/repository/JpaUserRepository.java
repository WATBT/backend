package alter.alter_core.repository;

import alter.alter_core.domain.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaUserRepository implements UserRepository{

    private final EntityManager em;

    public JpaUserRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public User save(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public Optional<User> findById(UUID id) {
        User user = em.find(User.class, id);
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findByName(String name) {
        return em.createQuery("select u from User u where u.name = :name", User.class)
                .setParameter("name", "%" + name + "%" )
                .getResultList();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        List<User> user = em.createQuery("select u from User u where u.email = :email", User.class)
                .setParameter("email", email)
                .getResultList();
        return user.stream().findAny();
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("select u from User u", User.class)
                .getResultList();
    }

    @Override
    public User delete(UUID id) {
        try {
            int updatedRows = em.createQuery("update User u set u.isValid = false where u.id = :id", User.class)
                    .setParameter("id", id)
                    .executeUpdate();
            if (updatedRows == 0) {
                throw new PersistenceException("No user found with id: " + id);
            }
            User user = em.find(User.class, id);
            return user;
        } catch (Exception e) {
            throw new PersistenceException("delete user failed" + e.getMessage());
        }
    }
}
