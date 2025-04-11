package com.ces3.exam.pitagorasapi.dao;

import com.ces3.exam.pitagorasapi.model.Course;

import java.util.List;
import java.util.Optional;

public interface CourseDAO {

    Optional<Course> getById(int id);

    List<Course> getAll();

    void save(Course course);

    void update(Course course);

    void delete(int id);

    List<Course> getByFaculty(String faculty);

    List<Course> getByPrerequisite(String prerequisite);
}
