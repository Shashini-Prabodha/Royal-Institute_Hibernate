package lk.royal.hibernate.dto;

public class CourseDTO {

    private String code;
    private String courseName;
    private String type;
    private String duration;
    private double fee;

    public CourseDTO() {
    }

    public CourseDTO(String code, String courseName, String type, String duration, double fee) {
        this.code = code;
        this.courseName = courseName;
        this.type = type;
        this.duration = duration;
        this.fee = fee;
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

    @Override
    public String toString() {
        return "CourseDTO{" +
                "code='" + code + '\'' +
                ", courseName='" + courseName + '\'' +
                ", type='" + type + '\'' +
                ", duration='" + duration + '\'' +
                ", fee=" + fee +
                '}';
    }
}
