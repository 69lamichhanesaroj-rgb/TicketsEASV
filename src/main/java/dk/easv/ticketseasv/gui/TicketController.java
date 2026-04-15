package dk.easv.ticketseasv.gui;

import dk.easv.ticketseasv.be.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

    @FXML private Button plusBtn;
    @FXML private Button minusBtn;
    @FXML private Label ticketCountLbl;
    @FXML private Label subtotalLbl;
    @FXML private Label totalLbl;

    private int ticketCount = 1;
    private double ticketPrice = 100; // replace with event.getPrice() when connected to the db again

    @FXML
    public void initialize() {
        plusBtn.setOnAction(e -> {
            ticketCount++;
            updatePrice();
        });

        minusBtn.setOnAction(e -> {
            if (ticketCount > 1) {
                ticketCount--;
                updatePrice();
            }
        });

        updatePrice();
    }

    public void setEvent(Event event) {
        eventNameLbl.setText(event.getName());
        eventDateLbl.setText(event.getWhen());
        eventDescriptionLbl.setText(event.getDescription());
        ticketPriceLbl.setText("100"); //also replace with event.getPrice()

        if (event.getImagePath() != null) {
            eventImageView.setImage(new Image(event.getImagePath()));
        }
    }

    private void updatePrice() {
        ticketCountLbl.setText(String.valueOf(ticketCount));

        double total = ticketCount * ticketPrice;

        subtotalLbl.setText(ticketCount + " x " + ticketPrice + " dkk");
        totalLbl.setText(total + " dkk");
    }
}

