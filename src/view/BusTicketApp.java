package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class BusTicketApp extends Application {
	private static Stage mainStage;

	public static void begin(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		mainStage = stage;
		stage.setTitle("Bus Ticket System");
		setScene(BusRideSelectionForm.createScene());
	}

	public static void setScene(Scene scene) {
		mainStage.setScene(scene);
		mainStage.show();
	}

	public static void blockWith(Stage stage) {
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(mainStage);
	}
}
