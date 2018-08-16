package edu.mum.cs.cs425.ahacarrentalservice.service;

import edu.mum.cs.cs425.ahacarrentalservice.model.User;
import edu.mum.cs.cs425.ahacarrentalservice.repository.IUserRepository;
import edu.mum.cs.cs425.ahacarrentalservice.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IService<User> {

    @Autowired
    private IUserRepository repository;

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public List<User> findAll(String orderingProperty) {
        return repository.findAll(new Sort(Sort.Direction.ASC,orderingProperty));
    }

    @Override
    public User findById(Long id) {
        Optional<User> result = repository.findById(id);
        return result.orElse(null);
    }

    @Override
    public User save(User user) throws ValidationException {
        if(verifyExistsAnyByUsername(user.getUsername())){
            throw new ValidationException("The informed username is already taken!");
        }
        return repository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Boolean verifyExistsAnyByUsername(String username){
        return repository.existsByUsername(username);
    }
}
