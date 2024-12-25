package alter.alter_core.service;

import alter.alter_core.domain.User;
import alter.alter_core.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 사용자 등록
    public User createUser(String name, String email, String password) {
        /*
            같은 이메일을 가진 사람은 가입 x (email 기준)
            비밀번호는 BCrpto로 암호화
        */

        try {
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(password));

            validateUser(user);
            userRepository.save(user);

            return user;
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            throw new IllegalStateException("Failed to create User");
        }
    }

    private void validateUser(User user) {
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalStateException("Email is null");
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalStateException("Password is null");
        }

        userRepository.findByEmail(user.getEmail())
                .ifPresent(existinguser -> {
                    throw new IllegalStateException("User email already exists");
                });
    }
}
