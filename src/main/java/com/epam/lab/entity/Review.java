package com.epam.lab.entity;

public class Review {
    private int id;
    private String feedback;
    private int mark;
    private int participationId;

    public Review(int id, String feedback, int mark, int participationId) {
        this.id = id;
        this.feedback = feedback;
        this.mark = mark;
        this.participationId = participationId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public int getParticipationId() {
        return participationId;
    }

    public void setParticipationId(int participationId) {
        this.participationId = participationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Review review = (Review) o;

        if (getId() != review.getId()) return false;
        if (getMark() != review.getMark()) return false;
        if (getParticipationId() != review.getParticipationId()) return false;
        return getFeedback() != null ? getFeedback().equals(review.getFeedback()) : review.getFeedback() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getFeedback() != null ? getFeedback().hashCode() : 0);
        result = 31 * result + getMark();
        result = 31 * result + getParticipationId();
        return result;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", feedback='" + feedback + '\'' +
                ", mark=" + mark +
                ", participationId=" + participationId +
                '}';
    }
}
