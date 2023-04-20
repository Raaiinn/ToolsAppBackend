package edu.javeriana.ProyectoDesarrolloWeb.userCRUD.repository;

import edu.javeriana.ProyectoDesarrolloWeb.userCRUD.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UserRepository extends JpaRepository<Person, Integer> {
}
