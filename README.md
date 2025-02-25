# 📚 Library Management System

A simple Library Management System for tracking books, authors, categories, members, and borrowing transactions. 
This system allows users to manage books, track borrowed and returned items, and organize the library.

## Features 🚀
✅ **Book Management** – Add, update, delete, and search books.  
✅ **Author Management** – Store and manage author details.  
✅ **Category Management** – Classify books into different categories.  
✅ **Member Management** – Keep track of library members.
✅ **Borrow & Return System** – Track book borrowings.

## Tech Stack 🛠
- **Backend**: Spring Boot (Java)
- **Database**: PostgreSQL
- **Containerization**: Docker & Docker Compose

## Installation & Setup 🏗

### Prerequisites  
Ensure you have the following installed:  
- [Docker](https://www.docker.com/)  
- [Docker Compose](https://docs.docker.com/compose/)  

### Steps  

1. **Clone the repository:**  
   ```bash
   git clone https://github.com/aidananurs/library.git
   cd library
   ```  

2. **Build and run the services:**  
   ```bash
   docker-compose up --build -d
   ```  
   This will start the following containers:  
   - `app`: The Spring Boot application.  
   - `db`: PostgreSQL database instance.  

3. **Verify that the containers are running:**  
   ```bash
   docker ps
   ```  
   You should see both the `app` and `db` containers running.  

4. **Access the application:**  
   - The backend service will be available at: [`http://localhost`](http://localhost)  
   - The PostgreSQL database runs on port `5432` with credentials:  
     - **User:** `library_user`  
     - **Password:** `library_pass_123`  
     - **Database:** `library_db`  

5. **Stopping the services:**  
   ```bash
   docker-compose down
   ```  

