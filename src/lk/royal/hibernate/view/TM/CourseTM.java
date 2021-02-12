package lk.royal.hibernate.view.TM;

import com.jfoenix.controls.JFXButton;

public class CourseTM {
    private String code;
    private String courseName;
    private String type;
    private String duration;
    private double fee;
    private JFXButton btn;


    public CourseTM(String code, String courseName, String type, String duration, double fee, JFXButton btn) {
        this.code = code;
        this.courseName = courseName;
        this.type = type;
        this.duration = duration;
        this.fee = fee;
        this.btn = btn;
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

    public JFXButton getBtn() {
        return btn;
    }

    public void setBtn(JFXButton btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "CourseTM{" +
                "code='" + code + '\'' +
                ", courseName='" + courseName + '\'' +
                ", type='" + type + '\'' +
                ", duration='" + duration + '\'' +
                ", fee=" + fee +
                ", btn=" + btn +
                '}';
    }
}
