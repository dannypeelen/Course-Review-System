package com.cs1530.coursereview.model;

import jakarta.presistence.*;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String courseNumber;

    @Column(nullable = false)
    private String title;

    @Column(length = 2000)
    private String description;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher; //we must implement Teacher object

    @OneToMany(mappedBy = "course")
    private List<Review> reviews;

    private String schedule;


    public Long getId(){ return id; }
    public void setId(Long id){ this.id = id; }

    public String getCourseNumber() { return courseNumber; }
    public void setCourseNumber(String courseNumber) { this.courseNumber = courseNumber; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Teacher getTeacher() { return teacher; }
    public void setTeacher(Teacher teacher) { this.teacher = teacher; }

    public List<Review> getReviews() { return reviews; }
    public void setReviews(List<Review> courseNumber) { this.reviews = reviews; }

    public String getSchedule() { return schedule; }
    public void setSchedule(String schedule) { this.schedule = schedule; }


}
