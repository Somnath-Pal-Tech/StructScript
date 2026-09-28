# 📘 StructScript

AI-powered source code documentation generator built with **Spring Boot** and **Gemini AI**.

---

## ✨ Features
- 🔍 Generate documentation directly from source code
- 🤖 Gemini AI integration for intelligent doc generation
- 🌐 RESTful API built with Spring Boot
- 🗄️ MySQL database support
- 💻 Simple and intuitive web interface

---

## 🛠️ Tech Stack
- **Backend:** Java, Spring Boot
- **AI Integration:** Gemini AI
- **Frontend:** HTML, CSS, JavaScript
- **Database:** MySQL
- **Build Tool:** Maven

---

## ⚡ Getting Started

### Prerequisites
- Java 17+
- Maven
- MySQL (create a database named `structscript`)
- Gemini AI API key

### Configuration
Update your `application.properties` file:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/structscript
spring.datasource.username=YOUR_DB_USER
spring.datasource.password=YOUR_DB_PASSWORD
gemini.api.key=YOUR_GEMINI_API_KEY
