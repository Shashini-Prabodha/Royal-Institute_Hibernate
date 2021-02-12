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
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Paint;
import lk.royal.hibernate.bo.BOFactory;
import lk.royal.hibernate.bo.BOType;
import lk.royal.hibernate.bo.custom.CourseBO;
import lk.royal.hibernate.bo.custom.RegisterBO;
import lk.royal.hibernate.bo.custom.StudentBO;
import lk.royal.hibernate.dto.CourseDTO;
import lk.royal.hibernate.dto.RegistrationDTO;
import lk.royal.hibernate.dto.StudentDTO;
import lk.royal.hibernate.view.TM.CourseTM;
import lk.royal.hibernate.view.TM.StudentTM;

import java.awt.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class DashboardViewController {


    @FXML
    private TabPane tabPane;
    @FXML
    private Tab tabReg;
    @FXML
    private JFXTextField txtSIDR, txtSName, txtAddr, txtContact, txtDuration, txtFee, txtRegFee;
    @FXML
    private JFXDatePicker dataPicker;
    @FXML
    private ToggleGroup groupGender;
    @FXML
    private JFXRadioButton rbtnMale, rbtnFemale;
    @FXML
    private Label lblRegNo, lblCID;
    @FXML
    private JFXComboBox cmbCourseR;
    @FXML
    private ToggleGroup group;
    @FXML
    private JFXRadioButton rbtnPartTime, rbtnFullTime;
    @FXML
    private JFXButton btnReg, btnSearchStudentR;


    @FXML
    private Tab tabCourse;
    @FXML
    private TableView<CourseTM> tblCourse;
    @FXML
    private TableColumn colCode, colCName, colType, colDurationC, colFeeC, colDeleteC;
    @FXML
    private Label lblCIDC;
    @FXML
    private ToggleGroup groupC;
    @FXML
    private JFXRadioButton rbtnPartTimeC, rbtnFullTimeC;
    @FXML
    private JFXTextField txtDurationC, txtFeeC, txtCourseNameC;
    @FXML
    private JFXButton btnNewCourse, btnSaveC, btnUpdateC;
    @FXML
    private JFXComboBox cmbDurationType;


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
    private JFXComboBox cmbCourse;
    @FXML
    private JFXButton btnSearch, btnOff;
    @FXML
    private TableView<?> tblStudentCWise;
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
        setNoOfCourse();
        setNoOfStudent();
        loadDurationTypes();
        loadAllCourseDetail();
        loadAllCourseCmb();
        genarateRegNo();

        //set course detail tab table col
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colCName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colDurationC.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colFeeC.setCellValueFactory(new PropertyValueFactory<>("fee"));
        colDeleteC.setCellValueFactory(new PropertyValueFactory<>("btn"));

        //set course detail tab table col click

        tblCourse.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setDataCourse(newValue);
        });

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
    void btnOffOnAction(ActionEvent event) {

    }

    @FXML
    void btnRegOnAction(ActionEvent event) {
        try {
            LocalDate localDate = LocalDate.now();
            Date date = Date.valueOf(localDate);
            if (Pattern.compile("^[A-z ]{1,}$").matcher(txtSName.getText()).matches()) {
                if (Pattern.compile("^[A-z, |0-9:./]*\\b$").matcher(txtAddr.getText()).matches()) {
                    if (Pattern.compile("^\\d{10}$").matcher(txtContact.getText()).matches()) {
                        if (dataPicker.getValue() != null) {
                            if (rbtnMale.isSelected() || rbtnFemale.isSelected()) {
                                if (!cmbCourseR.getSelectionModel().isEmpty()) {
                                    if (Pattern.compile("^[0-9.]{1,}$").matcher(txtRegFee.getText().trim()).matches()) {

                                        LocalDate value = dataPicker.getValue();
                                        Date dob1 = Date.valueOf(value);

                                        StudentDTO studentDTO = new StudentDTO(txtSIDR.getText(), txtSName.getText(), txtAddr.getText(), Integer.parseInt(txtContact.getText()), dob1, getGender());
                                        CourseDTO courseDTO = new CourseDTO(lblCID.getText(), (String) cmbCourseR.getValue(), getTypeR(), txtDuration.getText(), Double.parseDouble(txtFee.getText().trim()));

                                        RegistrationDTO registrationDTO = new RegistrationDTO(Integer.parseInt(lblRegNo.getText()), date, Double.parseDouble(txtRegFee.getText()),studentDTO,courseDTO.getCode());

//                                        boolean saved = studentBO.saveStudent(studentDTO);
                                        boolean register = registerBO.saveRegister(registrationDTO);
                                        if ( register) {
                                            loadID();
                                            loadAllStudent1();
                                            clearStudentDetailField();
                                            setNoOfStudent();
                                            genarateRegNo();
                                            new Alert(Alert.AlertType.CONFIRMATION, "Student Register...!").show();
                                        } else {
                                            new Alert(Alert.AlertType.ERROR, "Registration failed...!").show();
                                        }
                                    } else {
                                        new Alert(Alert.AlertType.ERROR, "Check Registration Fee Field...\n(Use only numbers for fill Fee...!)").show();
                                        txtRegFee.setFocusColor(Paint.valueOf("red"));
                                        txtRegFee.requestFocus();
                                    }
                                } else {
                                    new Alert(Alert.AlertType.ERROR, "Please choose course ").show();
                                    cmbCourseR.requestFocus();
                                    cmbCourseR.setFocusColor(Paint.valueOf("red"));
                                }
                            } else {
                                new Alert(Alert.AlertType.ERROR, "Please Choose gender...!").show();
                                rbtnMale.requestFocus();
                            }
                        } else {
                            new Alert(Alert.AlertType.ERROR, "Please Input Date of birth...!").show();
                            dataPicker.requestFocus();
                        }
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Check contact no field...\n(Use only 10 numbers for fill contact no...!)").show();
                        txtContact.requestFocus();
                        txtContact.setFocusColor(Paint.valueOf("red"));
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Check address field...!)").show();
                    txtAddr.requestFocus();
                    txtAddr.setFocusColor(Paint.valueOf("red"));
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Please input student name...!").show();
                txtSName.setFocusColor(Paint.valueOf("red"));
                txtSName.requestFocus();
            }
        } catch (
                Exception e) {
            e.printStackTrace();
        }

    }


    public void cmbCourseROnAction(ActionEvent actionEvent) {
        try {
            String cname = (String) cmbCourseR.getValue();
            CourseDTO courseN = courseBO.getCourseN(cname);
            lblCID.setText(courseN.getCode());
            txtDuration.setText(courseN.getDuration());
            txtFee.setText(courseN.getFee() + "");
            if (courseN.getType().equals("Part Time")) {
                rbtnPartTime.setSelected(true);
            } else {
                rbtnFullTime.setSelected(true);
            }
            txtRegFee.requestFocus();
            txtRegFee.setFocusColor(Paint.valueOf("red"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //set new reg no
    void genarateRegNo() {
        try {
            lblRegNo.setText(registerBO.newRegNo()+ "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //find gender in registration tab
    public String getGender() {
        if (rbtnMale.isSelected()) {
            return "Male";
        } else if (rbtnFemale.isSelected()) {
            return "Female";
        } else {
            return null;
        }
    }

    private String getTypeR() {
        if (rbtnFullTime.isSelected()) {
            return "Full Time";
        } else {
            return "Part Time";
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }


    @FXML
    void cmbCourseOnAction(ActionEvent event) {

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


    //    ************************ Home Tab Start *****************************

    //    get no of course available
    void setNoOfCourse() {
        int count = 0;
        try {
            ArrayList<CourseDTO> allCourse = courseBO.getAllCourse();
            for (CourseDTO courseDTO : allCourse) {
                if (courseDTO != null) {
                    count++;
                }
            }
        } catch (Exception e) {

        }
        lblNoOfCourse.setText(count + "");

    }

    //    get no of student available
    void setNoOfStudent() {
        int count = 0;
        try {
            ArrayList<StudentDTO> allStudent = studentBO.getAllStudent();
            for (StudentDTO studentDTO : allStudent) {
                if (studentDTO != null) {
                    count++;
                }
            }
        } catch (Exception e) {

        }
        lblNoOfStudent.setText(count + "");
    }

    // load all course name
    void loadAllCourseCmb() {
        try {
            List<CourseDTO> allCourse = courseBO.getAllCourse();
            for (CourseDTO dto : allCourse) {
                cmbCourse.getItems().addAll(dto.getCourseName());
                cmbCourseR.getItems().addAll(dto.getCourseName());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //    ************************ Home Tab End *****************************


    //    ************************  Student Detail Tab Start*****************************
    //load reg sid and student detail sid
    void loadID() {
        txtSIDR.setText(generateSID());
        lblSID1.setText(generateSID());
        lblCIDC.setText(generateCCode());

    }

    ObservableList<StudentTM> tmlistS;

    //load student detail tab table
    void loadAllStudent1() {
        try {
            tmlistS = FXCollections.observableArrayList();
            ArrayList<StudentDTO> list = studentBO.getAllStudent();
            for (StudentDTO dto : list) {
                JFXButton btn = new JFXButton("Delete");
                StudentTM tm = new StudentTM(dto.getID(), dto.getName(), dto.getAddress(), dto.getContactNo(), dto.getDob(), dto.getGender(), btn);
                tmlistS.add(tm);
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
            tblStudent.setItems(tmlistS);
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

            if (Pattern.compile("^[A-z ]{1,}$").matcher(txtSName1.getText()).matches()) {
                if (Pattern.compile("^[A-z, |0-9:./]*\\b$").matcher(txtAddr1.getText()).matches()) {
                    if (Pattern.compile("^\\d{10}$").matcher(txtContact1.getText()).matches()) {
                        if (dataPicker1.getValue() != null) {
                            if (rbtnMale1.isSelected() || rbtnFemale1.isSelected()) {
                                if (checkDuplicateSID()) {
                                    LocalDate value = dataPicker1.getValue();
                                    Date dob1 = Date.valueOf(value);
                                    boolean saved = studentBO.saveStudent(new StudentDTO(lblSID1.getText(), txtSName1.getText(), txtAddr1.getText(), Integer.parseInt(txtContact1.getText()), dob1, getGender1()));
                                    if (saved) {
                                        loadID();
                                        loadAllStudent1();
                                        clearStudentDetailField();
                                        setNoOfStudent();
                                        new Alert(Alert.AlertType.CONFIRMATION, "Student Saved...!").show();
                                    } else {
                                        new Alert(Alert.AlertType.ERROR, "Failed...!").show();
                                    }
                                } else {
                                    new Alert(Alert.AlertType.ERROR, "This Student ID Already exsist!\n      Please use new Button for new student add\n or\nPlease use update Button for update student detail ").show();
                                    btnNewCourse.requestFocus();
                                }
                            } else {
                                new Alert(Alert.AlertType.ERROR, "Please Choose gender...!").show();
                                rbtnMale1.requestFocus();
                            }
                        } else {
                            new Alert(Alert.AlertType.ERROR, "Please Input Date of birth...!").show();
                            dataPicker1.requestFocus();
                        }
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Check contact no field...\n(Use only 10 numbers for fill contact no...!)").show();
                        txtContact1.requestFocus();
                        txtContact1.setFocusColor(Paint.valueOf("red"));
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Check address field...!)").show();
                    txtAddr1.requestFocus();
                    txtAddr1.setFocusColor(Paint.valueOf("red"));
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Please input student name...!").show();
                txtSName1.setFocusColor(Paint.valueOf("red"));
                txtSName1.requestFocus();
            }
        } catch (
                Exception e) {
            e.printStackTrace();
        }

    }

    //student detail tab update
    public void btnUpdateSOnAction(ActionEvent actionEvent) {
        try {

            if (Pattern.compile("^[A-z ]{1,}$").matcher(txtSName1.getText()).matches()) {
                if (Pattern.compile("^[A-z, |0-9:./]*\\b$").matcher(txtAddr1.getText()).matches()) {
                    if (Pattern.compile("^\\d{10}$").matcher(txtContact1.getText()).matches()) {
                        if (dataPicker1.getValue() != null) {
                            if (rbtnMale1.isSelected() || rbtnFemale1.isSelected()) {
                                ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                                ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure Update Course Detail? ", yes, no);
                                Optional<ButtonType> result = alert.showAndWait();
                                if (result.orElse(no) == yes) {
                                    LocalDate value = dataPicker1.getValue();
                                    Date dob1 = Date.valueOf(value);
                                    boolean updated = studentBO.updateStudent(new StudentDTO(lblSID1.getText(), txtSName1.getText(), txtAddr1.getText(), Integer.parseInt(txtContact1.getText()), dob1, getGender1()));
                                    if (updated) {
                                        loadID();
                                        loadAllStudent1();
                                        clearStudentDetailField();
                                        setNoOfStudent();
                                        new Alert(Alert.AlertType.CONFIRMATION, "Student Updated...!").show();
                                    } else {
                                        new Alert(Alert.AlertType.ERROR, "Failed...!").show();
                                    }
                                }
                            } else {
                                new Alert(Alert.AlertType.ERROR, "Please Choose gender...!").show();
                                rbtnMale1.requestFocus();
                            }
                        } else {
                            new Alert(Alert.AlertType.ERROR, "Please Input Date of birth...!").show();
                            dataPicker1.requestFocus();
                        }
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Check contact no field...\n(Use only 10 numbers for fill contact no...!)").show();
                        txtContact1.requestFocus();
                        txtContact1.setFocusColor(Paint.valueOf("red"));
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Check address field...!)").show();
                    txtAddr1.requestFocus();
                    txtAddr1.setFocusColor(Paint.valueOf("red"));
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Please input student name...!").show();
                txtSName1.setFocusColor(Paint.valueOf("red"));
                txtSName1.requestFocus();
            }
        } catch (
                Exception e) {
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

    private boolean checkDuplicateSID() {
        for (StudentTM tm : tmlistS) {
            if (lblSID1.getText().equals(tm.getID())) {
                return false;
            }
        }
        return true;
    }


    //    ************************  Student Detail Tab End *****************************


    //    ************************  Course Detail Tab Start *****************************

    //genarate course code
    public String generateCCode() {
        String s = null;
        try {
            s = courseBO.newCourseID();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    //set Course detail tab data to click table
    private void setDataCourse(CourseTM tm) {
        try {
            String duration = tm.getDuration();

            lblCIDC.setText(tm.getCode());
            txtCourseNameC.setText(tm.getCourseName());
            txtDurationC.setText(duration.substring(0, 2));
            txtFeeC.setText(tm.getFee() + "");
            if (tm.getType().equals("Part Time")) {
                rbtnPartTimeC.setSelected(true);
            } else {
                rbtnFullTimeC.setSelected(true);
            }
            String d = null;
            if (duration.contains("Year")) {
                d = "Year";
            } else if (duration.contains("Month")) {
                d = "Month";
            } else if (duration.contains("Week")) {
                d = "Week";
            } else {
                d = "Day";
            }

            System.out.println("cmb " + d);
            cmbDurationType.setValue(d);

        } catch (NullPointerException e) {

        }
    }


    //course detail tab field clear
    void clearCourseDetailField() {
        txtCourseNameC.clear();
        txtDurationC.clear();
        txtFeeC.clear();
        rbtnPartTimeC.setSelected(false);
        rbtnFullTimeC.setSelected(false);
        lblCIDC.setText(generateCCode());
        cmbDurationType.getSelectionModel().clearSelection();

    }

    void loadDurationTypes() {
        cmbDurationType.getItems().add("Year");
        cmbDurationType.getItems().add("Month");
        cmbDurationType.getItems().add("Week");
        cmbDurationType.getItems().add("Day");
    }

    @FXML
    void btnNewCourseOnAction(ActionEvent event) {

        clearCourseDetailField();
    }

    ObservableList<CourseTM> tmlistC;

    //load course detail tab table
    void loadAllCourseDetail() {
        try {
            tmlistC = FXCollections.observableArrayList();
            ArrayList<CourseDTO> allCourse = courseBO.getAllCourse();
            for (CourseDTO dto : allCourse) {
                JFXButton btn = new JFXButton("Delete");
                CourseTM tm = new CourseTM(dto.getCode(), dto.getCourseName(), dto.getType(), dto.getDuration(), dto.getFee(), btn);
                tmlistC.add(tm);
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

                            if (courseBO.deleteCourse(tm.getCode())) {
                                new Alert(Alert.AlertType.CONFIRMATION,
                                        "Deleted", ButtonType.OK).show();
                                loadAllCourseDetail();
                                clearCourseDetailField();
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


            tblCourse.setItems(tmlistC);

        } catch (Exception e) {

        }
    }

    @FXML
    void btnSaveCOnAction(ActionEvent event) {
        try {
            if (Pattern.compile("^[A-z ]{1,}$").matcher(txtCourseNameC.getText()).matches()) {
                if (rbtnPartTimeC.isSelected() || rbtnFullTimeC.isSelected()) {
                    if (Pattern.compile("^[1-9.]{1,}$").matcher(txtDurationC.getText().trim()).matches()) {
                        if (!cmbDurationType.getSelectionModel().isEmpty()) {
                            if (Pattern.compile("^[0-9.]{1,}$").matcher(txtFeeC.getText().trim()).matches()) {
                                if (checkDuplicateCode()) {
                                    boolean saveCourse = courseBO.saveCourse(new CourseDTO(lblCIDC.getText(), txtCourseNameC.getText(), getTypeR(), (txtDurationC.getText() + " " + cmbDurationType.getValue()), Double.parseDouble(txtFeeC.getText().trim())));
                                    if (saveCourse) {
                                        loadAllCourseDetail();
                                        generateCCode();
                                        clearCourseDetailField();
                                        loadAllCourseCmb();

                                        new Alert(Alert.AlertType.CONFIRMATION, "Course Saved...!").show();
                                    } else {
                                        new Alert(Alert.AlertType.ERROR, "Failed...!").show();
                                    }
                                } else {
                                    new Alert(Alert.AlertType.ERROR, "This Course ID Already exsist!\n      Please use new Button for new course\n or\nPlease use update Button for update course ").show();
                                    btnNewCourse.requestFocus();
                                }
                            } else {
                                new Alert(Alert.AlertType.ERROR, "Check Fee Field...\n(Use only numbers for fill Fee...!)").show();
                                txtFeeC.setFocusColor(Paint.valueOf("red"));
                                txtFeeC.requestFocus();

                            }
                        } else {
                            cmbDurationType.setFocusColor(Paint.valueOf("red"));
                            cmbDurationType.requestFocus();

                        }
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Check Duration Field...\n(Use only numbers for fill duration...!)").show();
                        txtDurationC.setFocusColor(Paint.valueOf("red"));
                        txtDurationC.requestFocus();

                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Please Choose course type...!").show();
                    rbtnFullTimeC.requestFocus();
                    rbtnPartTimeC.requestFocus();

                }

            } else {
                new Alert(Alert.AlertType.ERROR, "Please input course name...!").show();
                txtCourseNameC.setFocusColor(Paint.valueOf("red"));
                txtCourseNameC.requestFocus();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //check duplicate course code
    private boolean checkDuplicateCode() {
        for (CourseTM tm : tmlistC) {
            if (lblCIDC.getText().equals(tm.getCode())) {
                return false;
            }
        }
        return true;
    }


    private String getType() {
        if (rbtnFullTimeC.isSelected()) {
            return "Full Time";
        } else {
            return "Part Time";
        }
    }


    public void btnUpdateOnActionC(ActionEvent actionEvent) {
        try {
            if (Pattern.compile("^[A-z ]{1,}$").matcher(txtCourseNameC.getText()).matches()) {
                if (rbtnPartTimeC.isSelected() || rbtnFullTimeC.isSelected()) {
                    if (Pattern.compile("^[1-9.]{1,}$").matcher(txtDurationC.getText().trim()).matches()) {
                        if (!cmbDurationType.getSelectionModel().isEmpty()) {
                            if (Pattern.compile("^[0-9.]{1,}$").matcher(txtFeeC.getText().trim()).matches()) {

                                ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
                                ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure Update Course Detail? ", yes, no);
                                Optional<ButtonType> result = alert.showAndWait();
                                if (result.orElse(no) == yes) {
                                    boolean updateCourse = courseBO.updateCourse(new CourseDTO(lblCIDC.getText(), txtCourseNameC.getText(), getType(), (txtDurationC.getText() + " " + cmbDurationType.getValue()), Double.parseDouble(txtFeeC.getText().trim())));
                                    if (updateCourse) {
                                        loadAllCourseDetail();
                                        generateCCode();
                                        loadAllCourseCmb();
                                        clearCourseDetailField();
                                        new Alert(Alert.AlertType.CONFIRMATION, "Course Updated...!").show();
                                    } else {
                                        new Alert(Alert.AlertType.ERROR, "Failed...!").show();
                                    }
                                }

                            } else {
                                new Alert(Alert.AlertType.ERROR, "This Course ID Already exsist!\n      Please use new Button for new course\n or\nPlease use update Button for update course ").show();
                                txtFeeC.setFocusColor(Paint.valueOf("red"));
                                txtFeeC.requestFocus();

                            }
                        } else {
                            cmbDurationType.setFocusColor(Paint.valueOf("red"));
                            cmbDurationType.requestFocus();

                        }
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Check your Duration Field...\n(Use only numbers for fill duration...!)").show();
                        txtDurationC.setFocusColor(Paint.valueOf("red"));
                        txtDurationC.requestFocus();

                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Please Choose course type...!").show();
                    rbtnFullTimeC.requestFocus();
                    rbtnPartTimeC.requestFocus();

                }

            } else {
                new Alert(Alert.AlertType.ERROR, "Please input course name...!").show();
                txtCourseNameC.setFocusColor(Paint.valueOf("red"));
                txtCourseNameC.requestFocus();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cmbDurationTypeOnAction(ActionEvent actionEvent) {
        String s = (String) cmbDurationType.getValue();
        System.out.println(s);

    }


    //    ************************  Course Detail Tab End *****************************

}
