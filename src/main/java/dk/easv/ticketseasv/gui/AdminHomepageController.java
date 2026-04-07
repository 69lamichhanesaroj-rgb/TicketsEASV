package dk.easv.ticketseasv.gui;

import dk.easv.ticketseasv.be.Event;
import dk.easv.ticketseasv.be.User;
import dk.easv.ticketseasv.dal.EventDAO;
import dk.easv.ticketseasv.dal.UsersDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.List;


public class AdminHomepageController {

    @FXML private ListView<Event> eventList;
    @FXML private TextField searchField;


    @FXML private ListView<User> userList;
    @FXML private TextField userSearchField;

    @FXML private Button newUserBtn; // Will probably remove later

    // User top bar "part"
    @FXML private HBox userBox;
    private ContextMenu userMenu;

    // Buttons 4 switching
    @FXML private Button eventsBtn;
    @FXML private Button usersBtn;

    // Content panes
    @FXML private VBox eventsPane;
    @FXML private VBox usersPane;

    private static ObservableList<Event> masterData = FXCollections.observableArrayList();
    private FilteredList<Event> filteredData;

    private static ObservableList<User> userMasterData = FXCollections.observableArrayList();
    private FilteredList<User> filteredUsers;

    @FXML
    public void initialize() {
        /* if (masterData.isEmpty()) {
            masterData.addAll(
                    new Event("The Voice Karaoke", "Friday, February 30, 2026", null),
                    new Event("Tech Conference 2026", "Saturday, June 4, 2026", null),
                    new Event("Sankt Hans/Bonfire", "Monday, June 23, 2026", null),
                    new Event("Summer Sausage Party", "Sunday, August 27, 2026", null),
                    new Event("Hackathon pt 2 2026", "Saturday, September 15, 2026", null),
                    new Event("Halloween Party (scary)", "Sunday, October 30, 2026", null)
            );
        }
         */
        if (masterData.isEmpty()) {
            EventDAO eventDAO = new EventDAO();
            try {
                masterData.addAll(eventDAO.getAllEvents());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        filteredData = new FilteredList<>(masterData, e -> true);
        eventList.setItems(filteredData);
        eventList.setCellFactory(list -> new EventCell("Admin"));
        
        /* if (userMasterData.isEmpty()) {
            userMasterData.addAll(
                    new User("Bob", "Event Coordinator"),
                    new User("Bobby", "Event Coordinator"),
                    new User("Bobson", "Admin")
            );
        }

         */


        if (userMasterData.isEmpty()) {
            UsersDAO usersDAO = new UsersDAO();
            try {
                userMasterData.addAll(usersDAO.getAllUsers());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        filteredUsers = new FilteredList<>(userMasterData, u -> true);
        userList.setItems(filteredUsers);


        userList.setCellFactory(list -> new ListCell<User>() {
            @Override
            protected void updateItem(User user, boolean empty) {
                super.updateItem(user, empty);
                if (empty || user == null) {
                    setText(null);
                } else {
                    setText(user.getUsername() + " (" + user.getRole() + ")");
                }
            }
        });

        // makes the user "part"/icon thingy clickable n gives them the sign-out option
        userMenu = new ContextMenu();
        MenuItem logout = new MenuItem("Log out");
        logout.setOnAction(e -> handleLogout());
        userMenu.getItems().add(logout);

        userBox.setOnMouseClicked(e -> {
            if (userMenu.isShowing()) userMenu.hide();
            else userMenu.show(userBox, javafx.geometry.Side.BOTTOM, 0, 0);
        });


        eventsBtn.setOnAction(e -> switchToEvents());
        usersBtn.setOnAction(e -> switchToUsers());
        switchToEvents();

        // live search for both events and users
        searchField.textProperty().addListener((obs, oldVal, newVal) -> filterEventList(newVal));
        userSearchField.textProperty().addListener((obs, oldVal, newVal) -> filterUserList(newVal));


        eventList.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Event selected = eventList.getSelectionModel().getSelectedItem();
                if (selected != null) openEventDetail(selected);
            }
        });
    }


    private void switchToEvents() {
        eventsPane.setVisible(true);
        eventsPane.setManaged(true);
        usersPane.setVisible(false);
        usersPane.setManaged(false);
    }

    private void switchToUsers() {
        eventsPane.setVisible(false);
        eventsPane.setManaged(false);
        usersPane.setVisible(true);
        usersPane.setManaged(true);
    }


    private void filterEventList(String filter) {
        String lower = filter.toLowerCase();
        filteredData.setPredicate(e -> e.getName().toLowerCase().contains(lower));
    }

    private void filterUserList(String filter) {
        String lower = filter.toLowerCase();
        filteredUsers.setPredicate(u -> u.getUsername().toLowerCase().contains(lower));
    }


    @FXML
    private void onSortClicked() {
        if (eventsPane.isVisible()) {
            FXCollections.sort(masterData, (a,b) -> a.getName().compareToIgnoreCase(b.getName()));
        } else {
            FXCollections.sort(userMasterData, (a,b) -> a.getUsername().compareToIgnoreCase(b.getUsername()));
        }
    }

    private void openEventDetail(Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dk.easv.ticketseasv.buyTicket.fxml"));
            Parent root = loader.load();
            TicketController controller = loader.getController();
            controller.setEvent(event);
            Stage stage = (Stage) eventList.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) { e.printStackTrace(); }
    }

    private void handleLogout() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../login-view.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) userBox.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) { e.printStackTrace(); }
    }

    @FXML
    private void onNewUserClicked() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../add-user.fxml"));
            VBox root = loader.load();

            AddUserController controller = loader.getController();

            Stage stage = new Stage();
            stage.setTitle("Add New User");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(userBox.getScene().getWindow());
            stage.setScene(new Scene(root));
            stage.setMinWidth(400);
            stage.setMinHeight(270);

            controller.setDialogStage(stage);

            stage.showAndWait();
            /*

            User addedUser = controller.getNewUser();
            if (addedUser != null) {
                userMasterData.add(addedUser); // Updates Listview automatically
            }

            This should be requesting the database to refresh the user list, since the new user doesn't have assigned id
            Same goes for the events, but I don't want to find this function right now
            */

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}