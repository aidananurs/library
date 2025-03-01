# Library Management System

A simple Library Management System for tracking books, authors, categories, members, and borrowing transactions. 
This system allows users to manage books, track borrowed and returned items, and organize the library.

## Features
✅ **Book Management** – Add, update, delete, and search books.  
✅ **Author Management** – Store and manage author details.  
✅ **Category Management** – Classify books into different categories.  
✅ **Member Management** – Keep track of library members.  
✅ **Borrow & Return System** – Track book borrowings.

## Tech Stack
- **Backend**: Spring Boot (Java)
- **Database**: PostgreSQL
- **Containerization**: Docker & Docker Compose

## Installation & Setup

### Prerequisites  
Ensure you have the following installed:  
- [Docker](https://www.docker.com/)  
- [Docker Compose](https://docs.docker.com/compose/)
- [Maven](https://maven.apache.org/install.html)

### Steps  

1. **Clone the repository:**  
   ```bash
   git clone https://github.com/aidananurs/library.git
   cd library
   ```
2. **Run:**  
   ```bash
   mvn clean package
   ``` 

3. **Build and run the services:**  
   ```bash
   docker compose up --build -d
   ```  
   This will start the following containers:  
   - `app`: The Spring Boot application.  
   - `db`: PostgreSQL database instance.  

4. **Verify that the containers are running:**  
   ```bash
   docker ps
   ```  
   You should see both the `app` and `db` containers running.  

5. **Access the application:**  
   - The backend service will be available at: [`http://0.0.0.0:8088`](http://0.0.0.0:8088)  
   - The PostgreSQL database runs on `0.0.0.0:5432` with credentials:  
     - **User:** `library_user`  
     - **Password:** `library_pass_123`  
     - **Database:** `library_db`
       
6. **Execute a SQL Script After Container Startup:**
   ```bash
   docker exec -i db psql -U library_user -d library_db < ./database/insert.sql
   ```
   This Script inserts test data.

7. **Postman Collection**  
      To test API endpoints, import the Postman collection:
      
      1. Open [Postman](https://www.postman.com/).
      2. Click on **File → Import**.
      3. Select the file `postman/libraryApi.json` from this repository.
      4. Update environment variable to match your local setup.
      5. Send requests to test the API

8. **Stopping the services:**  
   ```bash
   docker-compose down
   ```  

