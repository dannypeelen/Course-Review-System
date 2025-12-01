# Security Configuration and Deployment Guide

## Overview
This document describes the security improvements and deployment configurations added to the Course Review System.

## Security Improvements

### 1. Password Encryption
- **Implementation**: BCrypt password encoding
- **Location**: `SecurityConfig.java:25-27`
- **Impact**: All passwords are now hashed using BCrypt before storage
- **Migration**: Sample data includes pre-hashed passwords (password: `password123` for students, `admin123` for admin)

### 2. JWT Authentication
- **Token Type**: JSON Web Tokens (JWT)
- **Algorithm**: HS256
- **Expiration**: 24 hours (configurable via `jwt.expiration`)
- **Components**:
  - `JwtUtil.java` - Token generation and validation
  - `JwtAuthenticationFilter.java` - Request authentication filter
  - `AuthRequest.java` / `AuthResponse.java` - DTOs for auth endpoints

### 3. Spring Security Configuration
- **File**: `SecurityConfig.java`
- **Features**:
  - Stateless session management (JWT-based)
  - CORS configuration for frontend integration
  - Role-based access control (RBAC)
  - Public endpoints: `/api/auth/**`, `/api/students/register`, `/api/courses/**`
  - Protected endpoints: Reviews, Reports, Student account operations
  - Admin-only endpoints: Teacher management

### 4. Role-Based Access Control
- **Roles**:
  - `STUDENT`: Can create reviews, submit reports, view own history
  - `ADMINISTRATOR`: Full access including teacher CRUD operations

## Database Configuration

### 1. Flyway Migrations
- **Location**: `src/main/resources/db/migration/`
- **Migrations**:
  - `V1__Initial_schema.sql` - Creates all tables with proper constraints
  - `V2__Insert_sample_data.sql` - Adds sample data for testing

### 2. Database Schema
- **Tables**: administrators, students, teachers, courses, reviews, reports
- **Indexes**: Added for performance on frequently queried columns
- **Foreign Keys**: Proper referential integrity with CASCADE rules

### 3. Configuration
- **File**: `application.properties`
- **DDL Mode**: `validate` - Flyway manages schema changes
- **Connection**: Environment variable support for Docker deployment

## Docker Deployment

### 1. Docker Compose Setup
- **File**: `docker-compose.yml`
- **Services**:
  - `postgres`: PostgreSQL 16 database with health checks
  - `backend`: Spring Boot application
  - `frontend`: Angular application (placeholder)
- **Networks**: Isolated `coursereview-network`
- **Volumes**: Persistent database storage

### 2. Backend Dockerfile
- **Multi-stage build**: Optimized for size
- **Base Images**:
  - Build: `gradle:8.5-jdk17-alpine`
  - Runtime: `eclipse-temurin:17-jre-alpine`
- **Port**: 8080

### 3. Running with Docker

```bash
# Start all services
docker-compose up -d

# View logs
docker-compose logs -f backend

# Stop all services
docker-compose down

# Remove volumes (clean database)
docker-compose down -v
```

## Environment Variables

### Required Variables
- `DB_HOST` - Database host (default: localhost)
- `DB_PORT` - Database port (default: 5432)
- `DB_NAME` - Database name (default: coursereview)
- `DB_USER` - Database user (default: postgres)
- `DB_PASSWORD` - Database password (default: postgres)
- `JWT_SECRET` - Secret key for JWT signing (min 256 bits)
- `JWT_EXPIRATION` - Token expiration in milliseconds (default: 86400000 = 24h)

### Configuration File
- Copy `.env.example` to `.env` for local development
- Modify values as needed for production

## API Authentication

### Login Endpoints

#### Student Login
```bash
POST /api/auth/login/student
Content-Type: application/json

{
  "name": "John Doe",
  "password": "password123"
}

Response:
{
  "authenticated": true,
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "userId": 1,
  "name": "John Doe",
  "role": "STUDENT"
}
```

#### Administrator Login
```bash
POST /api/auth/login/admin
Content-Type: application/json

{
  "name": "admin",
  "password": "admin123"
}

Response:
{
  "authenticated": true,
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "userId": 1,
  "name": "admin",
  "role": "ADMINISTRATOR"
}
```

