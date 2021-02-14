package lk.royal.hibernate.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
public class Registration implements SuperEntity{
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)

    private int regNo;
    private Date regDate;
    private double regFee;

    @ManyToOne
//    @JoinColumn(name = "student_ID", referencedColumnName = "id", nullable = false)
    private Student student;

    @ManyToMany
//    @JoinTable(name = "Course")
    private List<Course> course;

    public Registration() {
    }

    public Registration(int regNo, Date regDate, double regFee) {
        this.regNo = regNo;
        this.regDate = regDate;
        this.regFee = regFee;
    }

    public Registration(int regNo, Date regDate, double regFee, Student student, List<Course> course) {
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

    public void setCourse(List<Course> course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "regNo=" + regNo +
                ", regDate=" + regDate +
                ", regFee=" + regFee +
                ", student=" + student +
                ", course=" + course +
                '}';
    }
}
