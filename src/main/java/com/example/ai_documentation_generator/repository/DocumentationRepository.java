package com.example.ai_documentation_generator.repository;

import com.example.ai_documentation_generator.model.Documentation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentationRepository extends JpaRepository<Documentation, Long> {

}
