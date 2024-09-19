package br.com.devbean.mode01.services;

import br.com.devbean.mode01.entities.UserEntity;
import br.com.devbean.mode01.repositories.UserRepository;
import br.com.devbean.mode02.repositories.UserAccessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAccessRepository userAccessRepository;

    @Autowired
    private LegacyUserAccessDetails legacyUserAccessDetails;

    @Autowired
    private ModernizationUserAccessDetails modernizationUserAccessDetails;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return Stream.of(
                        legacyUserAccessDetails,
                        modernizationUserAccessDetails
                )
                .map(service -> service.loadUserDetailsBy(username))
                .filter(Objects::nonNull)
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

//        UserEntity user = userRepository.findByUsername(username);
//
//        if (user == null) {
//            UserDetails userAccess = userAccessRepository.findByEmail(username);
//            if(userAccess == null)
//                throw new UsernameNotFoundException("User not found");
//             return userAccess;
//        }
//
//        return new org.springframework.security.core.userdetails.User(
//                user.getUsername(),
//                user.getPassword(),
//                user.getEnabled(),
//                true,
//                true,
//                true,
//                Arrays.stream(user.getRoles().split(","))
//                      .map(SimpleGrantedAuthority::new)
//                      .collect(Collectors.toList()));
    //  }
}

interface UserAccessDetails {
    UserDetails loadUserDetailsBy(String username);
}

@Component
class LegacyUserAccessDetails implements UserAccessDetails{

    private final UserRepository userRepository;

    LegacyUserAccessDetails(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserDetailsBy(String username) {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) return null;

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getEnabled(),
                true,
                true,
                true,
                Arrays.stream(user.getRoles().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList()));
    }
}

@Component
class ModernizationUserAccessDetails implements UserAccessDetails {

    private final UserAccessRepository repository;

    ModernizationUserAccessDetails(UserAccessRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserDetailsBy(String username) {
        return repository.findByEmail(username);
    }
}
