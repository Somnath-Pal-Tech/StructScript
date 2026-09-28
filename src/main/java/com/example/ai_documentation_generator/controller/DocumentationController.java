package com.example.ai_documentation_generator.controller; 
 
import com.example.ai_documentation_generator.dto.DocumentationRequest; 
import com.example.ai_documentation_generator.model.Documentation; 
import com.example.ai_documentation_generator.service.DocumentationService; 
import org.springframework.web.bind.annotation.*; 
 
@RestController 
public class DocumentationController { 
 
    private final DocumentationService service; 
 
    public DocumentationController(DocumentationService service) { 
        this.service = service; 
    } 
 
    @GetMapping("/api/documentation") 
    public String documentation() { 
        return "Documentation API is working!"; 
    } 
 
    @PostMapping("/api/documentation") 
    public Documentation saveDocumentation( 
            @RequestParam String title, 
            @RequestParam String content) { 
 
        return service.saveDocumentation(title, content); 
    } 
 
    @PostMapping("/api/documentation/generate") 
    public String generateDocumentation( 
            @RequestBody DocumentationRequest request) { 
 
        return service.generateDocumentation(request.getSourceCode()); 
    } 
}      