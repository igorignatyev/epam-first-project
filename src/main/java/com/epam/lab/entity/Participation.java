package com.epam.lab.entity;

public class Participation extends Entity {
    private int id;
    private int studentId;
    private int courseId;

    public Participation(int studentId, int courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public Participation(int id, int studentId, int courseId) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Participation that = (Participation) o;

        if (getId() != that.getId()) return false;
        if (getStudentId() != that.getStudentId()) return false;
        return getCourseId() == that.getCourseId();
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getStudentId();
        result = 31 * result + getCourseId();
        return result;
    }

    @Override
    public String toString() {
        return "Participation{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                '}';
    }
}
