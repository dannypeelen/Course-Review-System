<<<<<<< HEAD
# Quick Start Guide

## Prerequisites
- Docker and Docker Compose installed
- OR Java 17+ and PostgreSQL 16+ for local development

## Option 1: Docker Compose (Recommended)

### 1. Start the Application
```bash
# Clone or navigate to the project
cd course-review-system

# Start all services (database + backend)
=======
# Course Review System

A full-stack application for students to review courses, built with Spring Boot (backend), Angular (frontend), and PostgreSQL (database).

## Features

- Student registration and authentication (JWT-based)
- Course browsing and search
- Review submission with like/dislike functionality
- Report inappropriate reviews
- Administrator panel for content moderation
- Teacher management (admin only)
- Secure password hashing with BCrypt
- RESTful API with role-based access control

## Tech Stack

**Backend:**
- Java 17
- Spring Boot 3.5.7
- Spring Security with JWT
- PostgreSQL 16
- Flyway (database migrations)
- Gradle

**Frontend:**
- Angular
- TypeScript

## Prerequisites

- **For Docker:** Docker and Docker Compose
- **For Local Development:** Java 17+, Gradle, PostgreSQL 16+

## Quick Start

### Option 1: Docker Compose (Recommended)

This will run both the PostgreSQL database and Spring Boot backend in containers.

```bash
# Navigate to project root
cd course-review-system

# Start all services
>>>>>>> 5ff3c48 (extra changes for docker)
docker-compose up -d

# View logs
docker-compose logs -f backend
<<<<<<< HEAD
```

### 2. Verify It's Running
- Backend API: http://localhost:8080
- Database: localhost:5432

### 3. Test the API
```bash
# Login as student
curl -X POST http://localhost:8080/api/auth/login/student \
  -H "Content-Type: application/json" \
  -d '{"name":"John Doe","password":"password123"}'

# Get all courses (no auth required)
curl http://localhost:8080/api/courses
```

### 4. Stop the Application
=======

# The API will be available at http://localhost:8080
```

To stop:
>>>>>>> 5ff3c48 (extra changes for docker)
```bash
# Stop services
docker-compose down

<<<<<<< HEAD
# Stop and remove data
docker-compose down -v
```

## Option 2: Local Development

### 1. Start PostgreSQL
```bash
docker run -d --name coursereview-db \
=======
# Stop and remove all data (clean slate)
docker-compose down -v
```

### Option 2: Local Development

#### Step 1: Start PostgreSQL Database

**Option A: Using Docker (recommended)**
```bash
docker run -d \
  --name coursereview-db \
>>>>>>> 5ff3c48 (extra changes for docker)
  -e POSTGRES_DB=coursereview \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -p 5432:5432 \
  postgres:16-alpine
```

<<<<<<< HEAD
### 2. Run the Backend
```bash
cd backend
./gradlew bootRun
```

## Default Credentials

### Administrator
- Username: `admin`
- Password: `admin123`

### Students  
- Username: `John Doe`, Password: `password123`
- Username: `Jane Smith`, Password: `password123`
- Username: `Bob Johnson`, Password: `password123`

## API Endpoints Overview

### Public Endpoints (No Auth)
- `POST /api/auth/login/student` - Student login
- `POST /api/auth/login/admin` - Admin login
- `POST /api/students/register` - Register new student
- `GET /api/courses` - List all courses
- `GET /api/courses/{id}` - Get course details
- `GET /api/courses/search?keyword=...` - Search courses

### Protected Endpoints (Requires JWT)
- `POST /api/reviews` - Create review
- `GET /api/reviews/course/{id}` - Get reviews for course
- `POST /api/reviews/{id}/like` - Like review
- `POST /api/reviews/{id}/dislike` - Dislike review
- `GET /api/students/{id}/history` - Get student review history
- `POST /api/reports` - Submit report
- `GET /api/reports` - List reports (admin)

### Admin Only
- `POST /api/teachers` - Create teacher
- `PUT /api/teachers/{id}` - Update teacher
- `DELETE /api/teachers/{id}` - Delete teacher

## Example Workflow
=======
**Option B: Using local PostgreSQL installation**
```bash
# Create database
createdb coursereview

# Or using psql
psql -U postgres -c "CREATE DATABASE coursereview;"
```

#### Step 2: Run Backend

```bash
# Navigate to backend directory
cd backend

# Run the application
./gradlew bootRun

# Or on Windows
gradlew.bat bootRun
```

The backend will start on http://localhost:8080

#### Step 3: Run Frontend (Optional)

```bash
# Navigate to frontend directory
cd frontend

# Install dependencies (first time only)
npm install

# Start development server
ng serve

# Or using npm
npm start
```

The frontend will be available at http://localhost:4200

