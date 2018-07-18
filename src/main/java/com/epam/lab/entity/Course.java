package com.epam.lab.entity;

public class Course {
    private int id;
    private String name;
    private String description;
    private int teacherId;

    public Course() {
    }

    public Course(int id, String name, String description, int teacherId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.teacherId = teacherId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", teacherId=" + teacherId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (getId() != course.getId()) return false;
        if (getTeacherId() != course.getTeacherId()) return false;
        if (!getName().equals(course.getName())) return false;
        return getDescription().equals(course.getDescription());
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getDescription().hashCode();
        result = 31 * result + getTeacherId();
        return result;
    }
}