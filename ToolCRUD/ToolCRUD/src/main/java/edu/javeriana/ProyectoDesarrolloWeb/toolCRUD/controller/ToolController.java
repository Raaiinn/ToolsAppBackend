package edu.javeriana.ProyectoDesarrolloWeb.toolCRUD.controller;
import edu.javeriana.ProyectoDesarrolloWeb.toolCRUD.domain.Tool;
import edu.javeriana.ProyectoDesarrolloWeb.toolCRUD.service.ToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/data")
@CrossOrigin(origins = "http://localhost:4200")
public class ToolController {

    @Autowired
    private ToolService service;


    //RETRIEVE = GET
    @GetMapping(value="tool")
    public List<Tool> toolGet(){
        System.out.println("Executed HTTP request GET - ALL");
        return service.getTool();
    }

    @GetMapping(value="tool/{id}")
    public ResponseEntity<Tool> toolGetById(@PathVariable Integer id){
        System.out.println("Executed HTTP request GET - ById: "+id);
        return service.getToolById(id);
    }


    //CREATE = POST
    @PostMapping(value = "toolInsert", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Tool toolPost(@RequestBody Tool tool){
        System.out.println("Executed HTTP request POST with Id: "+tool.getId());
        return service.insertTool(tool);
    }

    //UPDATE = PUT
    @PutMapping(value = "toolUpdate/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Tool> toolUpdate(@PathVariable Integer id, @RequestBody Tool tool){
        System.out.println("Executed HTTP request PUT with Id: "+tool.getId());
        return service.updateTool(id, tool);
    }

    //DELETE = DELETE
    @DeleteMapping(value = "toolDelete/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity <Map<String, Boolean>> toolDelete(@PathVariable Integer id){
        System.out.println("Executed HTTP request DELETE with Id: "+id);
        return service.deleteTool(id);
    }
}
