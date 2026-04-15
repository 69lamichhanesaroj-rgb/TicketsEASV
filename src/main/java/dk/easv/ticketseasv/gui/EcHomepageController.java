package dk.easv.ticketseasv.gui;

import dk.easv.ticketseasv.be.Event;
import dk.easv.ticketseasv.dal.EventDAO;
import javafx.collections.*;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import static dk.easv.ticketseasv.gui.AddEventController.eventLogic;

public class EcHomepageController {

    @FXML private ListView<Event> eventList;
    @FXML private TilePane eventPane;
    @FXML private TextField searchField;
    @FXML private HBox userBox;  // So u can click the user
    private ContextMenu userMenu;

    private static final double CARD_WIDTH = 220;
    private static final double GAP = 15;

    private static ObservableList<Event> masterData = FXCollections.observableArrayList();
    private FilteredList<Event> filteredData;

    @FXML
    public void initialize() {


//        if (masterData.isEmpty()) {
//            masterData.addAll(
//                    new Event("The Voice Karaoke", "Friday, February 30, 2026", null),             new Event("Tech Conference 2026", "Saturday, June 4, 2026", null),
//                    new Event("Sankt Hans/Bonfire", "Monday, June 23, 2026", null),             new Event("Summer Sausage Party", "Sunday, August 27, 2026", null),
//                    new Event("Hackathon 2026", "Saturday, September 15, 2026", null),
//                    new Event("Halloween Party", "Sunday, October 30, 2026", null)
//            );
//        }
        if (masterData.isEmpty()) {
            EventDAO eventDAO = new EventDAO();
            try {
                masterData.addAll(eventDAO.getAllEvents());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        filteredData = new FilteredList<>(masterData, p -> true);

        setupAutoColumns(eventPane);
        updateEventGrid();

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

        updateEventGrid();
    }

    @FXML
    private void onSortClicked() {
        FXCollections.sort(masterData,
                (a, b) -> a.getName().compareToIgnoreCase(b.getName()));

        updateEventGrid();
    }

    private void openEventDetail(Event event, Node source) {

        if (event == null) {
            System.out.println("ERROR: Event is null!");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/dk/easv/ticketseasv/butTicketTemplate.fxml")
            );

            Parent root = loader.load();

            TicketController controller = loader.getController();
            controller.setEvent(event);

            Stage stage = (Stage) source.getScene().getWindow();
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
            stage.initOwner(eventPane.getScene().getWindow());
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

    private void updateEventGrid() {
        eventPane.getChildren().clear();

        for (Event event : filteredData) {
            VBox card = createEventCard(event);
            eventPane.getChildren().add(card);
        }
    }

    private VBox createEventCard(Event event) {

        VBox card = new VBox(10);
        System.out.println("Creating card for: " + event);
        card.setPrefWidth(CARD_WIDTH);

        card.setStyle("""
        -fx-background-color: white;
        -fx-background-radius: 10;
        -fx-padding: 15;
        -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10,0,0,4);
    """);

        Label title = new Label(event.getName());
        title.setStyle("-fx-font-size:16px; -fx-font-weight:bold;");

        Label date = new Label(event.getDate());
        date.setStyle("-fx-text-fill: gray;");

        Button menuBtn = new Button("⋮");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox top = new HBox(title, spacer, menuBtn);

        card.getChildren().addAll(top, date);

        // Hover effect
        card.setOnMouseEntered(e -> {
            card.setScaleX(1.03);
            card.setScaleY(1.03);
        });

        card.setOnMouseExited(e -> {
            card.setScaleX(1);
            card.setScaleY(1);
        });

        // Click → open event
        card.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                openEventDetail(event, (Node) e.getSource());
            }
        });

        return card;
    }

    private void setupAutoColumns(TilePane pane) {
        pane.widthProperty().addListener((obs, oldVal, newVal) -> {
            double width = newVal.doubleValue();

            int columns = (int) (width / (CARD_WIDTH + GAP));
            if (columns < 1) columns = 1;

            pane.setPrefColumns(columns);
        });
    }
}