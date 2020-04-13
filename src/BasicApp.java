import javafx.application.Application;
import javafx.stage.Stage;

public class BasicApp extends Application {
	public static void begin(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Bus Ticket System");
		stage.show();
	}
}
