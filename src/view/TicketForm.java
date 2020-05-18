package view;

import busTicketSystem.Ticketing;
import entity.Ticket;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.List;
import java.util.Objects;

public class TicketForm {
	private static Text priceField;
	private static double price;

	private static Scene scene;

	public static Scene getScene() {
		if(scene == null)
			scene = createScene();
		updatePrice();
		return scene;
	}

	private static Scene createScene() {
		// Creating elements
		var title = new Text("Here's how much the ticket will cost");
		priceField = new Text("...");
		priceField.setTextAlignment(TextAlignment.CENTER);
		var backButton = new Button("Back");
		backButton.setOnAction(actionEvent -> moveToPassengerForm());
		var claimButton = new Button("Claim Ticket");
		claimButton.setOnAction(actionEvent -> claimTicket());

		// Arranging elements
		var gridPane = new GridPane();
		gridPane.setPadding(new Insets(4.0, 4.0, 4.0, 4.0));
		gridPane.setHgap(4.0);
		gridPane.setVgap(4.0);
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setMinSize(480.0, 480.0);
		gridPane.add(title, 0, 0, 2, 1);
		gridPane.add(priceField, 0, 1, 2, 1);
		gridPane.add(backButton, 0, 2);
		gridPane.add(claimButton, 1, 2);

		return new Scene(gridPane);
	}

	private static void updatePrice() {
		Ticket ticket = BusRideSelectionForm.getSelectedTicket();
		double basePrice = ticket.getPrice();
		price = basePrice;
		String ticketDetails = ticket.getOrigin() + " -> " + ticket.getDestination()
							   + "\nDepart: " + ticket.getDate() + " " + ticket.getDeparture() + "\nETA: " + ticket.getEta()
							   + "\nName: " + PassengerForm.getLastName() + ", " + PassengerForm.getFirstName()
							   + "\nPhone: " + PassengerForm.getPhone() + "\nEmail: " + PassengerForm.getEmail()
							   + "\n" + PassengerForm.getGender() + ", " + PassengerForm.getAge() + "\n\n";
		String output = String.format("Base price: $%.2f", price);
		if(Objects.equals(PassengerForm.getGender(), "Female")) {
			output += "\nFemale discount: 25%";
			price *= 0.75;
		}
		if(PassengerForm.getAge() <= 12) {
			output += "\nAge discount: 50%";
			price *= 0.5;
		}
		if(PassengerForm.getAge() >= 60) {
			output += "\nAge discount: 60%";
			price *= 0.4;
		}
		if(price == basePrice)
			priceField.setText(ticketDetails + String.format("$%.2f", price));
		else {
			output += String.format("\nTotal: $%.2f", price);
			priceField.setText(ticketDetails + output);
		}
	}

	private static void moveToPassengerForm() {
		BusTicketApp.setScene(PassengerForm.getScene());
	}

	private static void claimTicket() {
		Ticket selectedTicket = BusRideSelectionForm.getSelectedTicket();
		List<Ticket> allTickets = Ticketing.getAvailableTickets();
		boolean canContinue = false;
		for(Ticket t : allTickets) {
			if(t.getTicketNumber() == selectedTicket.getTicketNumber()) {
				canContinue = true;
				break;
			}
		}
		if(canContinue) {
			Ticketing.updateInfo(selectedTicket.getTicketNumber(),
					PassengerForm.getFirstName(),
					PassengerForm.getLastName(),
					PassengerForm.getEmail(),
					PassengerForm.getPhone(),
					PassengerForm.getGender(),
					PassengerForm.getAge());
			BusTicketApp.setScene(ConfirmationForm.getScene());
		}
		else
			PopupApp.displayErrorMessage("Ticket has expired. Please select another ride.");
	}
}