### Using JWT Tokens

Include the token in the Authorization header:
```bash
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

## Sample Credentials

### Administrator
- Username: `admin`
- Password: `admin123`

### Students
- Username: `John Doe`, Password: `password123`
- Username: `Jane Smith`, Password: `password123`
- Username: `Bob Johnson`, Password: `password123`

## Development Setup

### Local Development (without Docker)

1. Start PostgreSQL:
```bash
docker run -d \
  --name coursereview-postgres \
  -e POSTGRES_DB=coursereview \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -p 5432:5432 \
  postgres:16-alpine
```

2. Run the backend:
```bash
cd backend
./gradlew bootRun
```

### With Docker Compose

```bash
docker-compose up -d
```

## Security Best Practices

### For Production

1. **Change Default Credentials**: Update all default passwords
2. **Generate Strong JWT Secret**: Use a cryptographically secure random string
3. **Use HTTPS**: Configure SSL/TLS certificates
4. **Environment Variables**: Never commit secrets to version control
5. **Database Security**: Use strong passwords and restrict network access
6. **Update Dependencies**: Regularly update Spring Boot and other dependencies
7. **Enable Rate Limiting**: Protect against brute force attacks
8. **Audit Logging**: Implement comprehensive logging for security events

### Recommendations

1. Add refresh tokens for better security
2. Implement account lockout after failed login attempts
3. Add password complexity requirements
4. Implement CSRF protection for browser-based clients
5. Add API rate limiting
6. Implement proper session management
7. Add security headers (HSTS, CSP, etc.)

## Files Added/Modified

### New Files
- `src/main/java/com/cs1530/coursereview/security/JwtUtil.java`
- `src/main/java/com/cs1530/coursereview/security/JwtAuthenticationFilter.java`
- `src/main/java/com/cs1530/coursereview/security/SecurityConfig.java`
- `src/main/java/com/cs1530/coursereview/dto/AuthRequest.java`
- `src/main/java/com/cs1530/coursereview/dto/AuthResponse.java`
- `src/main/resources/db/migration/V1__Initial_schema.sql`
- `src/main/resources/db/migration/V2__Insert_sample_data.sql`
- `backend/Dockerfile`
- `backend/.dockerignore`
- `docker-compose.yml`
- `.env.example`

### Modified Files
- `backend/build.gradle` - Added JWT and Flyway dependencies
- `backend/src/main/resources/application.properties` - Database and JWT configuration
- `backend/src/main/java/com/cs1530/coursereview/model/User.java` - Password encoding support
- `backend/src/main/java/com/cs1530/coursereview/service/StudentService.java` - Password hashing
- `backend/src/main/java/com/cs1530/coursereview/controller/AuthController.java` - JWT integration

## Testing

### Testing Authentication

```bash
# Register a new student
curl -X POST http://localhost:8080/api/students/register \
  -H "Content-Type: application/json" \
  -d '{"name":"Test User","password":"testpass123"}'

# Login
curl -X POST http://localhost:8080/api/auth/login/student \
  -H "Content-Type: application/json" \
  -d '{"name":"Test User","password":"testpass123"}'

# Use token for authenticated request
TOKEN="your-jwt-token-here"
curl -X GET http://localhost:8080/api/students/1/history \
  -H "Authorization: Bearer $TOKEN"
```

## Troubleshooting

### Database Connection Issues
- Check if PostgreSQL is running: `docker ps`
- Verify connection details in `application.properties`
- Check logs: `docker-compose logs postgres`

### JWT Token Issues
- Verify JWT_SECRET is at least 256 bits (32 characters)
- Check token expiration time
- Ensure Authorization header format: `Bearer <token>`

### Flyway Migration Errors
- Check database state: `docker exec -it coursereview-db psql -U postgres -d coursereview`
- Clean and restart: `docker-compose down -v && docker-compose up -d`

## Next Steps

1. Implement frontend authentication integration
2. Add refresh token mechanism
3. Implement password reset functionality
4. Add email verification for new accounts
5. Implement comprehensive audit logging
6. Add API documentation (Swagger/OpenAPI)
7. Set up CI/CD pipeline
8. Configure production-ready database backups
