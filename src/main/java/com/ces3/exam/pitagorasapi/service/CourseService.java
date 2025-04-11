package com.ces3.exam.pitagorasapi.service;

import com.ces3.exam.pitagorasapi.dao.CourseDAO;
import com.ces3.exam.pitagorasapi.model.Course;

public class CourseService {
    private CourseDAO courseDAO;

    public void createCourse(Course course) {
        if (courseDAO.getById(course.getId()).isPresent()) {
            throw new IllegalArgumentException("Course with ID " + course.getId() + " already exists");
        }
        if (course.getMaxCapacity() < 1) {
            throw new IllegalArgumentException("Course capacity must be greater than 0");
        }
        boolean codeExists = courseDAO.getAll().stream()
                .anyMatch(c -> c.getCode().equalsIgnoreCase(course.getCode()));

        if (codeExists) {
            throw new IllegalArgumentException("Course with code " + course.getCode() + " already exists");
        }
        courseDAO.save(course);
    }
}
