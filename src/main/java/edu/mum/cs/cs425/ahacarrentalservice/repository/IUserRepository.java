package edu.mum.cs.cs425.ahacarrentalservice.repository;

import edu.mum.cs.cs425.ahacarrentalservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    public User findByUsername(String username);
    public Boolean existsByUsername(String username);


}
