package edu.javeriana.ProyectoDesarrolloWeb.Security.service;

import edu.javeriana.ProyectoDesarrolloWeb.Security.domain.User;
import edu.javeriana.ProyectoDesarrolloWeb.Security.repository.UserRepository;
import edu.javeriana.ProyectoDesarrolloWeb.Security.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository repository;
    public User createUser(User user){
        User aux = new User();
        UserDetailsImpl imp = loadUserByUsername(user.getUsername());
        aux.setUsername(imp.getUsername());
        aux.setPassword(imp.getPassword());
        aux.setToken(user.getToken());
        aux.setActive(imp.isEnabled());
        aux.setId(imp.getId());
        return aux;
    }

    @Override
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findOneByUsername(username).orElseThrow(() -> new UsernameNotFoundException("The username "+username+" doesn't exist."));
        return new UserDetailsImpl(user);
    }
}
