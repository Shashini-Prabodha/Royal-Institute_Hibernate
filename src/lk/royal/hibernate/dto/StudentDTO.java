package lk.royal.hibernate.dto;

import java.sql.Date;

public class StudentDTO {
    private String ID;
    private String name;
    private String address;
    private int contactNo;
    private Date dob;
    private String gender;

    public StudentDTO() {
    }

    public StudentDTO(String ID, String name, String address, int contactNo, Date dob, String gender) {
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.contactNo = contactNo;
        this.dob = dob;
        this.gender = gender;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getContactNo() {
        return contactNo;
    }

    public void setContactNo(int contactNo) {
        this.contactNo = contactNo;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "StudentDTO{" +
                "ID='" + ID + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contactNo=" + contactNo +
                ", dob=" + dob +
                ", gender='" + gender + '\'' +
                '}';
    }
}
