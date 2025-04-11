package com.ces3.exam.pitagorasapi.model;

import java.util.ArrayList;

public class Course {
    private int id;
    private String name;
    private String code;
    private String teacher;
    private int maxCapacity;
    private int enrolledStudents;
    private String faculty;
    private ArrayList<String> prerequisites;
    private int level;
    private String startDate;

    public Course(int id, String name, String code, String teacher, int maxCapacity, int enrolledStudents, String faculty, ArrayList<String> prerequisites, int level, String startDate) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.teacher = teacher;
        this.maxCapacity = maxCapacity;
        this.enrolledStudents = enrolledStudents;
        this.faculty = faculty;
        this.prerequisites = prerequisites;
        this.level = level;
        this.startDate = startDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setEnrolledStudents(int enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public ArrayList<String> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(ArrayList<String> prerequisites) {
        this.prerequisites = prerequisites;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", teacher='" + teacher + '\'' +
                ", maxCapacity=" + maxCapacity +
                ", enrolledStudents=" + enrolledStudents +
                ", faculty='" + faculty + '\'' +
                ", prerequisites=" + prerequisites +
                ", level=" + level +
                ", startDate='" + startDate + '\'' +
                '}';
    }
}
