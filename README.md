# SettleFlow

SettleFlow is a learning-focused payment and merchant settlement platform built to explore financial correctness, backend architecture, transaction processing, reliability, and distributed-system design.

The project evolves through small vertical slices. External payment and payout infrastructure will be simulated so the project can focus on internal payment, ledger, settlement, payout, and reconciliation workflows without processing real money.

## Current Architecture

```text
Browser
   |
   v
React + TypeScript + Vite
   |
   | HTTP
   v
Spring Boot
   |
   v
PostgreSQL
```

The backend starts as a modular monolith. PostgreSQL is the durable source of truth, and Flyway owns database schema evolution.

Kafka, Redis, and additional infrastructure will be introduced only when an implemented feature demonstrates a concrete need for them.

## Current Stack

**Backend**

* Java 24
* Spring Boot
* Maven
* Spring MVC
* Spring Data JPA
* Flyway
* PostgreSQL
* Spring Boot Actuator

**Frontend**

* React
* TypeScript
* Vite

**Local Development**

* PostgreSQL installed locally
* Spring Boot runs on the host
* React/Vite runs on the host

Docker may be introduced later for supporting infrastructure such as Kafka, Redis, Prometheus, Grafana, or isolated tests.

## Running Locally

Ensure the local PostgreSQL service is running and that the `settleflow` database/user exist. By default, the backend expects:

```text
Database: settleflow
Username: settleflow
Password: settleflow_local
Port: 5432
```

### Start the backend

```powershell
cd backend
.\mvnw.cmd spring-boot:run
```

Backend health:

```text
http://localhost:8080/actuator/health
```

### Start the frontend

```powershell
cd frontend
npm install
npm run dev
```

Frontend:

```text
http://localhost:5173
```

During local development, Vite proxies the Actuator health request to the Spring Boot backend.

## Engineering Principles

* Start with a modular monolith rather than premature microservices.
* Organize code primarily around business capabilities.
* Keep financial business rules authoritative on the backend.
* Treat PostgreSQL as durable financial truth.
* Use Flyway rather than automatic Hibernate schema mutation.
* Keep external provider boundaries separate from core domain logic.
* Introduce Kafka, Redis, and other infrastructure only when a concrete problem justifies them.
* Build one business feature at a time and test its correctness before expanding the system.

## Project Status

The application foundation is complete:

```text
React
   ↓
Spring Boot
   ↓
PostgreSQL
```

The next business capability is the Merchant domain. It has not been started yet.
