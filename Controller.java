package Project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.LinkedHashSet;

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
	private Label lblResults;
	@FXML
	private Label lblMaleResult;
	@FXML
	private Label lblFemaleResult;
	@FXML
	private MenuBar menuMain;
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
	private TextArea txtaMaleResults;
	@FXML
	private TextArea txtaFemaleResults;
	@FXML
	private Button btnSubmit;
	@FXML
	private Button btnCalculate;
	@FXML
	private Label lblErrFName;
	@FXML
	private Label lblErrLName;
	@FXML
	private Label lblErrRun;
	@FXML
	private Label lblErrSwim;
	@FXML
	private Label lblErrBike;
	@FXML
	private Label lblErrGender;

	public void initialize(){
		//event handler. activates when btnSubmit is clicked. it takes the input data, creates objects, puts the objects in a set, and sends them to the database
	    btnSubmit.setOnAction(e -> { //lambda expression simplifies coding
	    	//create objects
			Athlete athleteInfo = storeAthlete();
			Running runTime = storeRunTime();
			Swimming swimTime = storeSwimTime();
			Biking bikeTime = storeBikeTime();
			//validate objects again before storing in set
			validateObjects(athleteInfo, runTime, swimTime, bikeTime);
		});
	    
	    
	    
	    btnCalculate.setOnAction(e ->{
	    	try{
	    		FXMLLoader loader = new FXMLLoader();
	    		loader.setLocation(getClass().getResource("DisplayData.fxml"));
	    		AnchorPane rootLayout = (AnchorPane) loader.load();
				Scene scene = new Scene(rootLayout);
				Stage primaryStage = new Stage();
				primaryStage.setScene(scene);
				primaryStage.show();

	    	} catch(Exception ex){
	    		ex.printStackTrace();
	    	}

	    });
	}
	//method makes sure that objects are ready to be added to the set
	private void validateObjects(Athlete athleteInfo, Running runTime, Swimming swimTime, Biking bikeTime){
		if (!(athleteInfo.getFirstName().matches("badjuju")) && runTime.getEndTime() > 0 && swimTime.getEndTime() > 0 && bikeTime.getEndTime() > 0){
			//create linked hash set to store objects
			LinkedHashSet<Object> currentAthlete = storeSet(athleteInfo, runTime, swimTime, bikeTime);
			//temp test
			for (Object element : currentAthlete){
				System.out.println(element.toString());
			}
		}
		//if the objects are ready, they are stored then sent to the database
		/*sendData(currentAthlete);*/
	}

	//method observes the radio buttons to determine the athlete's gender
	private String storeGender(){
	    String response = null;
		lblErrGender.setVisible(false);
		try{
			if (rbMale.isSelected()){
				response = rbMale.getText(); //returns "Male"
			} else if (rbFemale.isSelected()){
				response = rbFemale.getText(); //returns "Female"
			}else {
				throw new Exception();
			}
		} catch (Exception ex){
			displayError(6);
		}
		return response;
	}

	//Athlete data validation
	private Athlete storeAthlete(){
		lblErrLName.setVisible(false);
		lblErrFName.setVisible(false);
		if((! txtFirstName.getText().matches(".*[^A-Za-z].*")) && (! txtLastName.getText().matches(".*[^A-Za-z].*"))){
			return new Athlete(txtFirstName.getText(), txtLastName.getText(), storeGender(), txtNumber.getText());
		}else{
			//display red error text
            if(txtFirstName.getText().matches(".*[^A-Za-z].*")){
                displayError(1);
			}
			if(txtLastName.getText().matches(".*[^A-Za-z].*")){
				displayError(2);
			}
			return new Athlete("badjuju",  txtLastName.getText(), storeGender(), txtNumber.getText());
		}
	}

	//Time data validation
	private Running storeRunTime(){
		lblErrRun.setVisible(false);
		if ((! txtRunTime.getText().matches(".*[^0-9].*")) && Long.parseLong(txtRunTime.getText()) > 0) {
			return new Running(Long.parseLong(txtRunTime.getText()));
		}else{
			//display red error text
            displayError(3);
			return new Running(-1);
		}
	}
	private Swimming storeSwimTime(){
		lblErrSwim.setVisible(false);
		if((! txtSwimTime.getText().matches(".*[^0-9].*")) && Long.parseLong(txtSwimTime.getText()) > 0){
			return new Swimming(Long.parseLong(txtSwimTime.getText()));
		}else{
			//display red error text
			displayError(4);
			return new Swimming(-1);
		}
	}
	private Biking storeBikeTime(){
		lblErrBike.setVisible(false);
		if ((! txtBikeTime.getText().matches(".*[^0-9].*")) && Long.parseLong(txtBikeTime.getText()) > 0){
			return new Biking(Long.parseLong(txtBikeTime.getText()));
		}else{
			//display red error text
			displayError(5);
			return new Biking(-1);
		}
	}

	//store data in set
	private LinkedHashSet<Object> storeSet(Athlete ath, Running run, Swimming swim, Biking bike){
		LinkedHashSet<Object> currentAthlete = new LinkedHashSet<>();
		currentAthlete.add(ath);
		currentAthlete.add(run);
		currentAthlete.add(swim);
		currentAthlete.add(bike);
		return currentAthlete;
	}

	//method to display red error text
		//errCode = 1: athlete first name is invalid
		//errCode = 2: athlete last name is invalid
		//errCode = 3: running time is invalid
		//errCode = 4: swimming time is invalid
		//errCode = 5: biking time is invalid
		//errCode = 6: gender is not chosen
	private void displayError(int errCode){
		switch(errCode){
			case 1:
				lblErrFName.setVisible(true);
				break;
			case 2:
				lblErrLName.setVisible(true);
				break;
			case 3:
				lblErrRun.setVisible(true);
				break;
			case 4:
				lblErrSwim.setVisible(true);
				break;
			case 5:
				lblErrBike.setVisible(true);
				break;
			case 6:
				lblErrGender.setVisible(true);
				break;
		}
	}

	//method to send data to the mysql database
	/*private void sendData(LinkedHashSet currentAthlete){

	}*/
}