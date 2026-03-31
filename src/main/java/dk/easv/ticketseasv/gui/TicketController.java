package dk.easv.ticketseasv.gui;

import dk.easv.ticketseasv.be.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class TicketController {

    @FXML private Label eventNameLbl;
    @FXML private Label eventDateLbl;
    @FXML private Label eventDescriptionLbl;
    @FXML private Label ticketPriceLbl;
    @FXML private ImageView eventImageView;

    public void setEvent(Event event) {
        eventNameLbl.setText(event.getName());
        eventDateLbl.setText(event.getWhen());
        eventDescriptionLbl.setText(event.getDescription());
        ticketPriceLbl.setText("100"); // missing event.getPrice() rn

        if (event.getImagePath() != null) {
            eventImageView.setImage(new Image(event.getImagePath()));
        }
    }
}
