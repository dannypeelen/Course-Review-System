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
INSERT INTO courses (course_number, title, description, rating, schedule, teacher_id) VALUES
('CS1530', 'Software Engineering', 'Learn software development methodologies and practices', 4, 'MWF 10:00-11:00', 1),
('CS1550', 'Operating Systems', 'Study of operating system concepts and implementation', 5, 'TTh 13:00-14:30', 2),
('CS1501', 'Algorithm Implementation', 'Advanced algorithms and data structures', 4, 'MWF 14:00-15:00', 3);

-- Insert sample reviews
INSERT INTO reviews (review_content, likes, dislikes, writer_id, course_id) VALUES
('Great course! Learned a lot about software development.', 10, 2, 1, 1),
('Very challenging but rewarding.', 8, 1, 2, 2),
('Excellent instructor and course material.', 15, 0, 3, 3),
('The project was very practical and useful.', 12, 3, 1, 1);
