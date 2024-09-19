package br.com.devbean.mode01.dataloader;

import br.com.devbean.mode01.constants.AccessRole;
import br.com.devbean.mode01.entities.UserEntity;
import br.com.devbean.mode01.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Criação de usuário ADMIN
        if (userRepository.findByUsername("admin") == null) {
            UserEntity admin = new UserEntity();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123")); // senha criptografada
            admin.setRoles(AccessRole.ROLE_ADMIN);
            admin.setEnabled(true);
            userRepository.save(admin);
        }

        // Criação de usuário USER
        if (userRepository.findByUsername("user") == null) {
            UserEntity user = new UserEntity();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("user123")); // senha criptografada
            user.setRoles(AccessRole.ROLE_USER);
            user.setEnabled(true);
            userRepository.save(user);
        }
    }
}
