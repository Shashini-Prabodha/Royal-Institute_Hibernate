package lk.royal.hibernate.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.royal.hibernate.bo.BOFactory;
import lk.royal.hibernate.bo.BOType;
import lk.royal.hibernate.bo.custom.CourseBO;
import lk.royal.hibernate.bo.custom.RegisterBO;
import lk.royal.hibernate.bo.custom.StudentBO;
import lk.royal.hibernate.dto.StudentDTO;
import lk.royal.hibernate.view.TM.StudentTM;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

public class DashboardViewController {


    @FXML
    private TabPane tabPane;
    @FXML
    private Tab tabReg;
    @FXML
    private JFXTextField txtSIDR, txtSName, txtAddr, txtContact, txtDuration, txtFee;
    @FXML
    private JFXDatePicker dataPicker;
    @FXML
    private ToggleGroup groupGender;
    @FXML
    private JFXRadioButton rbtnMale, rbtnFemale;
    @FXML
    private Label lblRegNo, lblCID;
    @FXML
    private JFXComboBox<?> cmbCourse;
    @FXML
    private ToggleGroup group;
    @FXML
    private JFXRadioButton rbtnPartTime, rbtnFullTime;
    @FXML
    private JFXButton btnReg, btnSearchStudentR;


    @FXML
    private Tab tabCourse;
    @FXML
    private TableView<?> tblCourse;
    @FXML
    private TableColumn<?, ?> colCID, colCName, colType, colDurationC, colFeeC, colDeleteC;
    @FXML
    private Label lblCIDC;
    @FXML
    private ToggleGroup groupC;
    @FXML
    private JFXRadioButton rbtnPartTimeC, rbtnFullTimeC;
    @FXML
    private JFXTextField txtDurationC, txtFeeC, txtCourseNameC;
    @FXML
    private JFXButton btnNew, btnSaveC, btnUpdateC;


    @FXML
    private Tab tabStudent;
    @FXML
    private TableView<StudentTM> tblStudent;
    @FXML
    private TableColumn colSID, colSName, colAddress, colContact, colDOB, colGender, colDeleteS;
    @FXML
    private JFXTextField txtSName1, txtAddr1, txtContact1;
    @FXML
    private JFXDatePicker dataPicker1;
    @FXML
    private Label lblSID1;
    @FXML
    private ToggleGroup groupGender1;
    @FXML
    private JFXRadioButton rbtnMale1, rbtnFemale1;
    @FXML
    private JFXButton btnSaveS, btnUpdateS, btnNewS;


    @FXML
    private Tab tabDetail;
    @FXML
    private JFXComboBox<?> cmbCourse1;
    @FXML
    private JFXButton btnSearch, btnOff;
    @FXML
    private TableView<?> tblStudent1;
    @FXML
    private TableColumn colSID1, colSName1, colAddress1, colContact1, colDOB1, colGender1, colDeleteS1;
    @FXML
    private Label lblNoOfStudent, lblNoOfCourse;

    StudentBO studentBO = BOFactory.getInstance().getBO(BOType.STUDENT);
    CourseBO courseBO = BOFactory.getInstance().getBO(BOType.COURSE);
    RegisterBO registerBO = BOFactory.getInstance().getBO(BOType.REGISTER);

