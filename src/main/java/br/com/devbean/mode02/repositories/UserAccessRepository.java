package br.com.devbean.mode02.repositories;

import br.com.devbean.mode02.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface UserAccessRepository extends JpaRepository<UserModel, UUID> {

    UserDetails findByEmail(String email);
}
