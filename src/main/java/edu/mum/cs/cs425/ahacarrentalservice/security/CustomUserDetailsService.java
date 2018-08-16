package edu.mum.cs.cs425.ahacarrentalservice.security;

import edu.mum.cs.cs425.ahacarrentalservice.model.User;
import edu.mum.cs.cs425.ahacarrentalservice.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user==null){
            System.out.println("USER NOT FOUND");
            throw new UsernameNotFoundException("User "+username+" doesn't exist!");
        }else{
            return new CustomUserDetails(user);
        }
    }
}

