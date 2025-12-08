# Course Review System

## Introduction
As college students, we understand the stress of course selection every semester. From navigating an overwhelming list of options to figuring out which professors and classes best fit your learning style, this process can be challenging for many reasons. Our Course Review System aims to simplify this experience by providing a platform where students can share honest reviews, ratings, and insights about their courses. With this system, you can make informed decisions, plan your schedule effectively, and ultimately have a more rewarding academic experience.

## Current Functionality
- **University Scope:** Currently supports data for a single university (using a fake database).  
- **Course Browsing:** Users can browse all Computer Science courses.  
- **Course Details Page:** Selecting a course opens a detailed page showing:
  - Course name, code, professor, and credits  
  - Overall rating  
  - Recent student reviews  
  - Workload information, including difficulty, estimated hours per week, number of exams, and number of projects  
- **Submit Reviews:** Users can write their own reviews and are prompted to provide ratings for various aspects of the course.

## Planned Features / Future Functionality
- **Multi-University Support:** Expand the system to include real data from 200+ universities.  
- **Course Listings:** Include as many subjects as possible.  
- **Verified User Registration:** Allow users to register through their university, ensuring only verified students can submit reviews.  
- **Review Voting:** Enable upvoting and downvoting of reviews to highlight helpful feedback.  
- **Search Functionality:** Allow users to quickly find courses by name, code, or professor.  
- **Course Filtering:** Let users filter courses by difficulty, workload, or average rating.  
- **User Profiles:** Users can view their profile to see previously viewed courses and their submitted reviews.

## Creators
Danny Cohen, Danny Peelen, David Altman, Griffin Holcombe, Nathan Barton

## Quick Start Guide

### Prerequisites
- Docker and Docker Compose installed
- OR Java 17+ and PostgreSQL 16+ for local development

### Option 1: Docker Compose

This will run both the PostgreSQL database and Spring Boot backend in containers.

### Start the Application
```bash
# Navigate to project root
cd course-review-system

# Start all services (database + backend)
docker-compose up -d

Verify It's Running

Backend API: http://localhost:8080

Database: localhost:5432

# Get all courses (no auth required)
curl http://localhost:8080/api/courses
```

The API will be available at http://localhost:8080

### Stop the Application
```bash
# Stop services
docker-compose down

# Stop and remove data
docker-compose down -v
```
### Option 2: Local Development

#### Step 1: Start PostgreSQL Database

**Option A: Using Docker**
```bash
docker run -d \
  --name coursereview-db \
  -e POSTGRES_DB=coursereview \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -p 5432:5432 \
  postgres:16-alpine
```
**Option B: Using Local PostgreSQL**
```bash
# Create database
createdb coursereview

# Or using psql
psql -U postgres -c "CREATE DATABASE coursereview;"
```

#### Step 2. Run the Backend
```bash
cd backend
./gradlew bootRun
```

#### Step 3: Run Frontend

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

### Rebuild After Code Changes
```bash
# Rebuild and restart backend
docker-compose up -d --build backend
```
## API Endpoints Overview
- `GET /api/courses` - List all courses
- `GET /api/courses/{id}` - Get course details
- `POST /api/reviews` - Create review
