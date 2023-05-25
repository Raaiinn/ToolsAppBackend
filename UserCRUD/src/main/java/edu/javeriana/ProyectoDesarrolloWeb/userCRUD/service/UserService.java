package edu.javeriana.ProyectoDesarrolloWeb.userCRUD.service;

import edu.javeriana.ProyectoDesarrolloWeb.userCRUD.domain.City;
import edu.javeriana.ProyectoDesarrolloWeb.userCRUD.domain.Person;
import edu.javeriana.ProyectoDesarrolloWeb.userCRUD.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public Person createUser(Person person){
        return repository.save(person);
    }

    public List<Person> getUser(){
        return repository.findAll();
    }
    public Page<Person> pages(Pageable pageable){
        return repository.findAll(pageable);
    }

    public ResponseEntity<Person> getUserById(Integer id){
        Person aux = repository.findById(id).orElseThrow();
        return ResponseEntity.ok(aux);
    }

    public ResponseEntity<Person> updateUser(Integer id, Person person){
        Person aux = repository.findById(id).orElseThrow();
        aux.setId(person.getId());
        aux.setName(person.getName());
        aux.setLastname(person.getLastname());
        aux.setCity(person.getCity());
        aux.setUsers(person.getUsers());
        person = repository.save(aux);
        return ResponseEntity.ok(aux);
    }
    public ResponseEntity<Map<String, Boolean>> deactivateUser(Integer id){
        Person user = repository.findById(id).orElseThrow();
        for(int i=0; i<user.getUsers().size(); i++){
            user.getUsers().get(i).setActive(false);
        }
        repository.save(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("User deactivated", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }


}
