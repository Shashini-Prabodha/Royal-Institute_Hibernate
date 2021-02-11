package lk.royal.hibernate.view.TM;

import com.jfoenix.controls.JFXButton;

import java.sql.Date;

public class StudentTM {
    private String ID;
    private String name;
    private String address;
    private int contactNo;
    private Date dob;
    private String gender;
    private JFXButton btn;

    public StudentTM() {
    }

    public StudentTM(String ID, String name, String address, int contactNo, Date dob, String gender, JFXButton btn) {
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.contactNo = contactNo;
        this.dob = dob;
        this.gender = gender;
        this.btn = btn;
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

    public JFXButton getBtn() {
        return btn;
    }

    public void setBtn(JFXButton btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "StudentTM{" +
                "ID='" + ID + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contactNo=" + contactNo +
                ", dob=" + dob +
                ", gender='" + gender + '\'' +
                ", btn=" + btn +
                '}';
    }
}
