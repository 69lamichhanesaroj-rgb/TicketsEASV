package dk.easv.ticketseasv.gui;

import dk.easv.ticketseasv.be.User;
import dk.easv.ticketseasv.bll.PasswordManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

    PasswordManager passwordManager = new PasswordManager();

    public void btnSignIn(ActionEvent actionEvent) {
        String login = txtUsernameField.getText();
        String password = txtPasswordField.getText();

        if (passwordManager.checkLogin(login, password)) {
            User user = passwordManager.getUser();
            try {
                if (user.getRole().equals("Admin")) {
                    Stage stage = (Stage) txtUsernameField.getScene().getWindow();
                    stage.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../admin-homepage.fxml"))));
                } else {
                    Stage stage = (Stage) txtUsernameField.getScene().getWindow();
                    stage.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../coordinator-homepage.fxml"))));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            lblErr.setVisible(true);
            lblErr.setText("Wrong login or password!");
        }
    }
}
