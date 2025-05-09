package com.ces3.exam.pitagorasapi.dao;

import com.ces3.exam.pitagorasapi.model.Course;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class CourseDAOImpl implements CourseDAO {

    private final ArrayList<Course> courses = new ArrayList<>();

    @Override
    public Optional<Course> getById(int id) {
        return this.courses.stream().filter(c -> c.getId() == id).findFirst();
    }

    @Override
    public List<Course> getAll() {
        return new ArrayList<>(this.courses);
    }

    @Override
    public void save(Course course) {
        this.courses.add(course);
    }

    @Override
    public void update(Course course) {
        this.courses.set(this.courses.indexOf(course), course);
    }

    @Override
    public void delete(int id) {
        this.courses.removeIf(c -> c.getId() == id);
    }

    @Override
    public List<Course> getByFaculty(String faculty) {
        return this.courses.stream()
                .filter(c -> c.getFaculty().toLowerCase().contains(faculty.toLowerCase()))
                .collect(Collectors.toList());
    }
    @Override
    public List<Course> getByPrerequisite(String prerequisiteCode) {
        return this.courses.stream()
                .filter(c -> c.getPrerequisites() != null &&
                        c.getPrerequisites().stream()
                                .anyMatch(code -> code.equalsIgnoreCase(prerequisiteCode)))
                .sorted(Comparator.comparingInt(Course::getLevel))
                .collect(Collectors.toList());
    }

}
