package lk.royal.hibernate.dto;

import java.sql.Date;
import java.util.List;

public class RegistrationDTO {
    private int regNo;
    private Date regDate;
    private double regFee;
    private StudentDTO studentDTO;
    private List<CourseDTO> Course_list;

    public RegistrationDTO(int regNo, Date regDate, double regFee) {
    }

    public RegistrationDTO(int regNo, Date regDate, double regFee, StudentDTO studentDTO, List<CourseDTO> course_list) {
        this.regNo = regNo;
        this.regDate = regDate;
        this.regFee = regFee;
        this.studentDTO = studentDTO;
        Course_list = course_list;
    }

    public RegistrationDTO(Date  regDate,double regFee, StudentDTO studentDTO, List<CourseDTO> course_list) {
        this.regDate = regDate;
        this.regFee = regFee;
        this.studentDTO = studentDTO;
        Course_list = course_list;
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

    public List<CourseDTO> getCourse_list() {
        return Course_list;
    }

    public void setCourse_list(List<CourseDTO> course_code) {
        Course_list = course_code;
    }

    @Override
    public String toString() {
        return "RegistrationDTO{" +
                "regNo=" + regNo +
                ", regDate=" + regDate +
                ", regFee=" + regFee +
                ", studentDTO=" + studentDTO +
                ", Course_code=" + Course_list +
                '}';
    }
}
