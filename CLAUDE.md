# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

`eloarena-backend` is a Spring Boot 3.3.0 matchmaking demo that uses the Elo rating system for player pairing. Java 21, Gradle, H2 in-memory database.

## Commands

```bash
# Build
./gradlew build

# Run application (starts on http://localhost:8080)
./gradlew bootRun

# Run all tests
./gradlew test

# Run a single test class
./gradlew test --tests "com.eloarena.SomeTest"

# Run a single test method
./gradlew test --tests "com.eloarena.SomeTest.methodName"

# H2 console (while app is running)
# http://localhost:8080/h2-console  JDBC URL: jdbc:h2:mem:eloarena
```

## Architecture

Standard Spring Boot layered architecture under `src/main/java/com/eloarena/`:

- **Web layer** — REST controllers (`@RestController`)
- **Service layer** — business logic including Elo rating calculations and matchmaking
- **Data layer** — Spring Data JPA repositories with H2 in-memory persistence (`ddl-auto: update`)

Database schema is auto-managed by Hibernate from JPA entity definitions — no migration files.

## Key Notes

- Database is **in-memory** (H2); all data is lost on restart. Use `ddl-auto: create-drop` in tests if isolation is needed.
- The Elo system is the core domain — when implementing rating changes, the standard formula uses K-factor and expected score: `E = 1 / (1 + 10^((Rb - Ra) / 400))`, `R' = R + K * (S - E)`.
