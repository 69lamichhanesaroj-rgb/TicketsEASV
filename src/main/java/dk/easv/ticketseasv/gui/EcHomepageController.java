package dk.easv.ticketseasv.gui;

import dk.easv.ticketseasv.be.Event;
import javafx.collections.*;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class EcHomepageController {

    @FXML private ListView<Event> eventList;
    @FXML private TextField searchField;
    @FXML private HBox userBox;  // So u can click the user
    private ContextMenu userMenu;

    private static ObservableList<Event> masterData = FXCollections.observableArrayList();
    private FilteredList<Event> filteredData;

    @FXML
    public void initialize() {


        if (masterData.isEmpty()) {
            masterData.addAll(
                    new Event("The Voice Karaoke", "Friday, February 30, 2026", null),
                    new Event("Tech Conference 2026", "Saturday, June 4, 2026", null),
                    new Event("Sankt Hans/Bonfire", "Monday, June 23, 2026", null),
                    new Event("Summer Sausage Party", "Sunday, August 27, 2026", null),
                    new Event("Hackathon 2026", "Saturday, September 15, 2026", null),
                    new Event("Halloween Party", "Sunday, October 30, 2026", null)
            );
        }

        filteredData = new FilteredList<>(masterData, p -> true);

        eventList.setItems(filteredData);
        eventList.setCellFactory(list -> new EventCell());

        // Double click to open an event
        eventList.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Event selected = eventList.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    openEventDetail(selected);
                }
            }
        });

        // make the profile thingy click-able
        userMenu = new ContextMenu();
        MenuItem logout = new MenuItem("Log out");
        logout.setStyle("-fx-font-size: 14px; -fx-padding: 1 110 1 1;"); //the menu was comedically small before xdd
        userMenu.getItems().add(logout);


        logout.setOnAction(e -> handleLogout());

        // Make the menu appear or disappear when u click on it, peek-a-boo fr
        userBox.setOnMouseClicked(e -> {
            if (userMenu.isShowing()) {
                userMenu.hide();
            } else {
                userMenu.show(userBox, javafx.geometry.Side.BOTTOM, 0, 0);
            }
        });


        searchField.textProperty().addListener((obs, oldVal, newVal) -> filterList(newVal));
    }


    private void filterList(String filter) {
        String lower = filter.toLowerCase();

        filteredData.setPredicate(event ->
                event.getName().toLowerCase().contains(lower)
        );
    }

    @FXML
    private void onSortClicked() {
        FXCollections.sort(masterData,
                (a, b) -> a.getName().compareToIgnoreCase(b.getName()));
    }

    // Switch to th clicked event (instead of just opening a new window, this felt like a better option)
    private void openEventDetail(Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/view/buyTicket.fxml")
            );

            Parent root = loader.load();

            TicketController controller = loader.getController();
            controller.setEvent(event);

            Stage stage = (Stage) eventList.getScene().getWindow();
            stage.setScene(new Scene(root));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleLogout() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("../login-view.fxml")
            );
            Parent root = loader.load();
            Stage stage = (Stage) userBox.getScene().getWindow();
            stage.setScene(new Scene(root));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onNewEventClicked() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dk/easv/ticketseasv/add-event.fxml"));
            VBox root = loader.load();

            AddEventController controller = loader.getController();

            Stage stage = new Stage();
            stage.setTitle("Add New Event");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(eventList.getScene().getWindow());
            stage.setScene(new Scene(root));

            controller.setDialogStage(stage);
            stage.showAndWait();

            Event addedEvent = controller.getNewEvent();
            if (addedEvent != null) {
                masterData.add(addedEvent);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}