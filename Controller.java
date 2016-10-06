package Project;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.LoadException;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.LinkedHashSet;
import java.util.Set;


public class Controller {

	@FXML
	private Label lblAthleteInformation;
	@FXML
	private Label lblFirstName;
	@FXML
	private Label lblLastName;
	@FXML
	private Label lblGender;
	@FXML
	private Label lblAthleteNumber;
	@FXML
	private Label lblRunTime;
	@FXML
	private Label lblBikeTime;
	@FXML
	private Label lblSwimTime;
	@FXML
	private TextField txtFirstName;
	@FXML
	private TextField txtLastName;
	@FXML
	private TextField txtNumber;
	@FXML
	private TextField txtRunTime;
	@FXML
	private TextField txtBikeTime;
	@FXML
	private TextField txtSwimTime;
	@FXML
	private RadioButton rbMale;
	@FXML
	private RadioButton rbFemale;
	@FXML
	private TextArea txtaResults;
	@FXML
	private Button btnSubmit;
	@FXML
	private Button btnCalculate;
	
	public void initialize(){
		//event handler. activates when btnSubmit is clicked. it takes the input data, creates objects, puts the objects in a set, and sends them to the database
	    btnSubmit.setOnAction(e -> { //lambda expression simplifies coding
	    	//create objects
			Athlete athleteInfo = new Athlete(txtFirstName.getText(), txtLastName.getText(), getGender(), txtNumber.getText());
			Running runTime = new Running(Long.parseLong(txtRunTime.getText()));
			Swimming swimTime = new Swimming(Long.parseLong(txtSwimTime.getText()));
			Biking bikeTime = new Biking(Long.parseLong(txtBikeTime.getText()));
			//store in set
			LinkedHashSet<Object> currentAthlete = new LinkedHashSet<>();
			currentAthlete.add(athleteInfo);
			currentAthlete.add(runTime);
			currentAthlete.add(swimTime);
			currentAthlete.add(bikeTime);
			//send data to database
			//sendData(currentAthlete);

			//temp test
			for (Object element : currentAthlete){
				System.out.println(element.toString());
			}
		});
	}

	//method observes the radio buttons to determine the athlete's gender
	private String getGender(){
	    String response = "null";
		try{
			if (rbMale.isSelected()){
				response = rbMale.getText(); //returns "Male"
			} else if (rbFemale.isSelected()){
				response = rbFemale.getText(); //returns "Female"
			}else {
				throw new Exception();
			}
		} catch (Exception ex){
			ex.printStackTrace();
		}
		return response;
	}

	//method to send data to the mysql database
	/*private void sendData(LinkedHashSet currentAthlete){

	}*/
}