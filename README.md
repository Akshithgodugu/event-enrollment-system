# Gather — Web-Based Event Enrollment System

A full stack event enrollment application built with React and Vite, Java 21 and Spring Boot, Spring Web, Spring Data JPA, Spring Security, PostgreSQL, Maven, and npm. The UI uses responsive CSS with a navy and light-blue palette.

## Requirements

- Windows 10/11
- Java 21, Maven 3.9+, Node.js 20+ with npm, and PostgreSQL 15+

## Database setup

Start PostgreSQL, create a database named `event_enrollment_db`, then configure the backend. For local PowerShell, the default database username and password are both `postgres`. Override them if your local setup differs:

```powershell
$env:DB_USERNAME = "postgres"
$env:DB_PASSWORD = "your-postgres-password"
# Optional full JDBC URL override:
$env:DB_URL = "jdbc:postgresql://localhost:5432/event_enrollment_db"
```

JPA creates/updates the `users`, `events`, `enrollments`, and `payments` tables and their keys and relationships. At first launch, Spring seeds three upcoming events and a local administrator account.

**Local admin credentials:** `admin@gather.local` / `Admin123!`. For deployment, set `ADMIN_PASSWORD` to a private password before the database is initialized.

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

## Render demo deployment

This repository is a monorepo. Deploy the frontend as a Render Static Site with root directory `frontend`, build command `npm install && npm run build`, and publish directory `dist`. Deploy the backend as a Render Web Service using Docker, with root directory `backend` and Dockerfile `Dockerfile`.

Create a Render PostgreSQL database for the demo (the free database expires after 30 days). Configure the backend service with `DB_URL` (`jdbc:postgresql://<internal-host>:5432/<database-name>`), `DB_USERNAME`, `DB_PASSWORD`, `FRONTEND_URL` (the static site's `https://...onrender.com` URL), `SESSION_COOKIE_SECURE=true`, and a private `ADMIN_PASSWORD`. Configure the static site with `VITE_API_URL` set to the backend's `https://...onrender.com/api` URL, then redeploy it. Keep both services and the database in the same Render region. Render's free Postgres limits are documented at https://render.com/docs/free.

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


