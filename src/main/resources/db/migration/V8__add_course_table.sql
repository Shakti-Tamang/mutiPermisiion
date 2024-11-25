CREATE TABLE  courses(
 course_id VARCHAR(255) PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    course_code VARCHAR(255) NOT NULL,
    course_description TEXT NOT NULL,
    mode_of_delivery VARCHAR(255) NOT NULL
);