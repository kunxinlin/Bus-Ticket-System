package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class ConfirmationForm {
	private static Scene scene;

	public static Scene getScene() {
		if(scene == null)
			scene = createScene();
		return scene;
	}

	private static Scene createScene() {
		// Creating elements
		var title = new Text("Thank you for scheduling your ride. Your information has been entered.");

		// Arranging elements
		var gridPane = new GridPane();
		gridPane.setPadding(new Insets(4.0, 4.0, 4.0, 4.0));
		gridPane.setHgap(4.0);
		gridPane.setVgap(4.0);
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setMinSize(480.0, 480.0);
		gridPane.add(title, 0, 0);

		return new Scene(gridPane);
	}
}
