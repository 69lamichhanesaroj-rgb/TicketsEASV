package dk.easv.ticketseasv.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import dk.easv.ticketseasv.be.User;

public class AddUserController {

    @FXML
    private TextField txtUsername;
    @FXML
    private ComboBox<String> cbRole;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPassword;

    private Stage dialogStage;
    private User newUser;

    public void initialize() {
        cbRole.getItems().addAll("Admin", "Event Coordinator");
    }

    public void setDialogStage(Stage stage) {
        this.dialogStage = stage;
    }

    public User getNewUser() {
        return newUser;
    }

    public void btnAddUser() {
        String username = txtUsername.getText().trim();
        String role = cbRole.getSelectionModel().getSelectedItem();
        String email = txtEmail.getText().trim();
        String password = txtPassword.getText().trim();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || role.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Please make sure you've filled out all of the fields");
            alert.showAndWait();
            return;
        }

        dialogStage.close();
    }

    public void btnCancel() {
        dialogStage.close();
    }
}