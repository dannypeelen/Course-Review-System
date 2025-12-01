# Quick Start Guide

## Docker Compose (Recommended)

```bash
# Start database and backend
docker-compose up -d

# View logs
docker-compose logs -f backend

# Test it works
curl http://localhost:8080/api/courses

# Stop
docker-compose down
```

## Local Development

```bash
# Start database
docker run -d --name coursereview-db \
  -e POSTGRES_DB=coursereview \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -p 5432:5432 \
  postgres:16-alpine

# Run backend
cd backend && ./gradlew bootRun

# Run frontend (optional)
cd frontend && npm install && ng serve
```

## Default Credentials

- **Admin:** admin / admin123
- **Student:** John Doe / password123

## Test the API

```bash
# Login
curl -X POST http://localhost:8080/api/auth/login/student \
  -H "Content-Type: application/json" \
  -d '{"name":"John Doe","password":"password123"}'

# Create review (use token from login)
curl -X POST http://localhost:8080/api/reviews \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_TOKEN" \
  -d '{"courseId":1,"studentId":1,"content":"Great course!"}'
```

See [README.md](README.md) for full documentation.
