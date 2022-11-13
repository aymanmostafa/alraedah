# Track the occurrence of a perfect cycle API

API to track the occurrence of a perfect cycle in multiple lists
  
---

## Getting Started

This application was made by Java v8 using Spring boot framework v2.4.5 and the following are applied:

* SOLID principles.
* Restful best practices.
* Unit tests using jUnit.
* Swagger.
* Lombok.
* SonarList analysis.

---

### Run

* mvn spring-boot:run
* open http://localhost:8080

---

### Swagger

* http://localhost:8080/swagger-ui.html

---

### Docker

* mvn clean install
* docker build -t perfectCycle-api-image .
* docker run -p 8080:8080 perfectCycle-api-image
