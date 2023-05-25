package edu.javeriana.ProyectoDesarrolloWeb.toolCRUD.service;

import edu.javeriana.ProyectoDesarrolloWeb.toolCRUD.domain.Tool;
import edu.javeriana.ProyectoDesarrolloWeb.toolCRUD.repository.ToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ToolService {
    @Autowired
    private ToolRepository repository;

    public Tool insertTool(Tool tool){
        return repository.save(tool);
    }

    public Page<Tool> pages(Pageable pageable){
        return repository.findAll(pageable);
    }

    public List<Tool> getTool() {
        return repository.findAll();
    }

    public ResponseEntity<Tool> getToolById(Integer id){
        Tool aux = repository.findById(id).orElseThrow();
        return ResponseEntity.ok(aux);
    }

    public ResponseEntity<Tool> updateTool(Integer id, Tool tool){
        Tool aux = repository.findById(id).orElseThrow();
        aux.setDescription(tool.getDescription());
        aux.setName(tool.getName());
        aux.setImageURL(tool.getImageURL());
        aux.setBrand(tool.getBrand());
        tool = repository.save(aux);
        return ResponseEntity.ok(aux);
    }

    public ResponseEntity<Map<String, Boolean>> deleteTool(Integer id){
        Tool tool = repository.findById(id).orElseThrow();
        repository.delete(tool);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }


}
