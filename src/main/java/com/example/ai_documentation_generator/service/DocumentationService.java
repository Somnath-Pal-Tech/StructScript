package com.example.ai_documentation_generator.service;

import com.example.ai_documentation_generator.model.Documentation;
import com.example.ai_documentation_generator.repository.DocumentationRepository;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import org.springframework.stereotype.Service;

@Service
public class DocumentationService {

    private final DocumentationRepository repository;

    private final Client client = new Client();

    public DocumentationService(DocumentationRepository repository) {
        this.repository = repository;
    }


    // Save documentation to database
    public Documentation saveDocumentation(String title, String content) {

        Documentation documentation = new Documentation();

        documentation.setTitle(title);
        documentation.setContent(content);

        return repository.save(documentation);
    }


    // Generate documentation using Gemini AI
    public String generateDocumentation(String sourceCode) {

        String prompt = """
                You are a professional software documentation generator.

                Analyze the Java source code provided below and create
                professional, accurate, concise, and beginner-friendly
                technical documentation.

                IMPORTANT RULES:

                - Only describe functionality that can be understood from the code.
                - Do not invent classes, methods, libraries, features, or behavior.
                - Keep the explanation clear and practical.
                - Avoid unnecessary repetition.
                - Do not create ASCII diagrams or flowcharts.
                - Do not put normal explanations inside code blocks.
                - Use Markdown headings, bullet points, and tables where useful.
                - Use code blocks only when showing actual source code or output.
                - Keep the documentation well structured.
                - Do not repeat the complete source code unless necessary.
                - Keep the documentation reasonably concise.

                Use exactly this structure:

                # Technical Documentation

                ## 1. Overview

                Briefly explain what the program does and its main purpose.

                ## 2. Main Class

                Explain the main class and its responsibility.

                ## 3. Methods

                Create a Markdown table containing:

                | Method | Purpose | Parameters | Return Value |
                |--------|---------|------------|--------------|

                Include only methods that actually exist in the provided code.

                ## 4. How the Code Works

                Explain the execution flow step by step using a numbered list.

                ## 5. Important Variables

                Create a small Markdown table containing:

                | Variable | Type | Purpose |
                |----------|------|---------|

                Include only important variables that actually exist in the code.

                ## 6. Input and Output

                Explain the input received by the program and the output produced.

                ## 7. Example

                Provide a simple example based only on the given code.

                If the program produces console output, show it in a code block.

                ## 8. Key Points

                Give 3 to 5 important points about the program.

                Remember:

                - Do not invent information.
                - Do not create ASCII flowcharts.
                - Do not use unnecessary code blocks.
                - Keep the documentation professional and easy to understand.

                Source code:

                """ + sourceCode;


        // Retry if Gemini temporarily returns a server error
        for (int attempt = 1; attempt <= 3; attempt++) {

            try {

                GenerateContentResponse response =
                        client.models.generateContent(
                                "gemini-3.8-flash",
                                prompt,
                                null
                        );

                return response.text();

            } catch (com.google.genai.errors.ServerException e) {

                if (attempt == 3) {
                    throw e;
                }

                try {

                    Thread.sleep(3000);

                } catch (InterruptedException interruptedException) {

                    Thread.currentThread().interrupt();

                    throw new RuntimeException(
                            interruptedException
                    );
                }
            }
        }

        return "Unable to generate documentation.";
    }
}