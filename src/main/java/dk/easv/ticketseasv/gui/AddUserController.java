package dk.easv.ticketseasv.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import dk.easv.ticketseasv.be.User;

public class AddUserController {

    @FXML private TextField nameField;
    @FXML private TextField roleField;
    @FXML private TextField emailField;

    private Stage dialogStage;
    private User newUser;

    public void setDialogStage(Stage stage) {
        this.dialogStage = stage;
    }

    public User getNewUser() {
        return newUser;
    }

    @FXML
    private void onAddUser() {
        String name = nameField.getText().trim();
        String role = roleField.getText().trim();
        String email = emailField.getText().trim();

        if (name.isEmpty() || role.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Please make sure you've filled out the user's name and role");
            alert.showAndWait();
            return;
        }

        newUser = new User(name, role);
        newUser.setEmail(email);
        dialogStage.close();
    }

    @FXML
    private void onCancel() {
        dialogStage.close();
    }
}