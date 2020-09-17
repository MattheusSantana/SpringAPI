package com.dev.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Entity
public class Post extends AbstractEntity{

    @NotEmpty
    @Lob
    private String title;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name="post_student",
            joinColumns = { @JoinColumn(name="post_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name="student_id", referencedColumnName = "id")})
    private Student author;

    @NotEmpty
    private String text;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date;



    @Override
    public long getId() {
        return super.getId();
    }

    @Override
    public void setId(long id) {
        super.setId(id);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Student getAuthor() {
        return author;
    }

    public void setAuthor(Student author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
