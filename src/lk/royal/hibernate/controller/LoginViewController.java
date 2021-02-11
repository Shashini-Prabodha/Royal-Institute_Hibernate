package lk.royal.hibernate.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class LoginViewController {
    public ImageView imgLogo;
    public JFXTextField txtName;
    public JFXPasswordField txtPassword;
    public JFXButton btnViewPW;
    public Label lblStatus;
    public JFXTextField txtPasswordClone;
    public JFXButton btnInvisible;
    public JFXButton btnLogin;

    public void initialize() {
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
    }
}
