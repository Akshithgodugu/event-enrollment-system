CREATE DATABASE IF NOT EXISTS event_enrollment_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE event_enrollment_db;
-- Hibernate creates and evolves the four related tables on first backend start.
-- Three published events and the local admin user are seeded by Spring Boot at startup.
