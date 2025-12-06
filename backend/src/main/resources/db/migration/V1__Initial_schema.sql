-- Create teachers table
CREATE TABLE teachers (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Create students table
CREATE TABLE students (
    user_id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    password VARCHAR(255)
);

-- Create administrators table
CREATE TABLE administrators (
    user_id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    password VARCHAR(255),
    monitor BOOLEAN DEFAULT FALSE
);

-- Create courses table
CREATE TABLE courses (
    course_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    course_name VARCHAR(200) NOT NULL,
    course_code VARCHAR(50) NOT NULL,
    number_of_credits INT NOT NULL,
    description TEXT,
    professor VARCHAR(200),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create reviews table
CREATE TABLE reviews (
    review_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    course_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    review TEXT NOT NULL,
    rating INT CHECK (rating BETWEEN 1 AND 5),
    difficulty INT CHECK (difficulty BETWEEN 1 AND 5),
    time_commitment INT CHECK (time_commitment >= 0),
    number_of_exams INT CHECK (number_of_exams >= 0),
    number_of_projects INT CHECK (number_of_projects >= 0),
    FOREIGN KEY (course_id) REFERENCES courses(course_id) ON DELETE CASCADE
);

-- Create reports table
CREATE TABLE reports (
    id BIGSERIAL PRIMARY KEY,
    content VARCHAR(2000),
    confirmed BOOLEAN DEFAULT FALSE,
    review_id INT,
    CONSTRAINT fk_review FOREIGN KEY (review_id) REFERENCES reviews(review_id) ON DELETE CASCADE
);

-- Create indexes for better query performance
CREATE INDEX idx_courses_code ON courses(course_code);
CREATE INDEX idx_courses_name ON courses(course_name);
CREATE INDEX idx_reviews_course ON reviews(course_id);
CREATE INDEX idx_reports_confirmed ON reports(confirmed);
CREATE INDEX idx_students_name ON students(name);
CREATE INDEX idx_administrators_name ON administrators(name);
