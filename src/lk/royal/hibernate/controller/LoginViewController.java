package lk.royal.hibernate.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.regex.Pattern;

public class LoginViewController {
    public ImageView imgLogo;
    public JFXTextField txtName;
    public JFXPasswordField txtPassword;
    public JFXButton btnViewPW;
    public Label lblStatus;
    public JFXTextField txtPasswordClone;
    public JFXButton btnInvisible;
    public JFXButton btnLogin;
    public AnchorPane root;

    public void initialize() {
        fadeTransition();
        rotateAnimation();
        txtPasswordClone.setVisible(false);
        btnInvisible.setVisible(false);
    }

    public void btnViewPWOnAction(ActionEvent actionEvent) {
        txtPassword.setVisible(false);
        txtPasswordClone.setVisible(true);
        btnInvisible.setVisible(true);
        btnViewPW.setVisible(false);

        txtPasswordClone.setText(txtPassword.getText());
    }

    public void btnInvisibleOnAction(ActionEvent actionEvent) {
        txtPassword.setVisible(true);
        txtPasswordClone.setVisible(false);
        btnInvisible.setVisible(false);
        btnViewPW.setVisible(true);

        txtPassword.setText(txtPasswordClone.getText());
    }

    public void btnLoginOnAction(ActionEvent actionEvent) {
        String name = txtName.getText();
        if (Pattern.compile("^[A-z ]{1,}$").matcher(name).matches()) {
            String password = txtPassword.getText();
            if (name != null && name.equalsIgnoreCase("user")) {
                if (password != null && password.equalsIgnoreCase("user1234")) {
                    Stage s = (Stage) root.getScene().getWindow();
                    s.close();
                    loadUI("DashboardView");
                } else {
                    txtPassword.requestFocus();
                    txtPassword.setFocusColor(Paint.valueOf("red"));
                }

            } else {
                txtName.requestFocus();
                txtName.setFocusColor(Paint.valueOf("red"));
            }
        } else {
            txtName.requestFocus();
            txtName.setFocusColor(Paint.valueOf("red"));
        }
    }


    void fadeTransition() {

        FadeTransition fadeIn = new FadeTransition(Duration.millis(2000), root);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }

    void rotateAnimation() {
        RotateTransition transition = new RotateTransition();
        transition.setAxis(Rotate.Y_AXIS);
        transition.setByAngle(360);
        transition.setCycleCount(500);
        transition.setDuration(Duration.seconds(15));
        transition.setAutoReverse(true);
        transition.setNode(imgLogo);
        transition.play();
    }

    void loadUI(String location) {
        try {


            URL resource = this.getClass().getResource("/lk/royal/hibernate/view/" + location + ".fxml");
            Parent load = null;
            load = FXMLLoader.load(resource);
            Scene scene = new Scene(load);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
