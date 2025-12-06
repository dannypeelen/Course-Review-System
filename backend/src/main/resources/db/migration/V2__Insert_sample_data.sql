-- Insert sample teachers
INSERT INTO teachers (name) VALUES
('Dr. Smith'),
('Prof. Johnson'),
('Dr. Williams');

-- Insert sample administrator (password: admin123)
INSERT INTO administrators (name, password, monitor) VALUES
('admin', '$2a$10$xN3kC3zLvC5T0oqXJ0dGW.3LYvNNL0Z2P5CyxRjzHYYPxKq4zxYLS', true);

-- Insert sample students (password: password123)
INSERT INTO students (name, password) VALUES
('John Doe', '$2a$10$xN3kC3zLvC5T0oqXJ0dGW.3LYvNNL0Z2P5CyxRjzHYYPxKq4zxYLS'),
('Jane Smith', '$2a$10$xN3kC3zLvC5T0oqXJ0dGW.3LYvNNL0Z2P5CyxRjzHYYPxKq4zxYLS'),
('Bob Johnson', '$2a$10$xN3kC3zLvC5T0oqXJ0dGW.3LYvNNL0Z2P5CyxRjzHYYPxKq4zxYLS');

-- Insert sample courses
INSERT INTO courses (course_name, course_code, number_of_credits, description, professor) VALUES
('Introduction to Computer Science', 'CS101', 3, 'Basic principles of programming and algorithms.', 'Dr. Alice Nguyen'),
('Data Structures & Algorithms', 'CS201', 4, 'In-depth study of data structures and efficient algorithms.', 'Dr. Robert Hale'),
('Database Systems', 'CS330', 3, 'Relational databases, SQL, transactions, indexing.', 'Dr. Maria Lopez'),
('Operating Systems', 'CS350', 4, 'Processes, threads, scheduling, and memory management.', 'Dr. Kevin Patel'),
('Software Engineering', 'CS440', 3, 'Software design, patterns, testing, and team development.', 'Prof. Emily Chen'),
('Discrete Mathematics', 'MATH210', 3, 'Logic, sets, combinatorics, and proofs.', 'Dr. Hannah Brooks'),
('Machine Learning', 'CS470', 3, 'Supervised and unsupervised machine learning models.', 'Dr. Samuel Price'),
('Computer Networks', 'CS360', 3, 'Network protocols, routing, and distributed systems.', 'Dr. Laura Bennett'),
('Linear Algebra', 'MATH240', 3, 'Vectors, matrices, eigenvalues, and linear transformations.', 'Dr. Isaac Romero'),
('Introduction to Psychology', 'PSY101', 3, 'Foundations of human behavior and mental processes.', 'Dr. Olivia Carter');

-- Insert sample reviews
INSERT INTO reviews (course_id, review, rating, difficulty, time_commitment, number_of_exams, number_of_projects) VALUES
-- CS101
(1, 'Great intro class. Very beginner-friendly.', 5, 2, 5, 2, 1),
(1, 'Pretty easy but interesting. Lectures were clear.', 4, 2, 4, 1, 1),
-- CS201
(2, 'Challenging but rewarding. Data structures finally make sense.', 4, 4, 10, 2, 3),
(2, 'Tough exams but the projects were fun.', 3, 5, 12, 3, 2),
-- CS330
(3, 'Loved learning SQL. Homework was manageable.', 5, 3, 6, 1, 2),
(3, 'Interesting material but lectures were dry.', 3, 3, 7, 2, 1),
-- CS350
(4, 'Very difficult. The midterm destroyed me.', 2, 5, 14, 2, 2),
(4, 'Learned a lot about how OS works. Hard but worth it.', 4, 5, 15, 2, 2),
-- CS440
(5, 'Group project was a nightmare. Otherwise good.', 3, 3, 8, 1, 2),
(5, 'Solid overview of software engineering practices.', 4, 2, 6, 1, 1),
-- MATH210
(6, 'Lots of proofs. Prepare to struggle.', 3, 4, 12, 2, 0),
(6, 'Good class but heavy weekly homework.', 4, 4, 10, 1, 0),
-- CS470
(7, 'Amazing class. ML is super interesting.', 5, 4, 12, 1, 3),
(7, 'Hard to follow if you don''t know linear algebra well.', 3, 5, 14, 1, 2),
-- CS360
(8, 'Networks is fun. Labs take forever though.', 4, 4, 10, 2, 2),
(8, 'Loved the packet sniffing assignments.', 5, 3, 8, 1, 2),
-- MATH240
(9, 'Linear algebra is tough but useful.', 4, 4, 10, 2, 0),
(9, 'The professor explains clearly. Great course.', 5, 3, 8, 1, 0),
-- PSY101
(10, 'Easy class. Interesting material.', 5, 2, 4, 1, 0),
(10, 'Psych101 is a chill elective. Highly recommended.', 5, 1, 3, 1, 0);
