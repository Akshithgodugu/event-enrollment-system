# Gather — Web-Based Event Enrollment System

A full stack event enrollment application built with React and Vite, Java 21 and Spring Boot, Spring Web, Spring Data JPA, Spring Security, MySQL, Maven, and npm. The UI uses responsive normal CSS with a navy and light-blue palette.

## Requirements

- Windows 10/11
- Java 21, Maven 3.9+, Node.js 20+ with npm, and MySQL 8+

## Database setup

Start MySQL, then run from the project root:

```powershell
mysql -u root -p < .\database\schema.sql
```

The backend defaults to `root` / `root` and the local MySQL URL. Override credentials in PowerShell before starting the backend if needed:

```powershell
$env:DB_USERNAME = "root"
$env:DB_PASSWORD = "your-mysql-password"
# Optional full JDBC URL override:
$env:DB_URL = "jdbc:mysql://localhost:3306/event_enrollment_db?createDatabaseIfNotExist=true&serverTimezone=UTC"
```

JPA creates/updates the `users`, `events`, `enrollments`, and `payments` tables and their keys and relationships. At first launch, Spring seeds three upcoming events and a local administrator account.

**Local admin credentials:** `admin@gather.local` / `Admin123!`. Change this password before using the app outside a local demo environment.

## Run on Windows

Open two PowerShell windows from the project root.

Backend:

```powershell
cd .\backend
mvn spring-boot:run
```

Frontend:

```powershell
cd .\frontend
npm install
npm run dev
```

Open the Vite URL shown in the terminal (usually http://localhost:5173). The frontend calls `http://localhost:8081/api`; optionally set `VITE_API_URL` to change it before starting Vite.

## Features

- Register and log in with name, email, phone, and password. Passwords use BCrypt; duplicate emails are rejected.
- Session based login/logout, USER and ADMIN roles, account profile, and protected routes.
- Search/filter published upcoming events, event detail and seat availability.
- Admin event creation and cancellation, enrollment management, dashboard totals and successful payment revenue.
- Participant enrollment with duplicate, deadline, cancellation, and capacity checks.
- Simulated success/failure payments only. No payment credentials are requested. A failed enrollment can retry; success confirms it and returns the unique registration ID.
- My enrollments and confirmation with participant, event, price, payment status, and registration ID.

## API overview

- `POST /api/auth/register`, `POST /api/auth/login`, `POST /api/auth/logout`, `GET /api/auth/me`
- `GET /api/events`, `GET /api/events/{id}`
- `POST /api/enrollments`, `GET /api/enrollments/{id}`, `GET /api/enrollments/mine`
- `POST /api/payments/{enrollmentId}` with JSON `{"succeed":true|false}`
- Admin: `GET /api/admin/stats`, `GET|POST /api/admin/events`, `PATCH /api/admin/events/{id}/cancel`, `GET /api/admin/enrollments`

## Verification

```powershell
cd .\frontend
npm run build
cd ..\backend
mvn test -DforkCount=0
```

Payment is intentionally simulated. Backend authorization is enforced on protected APIs and admin operations; frontend route checks are for navigation and UX only.


