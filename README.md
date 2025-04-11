# 💼 eTap Job Portal

---

## 📖 Overview

A **comprehensive job posting platform** where:

- 🏢 Companies can post vacancies  
- 🙋‍♂️ Users can browse and apply for jobs

Designed to support **secure user management**, **role-based access**, and **media upload** via cloud.

---

## 🌟 Features

### 🔐 Authentication
- User registration & login with **JWT token**
- Company registration & login with **JWT token**
- **Role-based authorization** (`USER`, `COMPANY`)

### 📄 Vacancy Management
- Create job vacancies with detailed fields  
- Upload vacancy images (**stored in AWS S3**)  
- View all vacancies  
- Filter vacancies by:
  - Category
  - City
  - Salary range *(planned)*

### 👤 User Management
- Secure password storage (**BCrypt**)  
- Email uniqueness validation  
- Token-based session handling  

---

## 🧰 Technologies Used

### 🖥️ Backend
- **Java 17**
- **Spring Boot 3.x**
- **Spring Security**
- **JWT** for stateless authentication  
- **MapStruct** – DTO ↔ Entity mapper  
- **Lombok** – Boilerplate reduction  
- **Hibernate / JPA**

### ☁️ Cloud & Storage
- **AWS SDK** – Image upload to Amazon S3  

### 🗃️ Database
- **PostgreSQL** – Relational DB  

---

## 🔌 API Endpoints

### 🛂 Auth Controller



### API Endpoints
POST /auth/user/register       # Register new user
POST /auth/user/login          # User login
POST /auth/company/register    # Register new company
POST /auth/company/login       # Company login

### Vacancy Controller
- `POST /company/addVacancy` - Add new vacancy (with image upload)
- `GET /auth/vacancies` - Get all vacancies
⚙️ How It Works
User or company registers/logs in with email and password

System issues JWT token and verifies role

Depending on role:

Users can view & filter jobs

Companies can post and manage vacancies

🧠 Business Logic Highlights
🚀 The engine behind the platform includes:

Secure JWT handling with token validation filters

Role-based method access (e.g. @PreAuthorize)

AWS S3 integration for file uploads

MapStruct for clean and fast object conversions

DTO pattern for clean request/response models

Validation layer for input safety

🚀 Future Improvements
Apply to vacancies (user-side)

Save/bookmark jobs

Advanced filtering (salary, job type)

Company profiles

Admin dashboard

Daily email notifications

🤝 Contributions
Suggestions and contributions are welcome!
Feel free to fork, open pull requests, or create issues.

Made with ☕ and 💻 by Nurlan

## Front-end screenshots
![Screenshot 2025-04-09 184904](https://github.com/user-attachments/assets/2fcf020a-3940-433a-bd4b-1b9f04f4ea8a)
![Screenshot 2025-04-09 184856](https://github.com/user-attachments/assets/20c8c6ec-f6e5-4240-af21-2e194c691759)
![Screenshot 2025-04-09 184847](https://github.com/user-attachments/assets/c9c59280-c775-4582-b875-ad6dfca8ffc7)
![Screenshot 2025-04-09 202933](https://github.com/user-attachments/assets/60dda17d-d641-4f62-af4c-3fcfa2b78e6d)
![Screenshot 2025-04-09 184714](https://github.com/user-attachments/assets/115da753-4633-4dac-b1f0-325ba83e890f)
![Screenshot 2025-04-09 184653](https://github.com/user-attachments/assets/33562d2e-c57c-4f5a-87b1-e2516221c834)
![Screenshot 2025-04-09 184736](https://github.com/user-attachments/assets/5eacf93e-8220-4416-b3ff-d4ccace17e51)
![Screenshot 2025-04-09 202911](https://github.com/user-attachments/assets/891b68b2-8618-4d82-9733-7a3efc775ab2)
![Screenshot 2025-04-09 184802](https://github.com/user-attachments/assets/ce1019eb-675e-4544-b768-120608add4da)
![Screenshot 2025-04-09 184829](https://github.com/user-attachments/assets/a8325301-2ead-4449-9036-289c7d342531)
![Screenshot 2025-04-09 184824](https://github.com/user-attachments/assets/d2f15e1a-4c4d-4480-a385-e8cf4ba668ce)
![Screenshot 2025-04-09 184819](https://github.com/user-attachments/assets/cf05ed62-361e-4170-b57d-cac1aade25e3)
![Screenshot 2025-04-09 184813](https://github.com/user-attachments/assets/2397d3ca-8f28-4510-8986-b12df1cfcd8f)
![Screenshot 2025-04-09 184806](https://github.com/user-attachments/assets/439e8163-ee0a-4ad3-8156-ba3196b06ea6)

```http
