package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class TicketForm {
	private static Text price;

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
		price = new Text("...");
		price.setTextAlignment(TextAlignment.CENTER);
		var backButton = new Button("Back");
		backButton.setOnAction(actionEvent -> moveToPassengerForm());

		// Arranging elements
		var gridPane = new GridPane();
		gridPane.setPadding(new Insets(4.0, 4.0, 4.0, 4.0));
		gridPane.setHgap(4.0);
		gridPane.setVgap(4.0);
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setMinSize(480.0, 480.0);
		gridPane.add(title, 0, 0);
		gridPane.add(price, 0, 1);
		gridPane.add(backButton, 0, 2);

		return new Scene(gridPane);
	}

	private static void updatePrice() {
		price.setText("Base price: $13.74\nAge discount: 60%\nFemale discount: 25%\nTotal: $4.12");
	}

	private static void moveToPassengerForm() {
		BusTicketApp.setScene(PassengerForm.getScene());
	}
}
