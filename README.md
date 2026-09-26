# CloudMart — Product Service

## Project Description

Manages the product catalog for CloudMart. Backed by a relational
database (Cloud SQL for MySQL) and integrates with Google Cloud Storage so
product images uploaded through the API/frontend are stored in a bucket
rather than on local disk — satisfying the module's mandatory relational
database and Cloud Storage requirements.

## Technology Stack

- Java 25
- Spring Boot 4.0.7 (Spring Web MVC)
- Spring Data JPA + MySQL (Google Cloud SQL)
- Spring Cloud Eureka Client + Config Client
- Google Cloud Storage (product images)
- PM2 (process management on the deployed VM)

## API

| Method | Path                       | Description                     |
| ------ | -------------------------- | ------------------------------- |
| GET    | `/api/products`            | List all products               |
| GET    | `/api/products/{id}`       | Get one product                 |
| POST   | `/api/products`            | Create a product                |
| PUT    | `/api/products/{id}`       | Update a product                |
| DELETE | `/api/products/{id}`       | Delete a product                |
| POST   | `/api/products/{id}/image` | Upload image to GCS (multipart) |

## Setup / Getting Started

### Prerequisites

- Java 25 JDK, Maven 3.9+
- A MySQL instance reachable locally (or via Cloud SQL Auth Proxy)
- A GCP service account with Storage Object Admin on your bucket, either via
  `gcloud auth application-default login` (local) or the VM's attached
  service account (deployed)

### Run locally

```bash
mvn clean package
java -jar target/product-service.jar
```

### Environment variables

`DB_URL`, `DB_USERNAME`, `DB_PASSWORD`, `GCS_BUCKET_NAME`, `CONFIG_SERVER_URL`,
`EUREKA_SERVER_URL` — see `ecosystem.config.js`.

## Student Information

- **Student Name:** A.G.Vihana Pathum Piyasiri
- **Student Number:** 2301692038
- **Slack Handle:** vihana_piyasiri
- **GCP Project ID:** project-1023ef7b-f75c-4e17-ab5
