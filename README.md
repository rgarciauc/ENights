# Enights

## Getting Started

Follow these steps to run the application locally:

### 1. Database Setup

Create a PostgreSQL database named `hotelmanager`. You can configure the database connection in the Spring Boot application properties.

### 2. Run Spring Boot Server

Navigate to the `hotelmanagerBE` folder and run the Spring Boot server:

```bash
cd hotelmanagerBE
./mvnw spring-boot:run
```

### 3. Run Nuxt.js Client

```bash
cd hotelmanagerFE
yarn install
yarn dev
```
### 4. Open the browser

Open your web browser and navigate to http://localhost:3000 to access Enights Hotel application.

### Additional Information
The Spring Boot server exposes APIs at http://localhost:8080/api/hotel/
The Nuxt.js client communicates with the server APIs for data.