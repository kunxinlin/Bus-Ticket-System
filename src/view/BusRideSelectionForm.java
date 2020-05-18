package view;

import busTicketSystem.Ticketing;
import entity.Ticket;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.util.Objects;
import java.util.TreeMap;

public class BusRideSelectionForm {
	private static ChoiceBox<String> destination;
	private static ChoiceBox<String> origin;
	private static ChoiceBox<String> departureTime;
	private static Text etaResult;
	private static Button nextButton;

	private static String previousDestination;
	private static String previousOrigin;
	private static String previousTime;
	private static Scene scene;
	private static Ticket selectedTicket;

	private static TreeMap<String, TreeMap<String, TreeMap<String, Ticket>>> allOptions;

	public static Ticket getSelectedTicket() {
		return selectedTicket;
	}

	public static Scene getScene() {
		if(scene == null)
			scene = createScene();
		return scene;
	}

	private static Scene createScene() {
		loadAvailableTickets();

		// Creating elements
		var title = new Text("Pick the bus stops you will depart and arrive at");
		var destinationText = new Text("Destination");
		destination = new ChoiceBox<>();
		destination.getItems().addAll(allOptions.keySet());
		var originText = new Text("Origin");
		origin = new ChoiceBox<>();
		origin.setDisable(true);
		var departureText = new Text("Departure Time");
		departureTime = new ChoiceBox<>();
		departureTime.setDisable(true);
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
		nextButton = new Button("Next");
		nextButton.setDisable(true);

		// Linking to updater
		destination.setOnAction(actionEvent -> update());
		origin.setOnAction(actionEvent -> update());
		departureTime.setOnAction(actionEvent -> update());
		nextButton.setOnAction(actionEvent -> moveToPassengerForm());

		// Arranging elements
		var gridPane = new GridPane();
		gridPane.setPadding(new Insets(4.0, 4.0, 4.0, 4.0));
		gridPane.setHgap(4.0);
		gridPane.setVgap(4.0);
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setMinSize(480.0, 480.0);
		gridPane.add(title, 0, 0, 2, 1);
		gridPane.add(destinationText, 0, 1);
		gridPane.add(destination, 1, 1);
		gridPane.add(originText, 0, 2);
		gridPane.add(origin, 1, 2);
		gridPane.add(departureText, 0, 3);
		gridPane.add(departureTime, 1, 3);
		gridPane.add(etaText, 0, 4);
		gridPane.add(etaResult, 1, 4);
		gridPane.add(nextButton, 1, 5);
		update();

		return new Scene(gridPane);
	}

	private static void loadAvailableTickets() {
		allOptions = new TreeMap<>();
		for(Ticket ticket : Ticketing.getAvailableTickets()) {
			if(!allOptions.containsKey(ticket.getDestination()))
				allOptions.put(ticket.getDestination(), new TreeMap<>());
			TreeMap<String, TreeMap<String, Ticket>> allDestinationOptions = allOptions.get(ticket.getDestination());
			if(!allDestinationOptions.containsKey(ticket.getOrigin()))
				allDestinationOptions.put(ticket.getOrigin(), new TreeMap<>());
			TreeMap<String, Ticket> allPathOptions = allDestinationOptions.get(ticket.getOrigin());
			allPathOptions.put(ticket.getDate() + " " + ticket.getDeparture(), ticket);
		}
	}

	private static void update() {
		String newDestination = destination.getValue();
		if(!Objects.equals(previousDestination, newDestination)) {
			origin.setDisable(newDestination == null);
			origin.setValue(null);
			ObservableList<String> list = origin.getItems();
			list.clear();
			if(allOptions.containsKey(newDestination))
				list.addAll(allOptions.get(newDestination).keySet());
			previousDestination = newDestination;
			origin.setItems(list);
			if(list.size() == 1)
				origin.setValue(list.get(0));
		}
		String newOrigin = origin.getValue();
		if(!Objects.equals(previousOrigin, newOrigin)) {
			departureTime.setDisable(newOrigin == null);
			departureTime.setValue(null);
			ObservableList<String> list = departureTime.getItems();
			list.clear();
			if(allOptions.containsKey(newDestination)) {
				var originOptions = allOptions.get(newDestination);
				if(newOrigin != null && originOptions.containsKey(newOrigin))
					list.addAll(originOptions.get(newOrigin).keySet());
			}
			previousOrigin = newOrigin;
			departureTime.setItems(list);
			if(list.size() == 1)
				departureTime.setValue(list.get(0));
		}
		String newTime = departureTime.getValue();
		if(!Objects.equals(previousTime, newTime)) {
			previousTime = newTime;
			if(newTime != null) {
				selectedTicket = allOptions.get(newDestination).get(newOrigin).get(newTime);
				etaResult.setText(String.valueOf(selectedTicket.getEta()));
			}
			else {
				selectedTicket = null;
				etaResult.setText("--:--");
			}
		}
		nextButton.setDisable(selectedTicket == null);
	}

	private static void moveToPassengerForm() {
		BusTicketApp.setScene(PassengerForm.getScene());
	}
}