## Verifying the Setup

Once the backend is running, test it:

```bash
# Check health (get all courses)
curl http://localhost:8080/api/courses

# Login as a student
curl -X POST http://localhost:8080/api/auth/login/student \
  -H "Content-Type: application/json" \
  -d '{"name":"John Doe","password":"password123"}'
```

You should receive a JSON response with a JWT token.

## Default Credentials

### Administrator
- **Username:** `admin`
- **Password:** `admin123`

### Sample Students
All students have password: `password123`
- John Doe
- Jane Smith
- Bob Johnson

## Project Structure

```
course-review-system/
├── backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/cs1530/coursereview/
│   │   │   │   ├── controller/      # REST API endpoints
│   │   │   │   ├── service/         # Business logic
│   │   │   │   ├── repository/      # Data access layer
│   │   │   │   ├── model/           # JPA entities
│   │   │   │   ├── security/        # JWT & Security config
│   │   │   │   └── dto/             # Data transfer objects
│   │   │   └── resources/
│   │   │       ├── db/migration/    # Flyway SQL scripts
│   │   │       └── application.properties
│   ├── build.gradle
│   └── Dockerfile
├── frontend/
│   ├── src/
│   ├── package.json
│   └── angular.json
├── docker-compose.yml
├── .env.example
├── README.md
├── QUICKSTART.md
└── SECURITY_AND_DEPLOYMENT.md
```

## API Endpoints

### Public Endpoints (No Authentication Required)

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/auth/login/student` | Student login |
| POST | `/api/auth/login/admin` | Admin login |
| POST | `/api/students/register` | Register new student |
| GET | `/api/courses` | List all courses |
| GET | `/api/courses/{id}` | Get course details |
| GET | `/api/courses/search?keyword={keyword}` | Search courses |

### Protected Endpoints (Requires JWT Token)

**Reviews:**
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/reviews` | Create review |
| GET | `/api/reviews/course/{courseId}` | Get reviews for course |
| GET | `/api/reviews/{id}` | Get review by ID |
| POST | `/api/reviews/{id}/like` | Like a review |
| POST | `/api/reviews/{id}/dislike` | Dislike a review |
| DELETE | `/api/reviews/{id}` | Delete review |

**Students:**
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/students/{id}` | Get student by ID |
| GET | `/api/students/{id}/history` | Get review history |

**Reports:**
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/reports` | Submit report |
| GET | `/api/reports` | Get all reports (admin) |
| GET | `/api/reports/pending` | Get pending reports (admin) |
| GET | `/api/reports/confirmed` | Get confirmed reports (admin) |
| POST | `/api/reports/{id}/confirm` | Confirm report (admin) |
| DELETE | `/api/reports/{id}` | Delete report (admin) |

**Teachers (Admin Only):**
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/teachers` | List all teachers |
| GET | `/api/teachers/{id}` | Get teacher by ID |
| POST | `/api/teachers` | Create teacher |
| PUT | `/api/teachers/{id}` | Update teacher |
| DELETE | `/api/teachers/{id}` | Delete teacher |

## API Usage Examples
>>>>>>> 5ff3c48 (extra changes for docker)

### 1. Register a New Student
```bash
curl -X POST http://localhost:8080/api/students/register \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Alice Brown",
    "password": "mypassword123"
  }'
```

<<<<<<< HEAD
### 2. Login and Get Token
```bash
TOKEN=$(curl -X POST http://localhost:8080/api/auth/login/student \
=======
### 2. Login and Get JWT Token
```bash
curl -X POST http://localhost:8080/api/auth/login/student \
>>>>>>> 5ff3c48 (extra changes for docker)
  -H "Content-Type: application/json" \
  -d '{
    "name": "Alice Brown",
    "password": "mypassword123"
<<<<<<< HEAD
  }' | jq -r '.token')

echo $TOKEN
=======
  }'
```

Response:
```json
{
  "authenticated": true,
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "userId": 4,
  "name": "Alice Brown",
  "role": "STUDENT"
}
>>>>>>> 5ff3c48 (extra changes for docker)
```

### 3. Create a Review (Authenticated)
```bash
<<<<<<< HEAD
=======
# Save token from login response
TOKEN="your-jwt-token-here"

>>>>>>> 5ff3c48 (extra changes for docker)
curl -X POST http://localhost:8080/api/reviews \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{
    "courseId": 1,
<<<<<<< HEAD
    "studentId": 1,
    "content": "This is an excellent course!"
  }'
```

### 4. View Reviews for a Course
=======
    "studentId": 4,
    "content": "Excellent course! Highly recommend."
  }'
