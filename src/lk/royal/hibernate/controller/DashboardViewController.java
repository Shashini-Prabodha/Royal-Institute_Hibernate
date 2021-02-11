package lk.royal.hibernate.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import lk.royal.hibernate.bo.BOFactory;
import lk.royal.hibernate.bo.BOType;
import lk.royal.hibernate.bo.custom.CourseBO;
import lk.royal.hibernate.bo.custom.RegisterBO;
import lk.royal.hibernate.bo.custom.StudentBO;

public class DashboardViewController {
    
    @FXML private TabPane tabPane;
    @FXML private Tab tabReg;
    @FXML private JFXTextField txtSName,txtAddr,txtContact,txtDuration,txtFee;
    @FXML private JFXDatePicker dataPicker;
    @FXML private Label lblSID;
    @FXML private ToggleGroup groupGender;
    @FXML private JFXRadioButton rbtnMale,rbtnFemale;
    @FXML private Label lblRegNo,lblCID;
    @FXML private JFXComboBox<?> cmbCourse;
    @FXML private ToggleGroup group;
    @FXML private JFXRadioButton rbtnPartTime,rbtnFullTime;
    @FXML private JFXButton btnReg;


    @FXML private Tab tabCourse;
    @FXML private TableView<?> tblCourse;
    @FXML private TableColumn<?, ?> colCID,colCName,colType,colDurationC,colFeeC,colDeleteC;
    @FXML private Label lblCIDC;
    @FXML private ToggleGroup groupC;
    @FXML private JFXRadioButton rbtnPartTimeC,rbtnFullTimeC;
    @FXML private JFXTextField txtDurationC,txtFeeC, txtCourseNameC;
    @FXML private JFXButton btnNew,btnSaveC,btnUpdateC;


    @FXML private Tab tabStudent;
    @FXML private TableView<?> tblStudent;
    @FXML private TableColumn<?, ?> colSID,colSName,colAddress,colContact,colDOB,colGender,colDeleteS;
    @FXML private JFXTextField txtSName1,txtAddr1,txtContact1;
    @FXML private JFXDatePicker dataPicker1;
    @FXML private Label lblSID1;
    @FXML private ToggleGroup groupGender1;
    @FXML private JFXRadioButton rbtnMale1,rbtnFemale1;
    @FXML private JFXButton btnSaveC1,btnUpdateC1;


    @FXML private Tab tabDetail;
    @FXML private JFXComboBox<?> cmbCourse1;
    @FXML private JFXButton btnSearch,btnOff;
    @FXML private TableView<?> tblStudent1;
    @FXML private TableColumn<?, ?> colSID1,colSName1,colAddress1,colContact1,colDOB1,colGender1,colDeleteS1;
    @FXML private Label lblNoOfStudent,lblNoOfCourse;

    StudentBO studentBO= BOFactory.getInstance().getBO(BOType.STUDENT);
    CourseBO courseBO= BOFactory.getInstance().getBO(BOType.COURSE);
    RegisterBO registerBO= BOFactory.getInstance().getBO(BOType.REGISTER);

    public void initialize(){

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
    void btnUpdateOnActionC(ActionEvent event) {

    }

    @FXML
    void cmbCourseOnAction(ActionEvent event) {

    }

}
