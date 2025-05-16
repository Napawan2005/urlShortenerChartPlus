# Shorty 🏷️

## Overview 📖

Shorty is a lightweight URL shortening service built with **Java Spring Boot**. It provides endpoints to create, retrieve, and manage shortened URLs, offering a simple API and a clean web interface. Shorty helps you transform long URLs into compact, memorable links.

## Tech Stack 🛠️

- **Language & Framework**:
    - Java 21 with Spring Boot 3.4.5
- **Build Tool**:
    - Maven
- **Containerization**:
    - Docker
    - Docker Compose
- **Validation**:
    - Jakarta Validation API & Hibernate Validator
- **Data Access**:
    - Spring Data JDBC & Spring Data JPA
- **Database**:
    - PostgreSQL (runtime)


## Installation & Setup ⚙️

1. **Build with Maven**

   ```bash
   mvn clean package -DskipTests
   ```

2. **Build Docker Image**

   ```bash
   docker build -t shorty:latest .
   ```

3. **Run with Docker Compose**

   ```bash
   docker compose up --build
   ```

   This will start three containers:

    * **shorty-app**: the Spring Boot application
    * **postgres**: PostgreSQL database
    * **adminer** (optional): database administration UI

4. **Access the Service**

    * API endpoints: `http://localhost:8080/api/v1/...`
    * Health check: `http://localhost:8080/health`

## Configuration 🔧

Environment variables (default values shown):

| Name                         | Description             | Default                              |
| ---------------------------- | ----------------------- |--------------------------------------|
| `SPRING_DATASOURCE_URL`      | JDBC URL for PostgreSQL | `jdbc:postgresql://db:5432/shortydb` |
| `SPRING_DATASOURCE_USERNAME` | Database username       | `shorty_user`                        |
| `SPRING_DATASOURCE_PASSWORD` | Database password       | `shorty_pass`                        |
| `SERVER_PORT`                | HTTP server port        | `8080`                               |

## Usage 🚀

* **Create Short URL**:

```bash
  curl -X POST http://localhost:8080/api/v1/shorten \
    -H "Content-Type: application/json" \
    -d '{
            "url": "https://www.example.org/",
            "custom_alias": "example"
}'
  ```

***Redirect**:
Visit `http://localhost:8080/{shortCode}` in your browser.

## Contributing 🤝

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/...`)
3. Commit your changes (`git commit -m "feat: ..."`)
4. Push to the branch (`git push origin feature/...`)
5. Open a Pull Request

## License 📄

This project is licensed under the MIT License. See [LICENSE](LICENSE) for details.
