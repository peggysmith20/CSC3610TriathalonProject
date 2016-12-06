import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.Queue;

/* Created by carl on 12/2/16.
 */
public class SearchViewController {
	@FXML
	private RadioButton rbMale;
	@FXML
	private RadioButton rbFemale;
	@FXML
	private TextField txtNumber;
	@FXML
	private TextField txtLName;
	@FXML
	private Button btnSearch;
	@FXML
	private Button btnClear;
	@FXML
	private TableView<Athlete> tblvw;
	@FXML
	private TableColumn<Athlete, String> number;
	@FXML
	private TableColumn<Athlete, String> FirstName;
	@FXML
	private TableColumn<Athlete, String> LastName;
	@FXML
	private TableColumn<Athlete, String> gender;
	@FXML
	private TableColumn<Athlete, String> totalTime;
	@FXML
	private Label lblErrLName;
	@FXML
	private Label lblErrAthleteID;
	@FXML
	private Label lblErrGender;
	
	public void initialize(){
		//Set red error text to invisible
		lblErrLName.setVisible(false);
		lblErrGender.setVisible(false);
		lblErrAthleteID.setVisible(false);
		
		
		number.setCellValueFactory(new PropertyValueFactory<>("participantID"));
		FirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		LastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
		totalTime.setCellValueFactory(new PropertyValueFactory<>("totalTime"));
		
		btnClear.setOnAction(e -> {
			rbFemale.setSelected(false);
			rbMale.setSelected(false);
			txtNumber.clear();
			txtLName.clear();
			tblvw.setItems(null);
		});
		
		btnSearch.setOnAction(e -> {
			//check if male or female
			String genderSelected = checkGender();
			//check to see if they are searching by name or id, then pull resultset
			if(checkIDOrName()){
				Queue<Athlete> athlete = findAthlete(txtNumber.getText());
				//display queue in tableview
				displayAthlete(athlete);
			} else {
				Queue<Athlete> athlete = findAthlete(genderSelected, txtLName.getText());
				//display queue in tableview
				displayAthlete(athlete);
			}
			//handle errors
		});
	}
	
	private boolean checkIDOrName(){
		if (txtNumber.getText().matches("")) {
			return false;
		} else {
			return true;
		}
	}
	
	private String checkGender(){
		if (rbMale.isSelected()){
			return "male";
		} else if (rbFemale.isSelected()){
			return "female";
		} else {
			return null;
			//handle this
		}
	}
	
	private Queue<Athlete> findAthlete(String number){
		try {
			JDBCTriathalon jdbcObject = new JDBCTriathalon();
			Queue<Athlete> athlete = new LinkedList<>();
			ResultSet resultSet = jdbcObject.pullTuple(number);
			while (resultSet.next()){
				Athlete newAthlete = new Athlete(resultSet.getString("firstName"), resultSet.getString("lastName"),
						resultSet.getString("gender"), resultSet.getString("id"));
				Long runtime = resultSet.getLong("runTime");
				Long swimtime = resultSet.getLong("swimTime");
				Long biketime = resultSet.getLong("bikeTime");
				//if statement to change total time to null if athlete exceeds max time in any event
				if (new Running(runtime).exceedsMaxTime() || new Swimming(swimtime).exceedsMaxTime() || new Biking(biketime).exceedsMaxTime()){
					newAthlete.setTotalTime(null);
				} else {
					newAthlete.setTotalTime(runtime + swimtime + biketime);
				}
				athlete.add(newAthlete);
				System.out.println(newAthlete);
			}
			return athlete;
		}catch (Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	private Queue<Athlete> findAthlete(String gender, String lastName){
		try {
			JDBCTriathalon jdbcObject = new JDBCTriathalon();
			Queue<Athlete> athlete = new LinkedList<>();
			ResultSet resultSet = jdbcObject.pullTuple(gender, lastName);
			while (resultSet.next()){
				Athlete newAthlete = new Athlete(resultSet.getString("firstName"), resultSet.getString("lastName"),
						resultSet.getString("gender"), resultSet.getString("id"));
				Long runtime = resultSet.getLong("runTime");
				Long swimtime = resultSet.getLong("swimTime");
				Long biketime = resultSet.getLong("bikeTime");
				if (new Running(runtime).exceedsMaxTime() || new Swimming(swimtime).exceedsMaxTime() || new Biking(biketime).exceedsMaxTime()){
					newAthlete.setTotalTime(null);
				} else {
					newAthlete.setTotalTime(runtime + swimtime + biketime);
				}
				athlete.add(newAthlete);
				System.out.println(newAthlete);
			}
			return athlete;
		}catch (Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	private void displayAthlete(Queue<Athlete> athlete){
		ObservableList<Athlete> athleteList = FXCollections.observableArrayList(athlete);
		tblvw.setItems(athleteList);
	}
}
