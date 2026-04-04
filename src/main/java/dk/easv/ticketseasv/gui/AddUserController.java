package dk.easv.ticketseasv.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import dk.easv.ticketseasv.bll.PasswordManager;

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

    private final PasswordManager passwordManager = new PasswordManager();

    public void initialize() {
        cbRole.getItems().addAll("Admin", "Event Coordinator");
    }

    public void setDialogStage(Stage stage) {
        this.dialogStage = stage;
    }

    public void btnAddUser() {
        try {
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
            passwordManager.AddUser(username, role, email, password);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred while adding the user: " + e.getMessage());
            alert.showAndWait();
        }
        dialogStage.close();
    }

    public void btnCancel() {
        dialogStage.close();
    }
}