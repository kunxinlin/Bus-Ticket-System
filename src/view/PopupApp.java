package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PopupApp {
	private static Stage stage;

	public static void displayErrorMessage(String message) {
		stage = new Stage();
		var text = new Text(message);
		var button = new Button("OK");
		button.setOnAction(actionEvent -> close());
		var gridPane = new GridPane();
		gridPane.setPadding(new Insets(4.0, 4.0, 4.0, 4.0));
		gridPane.setHgap(4.0);
		gridPane.setVgap(4.0);
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setMinSize(160.0, 40.0);
		gridPane.add(text, 0, 0);
		gridPane.add(button, 0, 1);
		stage.setScene(new Scene(gridPane));
		stage.setResizable(false);
		BusTicketApp.blockWith(stage);
		stage.show();
	}

	private static void close() {
		stage.close();
	}
}