    public void initialize() {

        loadID();
        loadAllStudent1();

        //set student detail tab table col
        colSID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        colSName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colDeleteS.setCellValueFactory(new PropertyValueFactory<>("btn"));

        //set student detail tab table col click

        tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setDataStudent(newValue);
        });

    }


    @FXML
    void btnNewOnAction(ActionEvent event) {

    }

    @FXML
    void btnOffOnAction(ActionEvent event) {

    }

    @FXML
    void btnRegOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveCOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }


    @FXML
    void cmbCourseOnAction(ActionEvent event) {

    }

    private void setNoOfStudent() {

    }

    //genarate student id
    public String generateSID() {
        String s = null;
        try {
            s = studentBO.newStudentID();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }


    public void btnSearchStudentROnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateOnActionC(ActionEvent actionEvent) {
    }


    //    ************************  Student Detail Tab Start*****************************
    //load reg sid and student detail sid
    void loadID() {
        txtSIDR.setText(generateSID());
        lblSID1.setText(generateSID());
    }

    //load student detail tab table
    void loadAllStudent1() {
        try {
            ObservableList<StudentTM> tmlist = FXCollections.observableArrayList();
            ArrayList<StudentDTO> list = studentBO.getAllStudent();
            for (StudentDTO dto : list) {
                JFXButton btn = new JFXButton("Delete");
                StudentTM tm = new StudentTM(dto.getID(), dto.getName(), dto.getAddress(), dto.getContactNo(), dto.getDob(), dto.getGender(), btn);
                tmlist.add(tm);
                btn.setOnAction((e) -> {
                    try {
                        ButtonType ok = new ButtonType("OK",
                                ButtonBar.ButtonData.OK_DONE);
                        ButtonType no = new ButtonType("NO",
                                ButtonBar.ButtonData.CANCEL_CLOSE);

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                                "Are You Sure ?", ok, no);
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.orElse(no) == ok) {
                            System.out.println(tm.getID());
                            if (studentBO.deleteStudent(tm.getID())) {
                                new Alert(Alert.AlertType.CONFIRMATION,
                                        "Deleted", ButtonType.OK).show();
                                loadAllStudent1();
                                clearStudentDetailField();
                                return;
                            }
                            new Alert(Alert.AlertType.WARNING,
                                    "Try Again", ButtonType.OK).show();
                        } else {
                            //no
                        }

                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }

                });
            }
            tblStudent.setItems(tmlist);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //set student detail tab data to click table
    private void setDataStudent(StudentTM tm) {
        try {
            Date date = tm.getDob();
            lblSID1.setText(tm.getID());
            txtSName1.setText(tm.getName());
            txtAddr1.setText(tm.getAddress());
            txtContact1.setText(tm.getContactNo() + "");
            dataPicker1.setValue(date.toLocalDate());
            if (tm.getGender().equals("Male")) {
                rbtnMale1.setSelected(true);
            } else {
                rbtnFemale1.setSelected(true);
            }
        } catch (NullPointerException e) {

        }
    }

    //find gender in student detail tab
    public String getGender1() {
        if (rbtnMale1.isSelected()) {
            return "Male";
        } else if (rbtnFemale1.isSelected()) {
            return "Female";
        } else {
            return null;
        }
    }

    //student detail tab save
    public void btnSaveSOnAction(ActionEvent actionEvent) {
        try {
            LocalDate value = dataPicker1.getValue();
            Date dob1 = Date.valueOf(value);
            boolean saved = studentBO.saveStudent(new StudentDTO(lblSID1.getText(), txtSName1.getText(), txtAddr1.getText(), Integer.parseInt(txtContact1.getText()), dob1, getGender1()));
            if (saved) {
                loadID();
                loadAllStudent1();
                clearStudentDetailField();
                new Alert(Alert.AlertType.CONFIRMATION, "Student Saved...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed...!").show();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //student detail tab update
    public void btnUpdateSOnAction(ActionEvent actionEvent) {
        try {
            LocalDate value = dataPicker1.getValue();
            Date dob = Date.valueOf(value);
            boolean updated = studentBO.updateStudent(new StudentDTO(lblSID1.getText(), txtSName1.getText(), txtAddr1.getText(), Integer.parseInt(txtContact1.getText()), dob, getGender1()));

            if (updated) {
                loadID();
                loadAllStudent1();
                clearStudentDetailField();
                new Alert(Alert.AlertType.CONFIRMATION, "Student Updated...!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed...!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //student detail tab sid new btn on action
    public void btnNewSOnAction(ActionEvent actionEvent) {
        clearStudentDetailField();
    }

    //student detail tab field clear
    void clearStudentDetailField() {
        txtSName1.clear();
        txtContact1.clear();
        txtAddr1.clear();
        dataPicker1.setValue(null);
        rbtnFemale1.setSelected(false);
        rbtnMale1.setSelected(false);
        lblSID1.setText(generateSID());
    }

    //    ************************  Student Detail Tab End *****************************

}
