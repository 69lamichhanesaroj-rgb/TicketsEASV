package dk.easv.ticketseasv.gui;

import dk.easv.ticketseasv.be.Event;
import dk.easv.ticketseasv.bll.EventLogic;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddEventController {

    @FXML private TextField nameField;
    @FXML private TextField moreInfoField;
    @FXML private TextField startTimeField;
    @FXML private TextField endTimeField;
    @FXML private TextField locationField;
    @FXML private TextField descriptionField;
    @FXML private TextField whenField;
    @FXML private TextField imagePathField;
    @FXML private Label titleLabel;
    @FXML private Label errorLabel;
    @FXML private Button submitButton;

    private Stage dialogStage;
    private Event newEvent;
    private boolean isEditing = false;
    private Event eventToEdit = null;

    final static EventLogic eventLogic = new EventLogic();

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public Event getNewEvent() {
        return newEvent;
    }

    @FXML
    private void onCancel() {
        dialogStage.close();
    }

    @FXML
    private void onAddEvent() {
        String name = nameField.getText().trim();
        String date = whenField.getText().trim();
        String startTime = startTimeField.getText().trim();
        String endTime = endTimeField.getText().trim();
        String location = locationField.getText().trim();
        String description = descriptionField.getText().trim();

        if (name.isEmpty() || date.isEmpty() || location.isEmpty()) {
            errorLabel.setText("Name, date and location are required.");
            return;
        }

        if (isEditing) {
            newEvent = new Event(
                    name, description, startTime, endTime, date,
                    location, eventToEdit.getId()
            );
            boolean success = eventLogic.editEvent(newEvent);
            if (!success) {
                errorLabel.setText("Failed to save changes. Please try again.");
                return;
            }
        } else {
            newEvent = new Event(
                    name, description, startTime, endTime, date,
                    location, newEvent.getId()
            );
            int generatedId = eventLogic.createEvent(newEvent);
            if (generatedId == 0) {
                errorLabel.setText("Failed to create event. Please try again.");
                return;
            }
            newEvent.setId(generatedId);
        }

        dialogStage.close();
    }

    public void setEventToEdit(Event event) {
        this.eventToEdit = event;
        isEditing = true;
        titleLabel.setText("Edit Event");
        submitButton.setText("Save Changes");
        nameField.setText(event.getName());
        whenField.setText(event.getDate());
        startTimeField.setText(event.getStarTime());
        endTimeField.setText(event.getEndTime());
        locationField.setText(event.getLocation());
        descriptionField.setText(event.getDescription());
    }
}
