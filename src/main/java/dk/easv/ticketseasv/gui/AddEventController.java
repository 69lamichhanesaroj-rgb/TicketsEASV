package dk.easv.ticketseasv.gui;

import dk.easv.ticketseasv.be.Event;
import dk.easv.ticketseasv.bll.EventLogic;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Time;
import java.time.format.DateTimeFormatter;

public class AddEventController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField moreInfoField;
    @FXML
    private TextField startTimeField;
    @FXML
    private TextField endTimeField;
    @FXML
    private TextField locationField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField whenField;
    @FXML
    private TextField imagePathField;
    @FXML
    private Label titleLabel;
    @FXML
    private Button submitButton;

    private Stage dialogStage;
    private Event newEvent;

    private boolean isEditing = false;
    private Event eventToEdit = null;

    final static EventLogic eventLogic = new EventLogic();

    private static int idCounter = 1000; // simple auto id generator rn. Change whenever possible ^^

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
        try {
            //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

            String name = nameField.getText();
            String moreInfo = moreInfoField.getText();
            String when = whenField.getText();
            String starTime = startTimeField.getText();
            String endTime = endTimeField.getText();
            String location = locationField.getText();
            String description = descriptionField.getText();
            String imagePath = imagePathField.getText();

            newEvent = new Event(
                    idCounter++,
                    name,
                    moreInfo,
                    when,
                    starTime,
                    endTime,
                    location,
                    description,
                    imagePath
            );
            if (isEditing) {
                eventLogic.editEvent(newEvent);
            } else {
                eventLogic.createEvent(newEvent);
            }

            dialogStage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setEventToEdit(Event event) {
        this.eventToEdit = event;
        isEditing = true;
        titleLabel.setText("Edit Event");
        submitButton.setText("Save Event");
        nameField.setText(eventToEdit.getName());
        moreInfoField.setText(eventToEdit.getMoreInfo());
        whenField.setText(eventToEdit.getWhen());
        startTimeField.setText(eventToEdit.getStarTime());
        endTimeField.setText(eventToEdit.getEndTime());
        locationField.setText(eventToEdit.getLocation());
        descriptionField.setText(eventToEdit.getDescription());
        imagePathField.setText(eventToEdit.getImagePath());

    }
}
