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
docker-compose up -d

# View logs
docker-compose logs -f backend
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
```bash
# Stop services
docker-compose down

# Stop and remove data
docker-compose down -v
```

## Option 2: Local Development

### 1. Start PostgreSQL
```bash
docker run -d --name coursereview-db \
  -e POSTGRES_DB=coursereview \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -p 5432:5432 \
  postgres:16-alpine
```

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

### 1. Register a New Student
```bash
curl -X POST http://localhost:8080/api/students/register \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Alice Brown",
    "password": "mypassword123"
  }'
```

### 2. Login and Get Token
```bash
TOKEN=$(curl -X POST http://localhost:8080/api/auth/login/student \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Alice Brown",
    "password": "mypassword123"
  }' | jq -r '.token')

echo $TOKEN
```

### 3. Create a Review (Authenticated)
```bash
curl -X POST http://localhost:8080/api/reviews \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer $TOKEN" \
  -d '{
    "courseId": 1,
    "studentId": 1,
    "content": "This is an excellent course!"
  }'
```

### 4. View Reviews for a Course
```bash
curl http://localhost:8080/api/reviews/course/1
```

### 5. Like a Review (Authenticated)
```bash
curl -X POST http://localhost:8080/api/reviews/1/like \
  -H "Authorization: Bearer $TOKEN"
```

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
```bash
# Connect to database
docker exec -it coursereview-db psql -U postgres -d coursereview

# List tables
\dt

# View students
SELECT * FROM students;

# Exit
\q
```

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
