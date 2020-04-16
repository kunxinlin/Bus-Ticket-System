package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class PassengerForm {
	private static TextField firstName;
	private static TextField lastName;
	private static TextField email;
	private static TextField phone;
	private static ToggleGroup gender;
	private static TextField age;

	private static Scene scene;

	public static Scene getScene() {
		if(scene == null)
			scene = createScene();
		return scene;
	}

	private static Scene createScene() {
		// Creating elements
		var title = new Text("Please enter your information");
		var firstNameText = new Text("First Name");
		firstName = new TextField();
		var lastNameText = new Text("Last Name");
		lastName = new TextField();
		var emailText = new Text("Email");
		email = new TextField();
		var phoneText = new Text("Phone");
		phone = new TextField();
		var genderText = new Text("Gender");
		gender = new ToggleGroup();
		var male = new RadioButton("Male");
		male.setToggleGroup(gender);
		var female = new RadioButton("Female");
		female.setToggleGroup(gender);
		var ageText = new Text("Age");
		age = new TextField();
		var backButton = new Button("Back");
		backButton.setOnAction(actionEvent -> moveToRideSelectionForm());
		var nextButton = new Button("Next");
		nextButton.setOnAction(actionEvent -> moveToTicketForm());

		// Arranging elements
		var gridPane = new GridPane();
		gridPane.setPadding(new Insets(4.0, 4.0, 4.0, 4.0));
		gridPane.setHgap(4.0);
		gridPane.setVgap(4.0);
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setMinSize(480.0, 480.0);
		gridPane.add(title, 0, 0, 3, 1);
		gridPane.add(firstNameText, 0, 1);
		gridPane.add(firstName, 1, 1, 2, 1);
		gridPane.add(lastNameText, 0, 2);
		gridPane.add(lastName, 1, 2, 2, 1);
		gridPane.add(phoneText, 0, 3);
		gridPane.add(phone, 1, 3, 2, 1);
		gridPane.add(emailText, 0, 4);
		gridPane.add(email, 1, 4, 2, 1);
		gridPane.add(genderText, 0, 5);
		gridPane.add(male, 1, 5);
		gridPane.add(female, 2, 5);
		gridPane.add(ageText, 0, 6);
		gridPane.add(age, 1, 6, 2, 1);
		gridPane.add(nextButton, 1, 7);
		gridPane.add(backButton, 0, 7);

		return new Scene(gridPane);
	}

	private static void moveToRideSelectionForm() {
		BusTicketApp.setScene(BusRideSelectionForm.getScene());
	}

	private static void moveToTicketForm() {
		if(getFirstName().equals("") || getLastName().equals("") || getEmail().equals("") || getPhone().equals("") || getAge() == -2 || getFirstName().equals("") || getGender() == null)
			PopupApp.displayErrorMessage("Please fill in all the fields.");
		else if(getPhone().length() < 7)
			PopupApp.displayErrorMessage("Please enter a valid phone number.");
		else if(!getEmail().matches("^.*@.*\\..*"))
			PopupApp.displayErrorMessage("Please enter a valid email.");
		else if(getAge() < 0)
			PopupApp.displayErrorMessage("Please enter a valid numerical age.");
		else
			BusTicketApp.setScene(TicketForm.getScene());
	}

	public static String getFirstName() {
		return firstName.getText();
	}

	public static String getLastName() {
		return lastName.getText();
	}

	public static String getEmail() {
		return email.getText();
	}

	public static String getPhone() {
		return phone.getText();
	}

	public static int getAge() {
		if(age.getText().equals(""))
			return -2;
		try {
			return Math.max(Integer.parseInt(age.getText()), -1);
		}
		catch(Exception e) {
			return -1;
		}
	}

	public static String getGender() {
		if(gender.getSelectedToggle() == null)
			return null;
		return ((RadioButton)gender.getSelectedToggle()).getText();
	}
}
