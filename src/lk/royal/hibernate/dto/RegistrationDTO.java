package lk.royal.hibernate.dto;

import java.sql.Date;

public class RegistrationDTO {
    private int regNo;
    private Date regDate;
    private double regFee;
    private StudentDTO studentDTO;
    private String Course_code;

    public RegistrationDTO(int regNo, Date regDate, double regFee) {
    }

    public RegistrationDTO(int regNo, Date regDate, double regFee, StudentDTO studentDTO, String course_code) {
        this.regNo = regNo;
        this.regDate = regDate;
        this.regFee = regFee;
        this.studentDTO= studentDTO;
        Course_code = course_code;
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

    public StudentDTO getStudentDTO() {
        return studentDTO;
    }

    public void setStudentDTO(StudentDTO studentDTO) {
        this.studentDTO = studentDTO;
    }

    public String getCourse_code() {
        return Course_code;
    }

    public void setCourse_code(String course_code) {
        Course_code = course_code;
    }

    @Override
    public String toString() {
        return "RegistrationDTO{" +
                "regNo=" + regNo +
                ", regDate=" + regDate +
                ", regFee=" + regFee +
                ", studentDTO=" + studentDTO +
                ", Course_code='" + Course_code + '\'' +
                '}';
    }
}
