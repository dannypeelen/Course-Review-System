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
    id BIGSERIAL PRIMARY KEY,
    course_number VARCHAR(255) NOT NULL UNIQUE,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(2000),
    rating INTEGER,
    schedule VARCHAR(255),
    teacher_id BIGINT,
    CONSTRAINT fk_teacher FOREIGN KEY (teacher_id) REFERENCES teachers(id) ON DELETE SET NULL
);

-- Create reviews table
CREATE TABLE reviews (
    id BIGSERIAL PRIMARY KEY,
    review_content VARCHAR(2000),
    likes INTEGER DEFAULT 0,
    dislikes INTEGER DEFAULT 0,
    writer_id BIGINT,
    course_id BIGINT,
    CONSTRAINT fk_writer FOREIGN KEY (writer_id) REFERENCES students(user_id) ON DELETE CASCADE,
    CONSTRAINT fk_course FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE
);

-- Create reports table
CREATE TABLE reports (
    id BIGSERIAL PRIMARY KEY,
    content VARCHAR(2000),
    confirmed BOOLEAN DEFAULT FALSE,
    review_id BIGINT,
    CONSTRAINT fk_review FOREIGN KEY (review_id) REFERENCES reviews(id) ON DELETE CASCADE
);

-- Create indexes for better query performance
CREATE INDEX idx_courses_teacher ON courses(teacher_id);
CREATE INDEX idx_courses_title ON courses(title);
CREATE INDEX idx_reviews_course ON reviews(course_id);
CREATE INDEX idx_reviews_writer ON reviews(writer_id);
CREATE INDEX idx_reports_review ON reports(review_id);
CREATE INDEX idx_reports_confirmed ON reports(confirmed);
CREATE INDEX idx_students_name ON students(name);
CREATE INDEX idx_administrators_name ON administrators(name);
