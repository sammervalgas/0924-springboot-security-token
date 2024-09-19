package br.com.devbean.mode02.dataloader;

import br.com.devbean.mode02.enums.UserRole;
import br.com.devbean.mode02.models.UserModel;
import br.com.devbean.mode02.repositories.UserAccessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DataLoaderMode02 implements CommandLineRunner {

    @Autowired
    private UserAccessRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Criação de usuário ADMIN
        if (userRepository.findByEmail("admin@admin.com") == null) {
            UserModel admin = new UserModel();
            admin.setEmail("admin@admin.com");
            admin.setPassword(passwordEncoder.encode("admin")); // senha criptografada
            admin.setRole(UserRole.ADMIN);
            admin.setCreatedAt(new Date());
            userRepository.save(admin);
        }

        // Criação de usuário USER
        if (userRepository.findByEmail("guest@guest.com") == null) {
            UserModel admin = new UserModel();
            admin.setEmail("guest@guest.com");
            admin.setPassword(passwordEncoder.encode("guest")); // senha criptografada
            admin.setRole(UserRole.GUEST);
            admin.setCreatedAt(new Date());
            userRepository.save(admin);
        }
    }
}
