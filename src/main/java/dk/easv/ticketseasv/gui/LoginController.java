package dk.easv.ticketseasv.gui;

import dk.easv.ticketseasv.be.User;
import dk.easv.ticketseasv.bll.PasswordManager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
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
    @FXML
    public Button btnLogIn;

    PasswordManager passwordManager = new PasswordManager();

    @FXML
    public void initialize() {
         txtUsernameField.textProperty().addListener((_, _, _) -> updateButton());
         txtPasswordField.textProperty().addListener((_, _, _) -> updateButton());
    }

    private void updateButton() {
        btnLogIn.setDisable(txtUsernameField.getText().isEmpty() || txtPasswordField.getText().isEmpty());
    }

    public void btnSignIn() {
        String login = txtUsernameField.getText();
        String password = txtPasswordField.getText();

        if (passwordManager.checkLogin(login, password)) {
            User user = passwordManager.getUser();
            try {
                Stage stage = (Stage) txtUsernameField.getScene().getWindow();
                if (user.getRole().equals("Admin")) {
                    stage.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../admin-homepage.fxml"))));
                } else {
                    stage.getScene().setRoot(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../coordinator-homepage.fxml"))));
                }
            } catch (Exception e) {
                lblErr.setVisible(true);
                lblErr.setText("An error occurred while loading the homepage");
            }
        } else {
            lblErr.setVisible(true);
            lblErr.setText("Wrong login or password!");
        }
    }
}
