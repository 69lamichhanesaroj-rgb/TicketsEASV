package dk.easv.ticketseasv.gui;

import dk.easv.ticketseasv.be.User;
import dk.easv.ticketseasv.bll.LoginManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Objects;

public class LoginController {
    @FXML
    public PasswordField txtPasswordField;
    @FXML
    public TextField txtUsernameField;
    @FXML
    public Label lblErr;

    LoginManager loginManager = new LoginManager();

    public void btnSignIn(ActionEvent actionEvent) throws Exception {
        String login = txtUsernameField.getText();
        String password = txtPasswordField.getText();


        if (loginManager.checkLogin(login, password)) {
            User user = loginManager.getUser();
            if (user.getRole().equals("Admin")) {
                Stage stage = (Stage) txtUsernameField.getScene().getWindow();
                stage.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../admin-homepage.fxml"))));
            } else {
                Stage stage = (Stage) txtUsernameField.getScene().getWindow();
                stage.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../coordinator-homepage.fxml"))));
            }
        } else {
            lblErr.setVisible(true);
            lblErr.setText("Wrong login or password!");
        }
    }
}
