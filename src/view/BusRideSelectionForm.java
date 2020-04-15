package view;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.util.Objects;

public class BusRideSelectionForm {
	private static ChoiceBox<String> destination;
	private static ChoiceBox<String> origin;
	private static DatePicker departureDate;
	private static ChoiceBox<String> departureTime;
	private static Text etaResult;

	private static String previousDestination;
	private static Scene scene;

	public static Scene getScene() {
		if(scene == null)
			scene = createScene();
		return scene;
	}

	private static Scene createScene() {
		// Creating elements
		var title = new Text("Pick the bus stops you will depart and arrive at");
		var destinationText = new Text("Destination");
		destination = new ChoiceBox<>();
		destination.getItems().add("Atlanta");
		destination.getItems().add("Orlando");
		var originText = new Text("Origin");
		origin = new ChoiceBox<>();
		var departureText = new Text("Departure Time");
		departureDate = new DatePicker();
		departureTime = new ChoiceBox<>();
		for(int pm = 0; pm <= 1; pm++) {
			for(int hr = 0; hr < 12; hr++) {
				for(int min = 0; min < 60; min += 30) {
					String suffix = (pm == 1 ? " PM" : " AM");
					String prefix = (hr == 0 ? "12:" : hr + ":");
					departureTime.getItems().add(prefix + String.format("%02d", min) + suffix);
				}
			}
		}
		var etaText = new Text("ETA");
		etaResult = new Text("--:--");
		var nextButton = new Button("Next");

		// Linking to updater
		destination.setOnAction(actionEvent -> update());
		origin.setOnAction(actionEvent -> update());
		departureDate.setOnAction(actionEvent -> update());
		departureTime.setOnAction(actionEvent -> update());
		nextButton.setOnAction(actionEvent -> moveToPassengerForm());

		// Arranging elements
		var gridPane = new GridPane();
		gridPane.setPadding(new Insets(4.0, 4.0, 4.0, 4.0));
		gridPane.setHgap(4.0);
		gridPane.setVgap(4.0);
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setMinSize(480.0, 480.0);
		gridPane.add(title, 0, 0, 3, 1);
		gridPane.add(destinationText, 0, 1);
		gridPane.add(destination, 1, 1, 2, 1);
		gridPane.add(originText, 0, 2);
		gridPane.add(origin, 1, 2, 2, 1);
		gridPane.add(departureText, 0, 3);
		gridPane.add(departureDate, 1, 3);
		gridPane.add(departureTime, 2, 3);
		gridPane.add(etaText, 0, 4);
		gridPane.add(etaResult, 1, 4, 2, 1);
		gridPane.add(nextButton, 1, 5);
		update();

		return new Scene(gridPane);
	}

	private static void update() {
		String newDestination = destination.getValue();
		if(!Objects.equals(previousDestination, newDestination)) {
			origin.setDisable(newDestination == null);
			origin.setValue(null);
			ObservableList<String> list = origin.getItems();
			list.clear();
			list.addAll(destination.getItems());
			list.remove(newDestination);
			previousDestination = newDestination;
			origin.setItems(list);
		}
	}

	private static void moveToPassengerForm() {
		BusTicketApp.setScene(PassengerForm.getScene());
	}
}