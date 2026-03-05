# CLAUDE.md - AI Assistant Guide for escaperoomapi

## Project Overview

Spring Boot REST API for escape room games. Players solve sequential riddles by submitting solutions via API endpoints. Multiple themed escape rooms are supported through Spring Profiles.

## Tech Stack

- **Language:** Java 19
- **Framework:** Spring Boot 3.2.11
- **Build Tool:** Maven 3.6.3 (via `./mvnw` wrapper)
- **Annotations:** Lombok 1.18.36
- **Testing:** JUnit 5 (Jupiter)
- **Code Coverage:** JaCoCo 0.8.10
- **CI/CD:** CircleCI
- **Container:** Docker (Alpine + JDK 21)

## Project Structure

```
src/main/java/de/serviceware/escaperoom/escaperoom/
├── EscaperoomApplication.java       # Spring Boot entry point
├── EscaperoomController.java        # REST controller (all endpoints)
├── model/                           # DTOs: EscapeRoom, Riddle, Hint, SolutionProposal, etc.
└── service/                         # Business logic
    ├── RiddleService.java           # Interface
    ├── RiddleServiceBase.java       # Abstract base with shared logic
    ├── KVRiddle.java                # @Profile("KV") implementation
    ├── KVRiddle2.java               # @Profile("KV2") - Scotland Yard theme
    ├── PDRiddle.java                # @Profile("PD") implementation
    └── PSORiddle.java               # @Profile("PSO") implementation

src/main/resources/
├── application.properties           # Config (default profile: PD)
└── static/                          # Images and PDF hints per theme
    ├── kv/, kv2/, pd/, pso/

src/test/java/de/serviceware/escaperoom/escaperoom/
├── EscaperoomApplicationTests.java  # Context load test
├── KVRiddleTest.java                # KV riddle tests (14 tests)
├── PDRiddleTest.java                # PD riddle tests (17 tests)
└── model/EscaperoomModelTest.java   # Model tests
```

## Common Commands

```bash
# Build
./mvnw clean package                    # Build with tests
./mvnw clean package -DskipTests        # Build without tests

# Test
./mvnw test                             # Run all tests
./mvnw test -Dtest=KVRiddleTest         # Run specific test class
./mvnw test jacoco:report               # Tests + coverage report

# Run locally
./mvnw spring-boot:run                  # Default profile (PD)
./mvnw spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=KV"

# Docker
docker build -t hbuender/escaperoom:latest .
```

## API Endpoints

| Method | Path | Description |
|--------|------|-------------|
| GET | `/health` | Health check |
| GET | `/initEscapeRoom` | Initialize and return escape room with first riddle |
| POST | `/solutionProposal` | Submit a riddle solution, returns result + next riddle |

Server runs on port **8080** (default).

## Architecture & Patterns

- **Profile-based bean selection:** Each escape room theme is a `@Service` with a `@Profile` annotation. The active profile in `application.properties` determines which service bean is injected.
- **Strategy pattern:** `RiddleService` interface with multiple implementations (`KVRiddle`, `PDRiddle`, etc.), all extending `RiddleServiceBase`.
- **HashMap solution lookup:** Solutions are stored as constants in HashMaps for O(1) validation.
- **Stateless design:** No sessions or persistence. Each request is independent; riddle content and solutions are hardcoded constants.
- **Lombok DTOs:** Models use `@Data` and `@AllArgsConstructor` — do not write manual getters/setters.

## Key Conventions

- **Package:** `de.serviceware.escaperoom.escaperoom` (base), with `.model` and `.service` subpackages.
- **Naming:** PascalCase classes, camelCase methods, UPPER_SNAKE_CASE constants.
- **New escape rooms:** Create a new `@Service` class extending `RiddleServiceBase` with a unique `@Profile`, add static resources under `src/main/resources/static/<theme>/`, and add the profile to `application.properties` to activate.
- **Tests:** Use `@ParameterizedTest` with `@CsvSource` for solution validation tests. Activate the correct profile with `@ActiveProfiles`.
- **CORS:** Controller uses `@CrossOrigin` — maintain this for frontend compatibility.

## CI/CD (CircleCI)

Pipeline runs on `cimg/openjdk:19.0`:
1. `mvn -B -DskipTests clean package` (build)
2. `mvn test` (test)
3. Store results from `target/surefire-reports`

## Spring Profiles

| Profile | Theme | Service Class |
|---------|-------|---------------|
| `KV` | KV escape room | `KVRiddle` |
| `KV2` | Scotland Yard | `KVRiddle2` |
| `PD` | PD escape room (default) | `PDRiddle` |
| `PSO` | PSO escape room | `PSORiddle` |
