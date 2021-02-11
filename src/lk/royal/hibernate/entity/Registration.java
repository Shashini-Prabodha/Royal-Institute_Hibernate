package lk.royal.hibernate.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Registration implements SuperEntity{
    @Id

    private int regNo;
    private Date regDate;
    private double regFee;

    @ManyToOne
//    @JoinColumn(name = "Student_ID", referencedColumnName = "ID" , insertable = false , updatable = false)
    private Student student;

    @ManyToOne
//    @JoinColumn(name = "Course_code", referencedColumnName = "code" , insertable = false , updatable = false)
    private Course course;

    public Registration() {
    }

    public Registration(int regNo, Date regDate, double regFee) {
        this.regNo = regNo;
        this.regDate = regDate;
        this.regFee = regFee;
    }

    public Registration(int regNo, Date regDate, double regFee, Student student, Course course) {
        this.regNo = regNo;
        this.regDate = regDate;
        this.regFee = regFee;
        this.student = student;
        this.course = course;
    }

    public int getRegNo() {
        return regNo;
    }

    public void setRegNo(int regNo) {
        this.regNo = regNo;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public double getRegFee() {
        return regFee;
    }

    public void setRegFee(double regFee) {
        this.regFee = regFee;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
