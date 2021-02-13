package lk.royal.hibernate.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Course implements SuperEntity{
    @Id
    private String code;
    private String courseName;
    private String type;
    private String duration;
    private double fee;
    @ManyToMany(mappedBy = "course",cascade= CascadeType.ALL)
    private List<Registration> registrations=new ArrayList<>();

    public Course() {
    }

    public Course(String code, String courseName, String type, String duration, double fee) {
        this.code = code;
        this.courseName = courseName;
        this.type = type;
        this.duration = duration;
        this.fee = fee;
    }

    public Course(String code, String courseName, String type, String duration, double fee, List<Registration> registrations) {
        this.code = code;
        this.courseName = courseName;
        this.type = type;
        this.duration = duration;
        this.fee = fee;
        this.registrations = registrations;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public List<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<Registration> registrations) {
        this.registrations = registrations;
    }

    @Override
    public String toString() {
        return "Course{" +
                "code='" + code + '\'' +
                ", courseName='" + courseName + '\'' +
                ", type='" + type + '\'' +
                ", duration='" + duration + '\'' +
                ", fee=" + fee +
                ", registrations=" + registrations +
                '}';
    }
}
