# Employee Management API (No Docker)

This is a ready-to-run Spring Boot 3.1 project (Java 17) for the **Employee Management API**.

## What is included
- Spring Boot 3.1.4
- Spring Web, Spring Data JPA, Spring Security, Validation
- PostgreSQL JDBC driver
- jjwt for JWT handling
- Lombok
- springdoc-openapi (Swagger UI)
- Pre-created default users on startup (admin/admin123, user/user123)

## Run without Docker (local Postgres)
1. Make sure you have a running PostgreSQL database.
   - Create database: `employee_db`
   - Create a user `postgres` with password `postgres` (or update src/main/resources/application.yml)
2. Update `src/main/resources/application.yml` if your DB credentials differ.
3. Build:
   ```bash
   mvn -DskipTests clean package
   ```
4. Run:
   ```bash
   java -jar target/employee-api-0.0.1-SNAPSHOT.jar
   ```
5. Default users are created automatically on startup:
   - admin / admin123 (ROLE_ADMIN)
   - user / user123 (ROLE_USER)

## Endpoints
- `POST /api/auth/register?username=&email=&password=&role=ROLE_USER|ROLE_ADMIN`
- `POST /api/auth/login` (JSON body: `{ "username":"...", "password":"..." }`)
- `POST /api/employees` (admin only)
- `GET /api/employees` (auth)
- `GET /api/employees/{id}` (auth)
- `PUT /api/employees/{id}` (auth)
- `DELETE /api/employees/{id}` (admin only)

## Swagger UI
- Open: `http://localhost:8080/swagger-ui.html` after app starts.

## Notes
- The `jwt.secret` in `application.yml` must be at least 32 bytes for HMAC key size when using jjwt.
- If you want to use Docker later, re-add `docker-compose.yml` from the original package.

