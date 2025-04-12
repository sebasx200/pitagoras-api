package com.ces3.exam.pitagorasapi.service;

import com.ces3.exam.pitagorasapi.dao.CourseDAO;
import com.ces3.exam.pitagorasapi.model.Course;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestScoped
public class CourseService {

    @Inject
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
        if (course.getPrerequisites() != null && !course.getPrerequisites().isEmpty()) {
            List<String> invalidPrereqs = course.getPrerequisites().stream()
                    .filter(code -> courseDAO.getAll().stream()
                            .noneMatch(existing -> existing.getCode().equalsIgnoreCase(code)))
                    .collect(Collectors.toList());

            if (!invalidPrereqs.isEmpty()) {
                throw new IllegalArgumentException("Invalid prerequisites: " + invalidPrereqs);
            }
        }

        courseDAO.save(course);
    }

    public List<Course> getAllCourses() {
        return this.courseDAO.getAll();
    }

    public Optional<Course> getCourseById(int id) {
        if(!courseDAO.getById(id).isPresent()) {
            throw new NoSuchElementException("Course does not exist");
        }
        return courseDAO.getById(id);
    }

    public List<Course> getCourseByFaculty(String faculty) {
        List<Course> courses = courseDAO.getByFaculty(faculty);
        if (courses.isEmpty()) {
            throw new NoSuchElementException("No courses found for faculty: " + faculty);
        }
        return courses;
    }
    public List<Course> getPrerequisiteCoursesByCode(String courseCode) {
        Optional<Course> targetCourse = courseDAO.getAll().stream()
                .filter(c -> c.getCode().equalsIgnoreCase(courseCode))
                .findFirst();

        if (!targetCourse.isPresent()) {
            throw new NoSuchElementException("Course with code " + courseCode + " not found");
        }
        List<String> prerequisites = targetCourse.get().getPrerequisites();
        return courseDAO.getAll().stream()
                .filter(c -> prerequisites.contains(c.getCode()))
                .sorted(Comparator.comparingInt(Course::getLevel))
                .collect(Collectors.toList());
    }
}
