package dk.easv.ticketseasv.gui;

import dk.easv.ticketseasv.be.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class EventCell extends ListCell<Event> {

    private HBox root = new HBox(15);
    private VBox textBox = new VBox(5);
    private Label nameLbl = new Label();
    private Label dateLbl = new Label();
    private Region spacer = new Region();
    private Button menuBtn = new Button("⋮");

    public EventCell() {

        nameLbl.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");
        dateLbl.setStyle("-fx-text-fill: gray;");

        textBox.getChildren().addAll(nameLbl, dateLbl);
        HBox.setHgrow(spacer, Priority.ALWAYS);

        root.getChildren().addAll(textBox, spacer, menuBtn);

        root.getStyleClass().add("event-card");

        // Added options to choose between after clicking the button
        ContextMenu menu = new ContextMenu();

        MenuItem edit = new MenuItem("Edit event");
        MenuItem manage = new MenuItem("Manage coordinators");
        MenuItem delete = new MenuItem("Delete event");

        menu.getItems().addAll(edit, manage, delete);

        // shows the options + peek-a-boo with the clicking
        menuBtn.setOnAction(e -> {
            if (menu.isShowing()) {
                menu.hide();
            } else {
                menu.show(menuBtn, javafx.geometry.Side.BOTTOM, 0, 0);
            }
        });

        edit.setOnAction(e -> {

            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/dk/easv/ticketseasv/add-event.fxml")
                );

                VBox root = loader.load();

                Stage stage = new Stage();
                stage.setTitle("Manage");
                stage.setScene(new Scene(root));
                stage.show();

                AddEventController controller = loader.getController();
                controller.setEventToEdit(getItem());

                System.out.println("Manage: " + getItem().getName());

            } catch (Exception ex) {
                ex.printStackTrace(); // IMPORTANT for debugging
            }


            System.out.println("Edit: " + getItem().getName());
        });

        manage.setOnAction(e -> {
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/dk/easv/ticketseasv/manageCoordinator.fxml")
                );

                VBox root = loader.load();

                Stage stage = new Stage();
                stage.setTitle("Manage");
                stage.setScene(new Scene(root));
                stage.show();

                System.out.println("Manage: " + getItem().getName());

            } catch (Exception ex) {
                ex.printStackTrace(); // IMPORTANT for debugging
            }
        });

        delete.setOnAction(e -> {

            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/dk/easv/ticketseasv/delete-event.fxml")
                );

                VBox root = loader.load();

                Stage stage = new Stage();
                stage.setTitle("Delete Event");
                stage.setScene(new Scene(root));
                stage.show();

            } catch (Exception ex) {
                ex.printStackTrace(); // IMPORTANT for debugging
            }

            System.out.println("Delete: " + getItem().getName());
            getListView().getItems().remove(getItem());
        });

        // makes it react to your cursor, they all wanna be picked frfr
        root.setOnMouseEntered(e -> {
            root.setScaleX(1.02);
            root.setScaleY(1.02);
        });

        root.setOnMouseExited(e -> {
            root.setScaleX(1.0);
            root.setScaleY(1.0);
        });
    }

    @Override
    protected void updateItem(Event event, boolean empty) {
        super.updateItem(event, empty);

        if (empty || event == null) {
            setText(null);
            setGraphic(null);
        } else {
            nameLbl.setText(event.getName());
            dateLbl.setText(event.getDate());
            setGraphic(root);
        }
    }
}