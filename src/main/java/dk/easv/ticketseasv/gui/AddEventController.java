package dk.easv.ticketseasv.gui;

import dk.easv.ticketseasv.be.Event;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Time;
import java.time.format.DateTimeFormatter;

public class AddEventController {

    @FXML private TextField nameField;
    @FXML private TextField moreInfoField;
    @FXML private TextField startTimeField;
    @FXML private TextField endTimeField;
    @FXML private TextField locationField;
    @FXML private TextField descriptionField;
    @FXML private TextField whenField;
    @FXML private TextField imagePathField;

    private Stage dialogStage;
    private Event newEvent;

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

            dialogStage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