```

### 4. Get Reviews for a Course
>>>>>>> 5ff3c48 (extra changes for docker)
```bash
curl http://localhost:8080/api/reviews/course/1
```

### 5. Like a Review (Authenticated)
```bash
curl -X POST http://localhost:8080/api/reviews/1/like \
  -H "Authorization: Bearer $TOKEN"
```

<<<<<<< HEAD
## Troubleshooting

### Port Already in Use
```bash
# Check what's using port 8080
lsof -i :8080

# Or change the port in docker-compose.yml
ports:
  - "8081:8080"  # Use 8081 instead
```

### Database Connection Failed
```bash
# Check if PostgreSQL is running
docker ps | grep postgres

# Restart the database
docker-compose restart postgres
```

### Can't Login
- Verify you're using the correct credentials
- Check if Flyway migrations ran successfully
- View backend logs: `docker-compose logs backend`

## Development Tips

### View Database Contents
=======
## Database Management

### Viewing Database Contents

**Using Docker:**
>>>>>>> 5ff3c48 (extra changes for docker)
```bash
# Connect to database
docker exec -it coursereview-db psql -U postgres -d coursereview

# List tables
\dt

<<<<<<< HEAD
# View students
SELECT * FROM students;
=======
# View data
SELECT * FROM students;
SELECT * FROM courses;
SELECT * FROM reviews;
>>>>>>> 5ff3c48 (extra changes for docker)

# Exit
\q
```

<<<<<<< HEAD
### Rebuild After Code Changes
```bash
# Rebuild and restart backend
docker-compose up -d --build backend
```

### View Application Logs
```bash
# All services
docker-compose logs -f

# Just backend
docker-compose logs -f backend

# Last 100 lines
docker-compose logs --tail=100 backend
```

## Next Steps

1. Integrate with Angular frontend
2. Add more test data
3. Implement additional features
4. Set up proper production deployment

## Support

For more details, see:
- [SECURITY_AND_DEPLOYMENT.md](SECURITY_AND_DEPLOYMENT.md) - Complete security and deployment guide
- API documentation at http://localhost:8080/swagger-ui.html (if configured)
=======
### Resetting Database

**With Docker Compose:**
```bash
# Stop and remove volumes (deletes all data)
docker-compose down -v

# Restart (migrations will run again)
docker-compose up -d
```

**With Local PostgreSQL:**
```bash
# Drop and recreate database
dropdb coursereview
createdb coursereview

# Restart backend (Flyway will recreate schema)
cd backend && ./gradlew bootRun
```

## Environment Configuration

The application uses environment variables for configuration. See [.env.example](.env.example) for available options.

Key configurations:
- `DB_HOST` - Database host (default: localhost)
- `DB_PORT` - Database port (default: 5432)
- `DB_NAME` - Database name (default: coursereview)
- `DB_USER` - Database username (default: postgres)
- `DB_PASSWORD` - Database password (default: postgres)
- `JWT_SECRET` - Secret key for JWT signing
- `JWT_EXPIRATION` - Token expiration time in milliseconds (default: 86400000 = 24 hours)

## Troubleshooting

### Port Already in Use
```bash
# Check what's using port 8080
lsof -i :8080

# Kill the process or change port in application.properties
server.port=8081
```

### Database Connection Failed
```bash
# Check PostgreSQL is running
docker ps | grep postgres

# View logs
docker logs coursereview-db

# Restart database
docker restart coursereview-db
```

### Backend Won't Start
```bash
# Clean and rebuild
cd backend
./gradlew clean build

# Check logs for errors
./gradlew bootRun --stacktrace
```

### Flyway Migration Errors
```bash
# If schema is out of sync, clean database
docker-compose down -v
docker-compose up -d

# Or manually reset Flyway
# Connect to DB and: DROP TABLE flyway_schema_history;
```

## Development

### Building

```bash
# Build backend
cd backend
./gradlew build

# Build Docker image
docker build -t coursereview-backend .
```

### Running Tests

```bash
cd backend
./gradlew test
```

## Security Features

- **Password Encryption:** BCrypt hashing with salt
- **JWT Authentication:** Stateless token-based auth
- **Role-Based Access Control:** Student vs Administrator roles
- **CORS Configuration:** Configured for frontend integration
- **SQL Injection Prevention:** JPA/Hibernate parameterized queries
- **Input Validation:** Spring Validation on DTOs

## Additional Documentation

- [QUICKSTART.md](QUICKSTART.md) - Detailed quick start guide
- [SECURITY_AND_DEPLOYMENT.md](SECURITY_AND_DEPLOYMENT.md) - Security configuration and deployment details

## Contributing

1. Create a feature branch
2. Make changes
3. Run tests
4. Submit pull request

## License

[Add your license here]

## Support

For issues and questions, please create an issue in the repository.
>>>>>>> 5ff3c48 (extra changes for docker)
