package edu.javeriana.ProyectoDesarrolloWeb.userCRUD.controller;

import edu.javeriana.ProyectoDesarrolloWeb.userCRUD.domain.Person;
import edu.javeriana.ProyectoDesarrolloWeb.userCRUD.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")

public class UserController {
    @Autowired
    private UserService service;

    @GetMapping(value="get")
    public List<Person> userGet(){
        return service.getUser();
    }

    @GetMapping(value="user/{id}")
    public ResponseEntity<Person> userGetById(@PathVariable Integer id){
        return service.getUserById(id);
    }

    @PostMapping(value="userInsert", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Person userPost(@RequestBody Person person){
        return service.createUser(person);
    }

    @PutMapping(value="userUpdate/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Person> userPut(@PathVariable Integer id, @RequestBody Person person){
        return service.updateUser(id, person);
    }

    @DeleteMapping(value = "userDelete/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Boolean>> userDelete(@PathVariable Integer id){
        return service.deactivateUser(id);
    }
}

