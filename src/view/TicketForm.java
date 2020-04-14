package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

public class TicketForm {
	public static Scene createScene() {
		// Creating elements

		// Arranging elements
		var gridPane = new GridPane();
		gridPane.setPadding(new Insets(4.0, 4.0, 4.0, 4.0));
		gridPane.setHgap(4.0);
		gridPane.setVgap(4.0);
		gridPane.setAlignment(Pos.CENTER);

		return new Scene(gridPane);
	}
}
