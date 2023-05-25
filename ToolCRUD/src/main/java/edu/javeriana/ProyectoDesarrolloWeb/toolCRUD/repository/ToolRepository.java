package edu.javeriana.ProyectoDesarrolloWeb.toolCRUD.repository;

import edu.javeriana.ProyectoDesarrolloWeb.toolCRUD.domain.Tool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToolRepository extends JpaRepository<Tool, Integer> {

}
