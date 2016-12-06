import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.Queue;

public class ResultsViewController {
	@FXML
	private TableView<Athlete> tblvwMale;
	@FXML
	private TableView<Athlete> tblvwFemale;
	@FXML
	private TableColumn<Athlete, String> numberMale;
	@FXML
	private TableColumn<Athlete, String> FirstNameMale;
	@FXML
	private TableColumn<Athlete, String> LastNameMale;
	@FXML
	private TableColumn<Athlete, String> genderMale;
	@FXML
	private TableColumn<Athlete, String> totalTimeMale;

    @FXML
	private TableColumn<Athlete, String> numberFemale;
	@FXML
	private TableColumn<Athlete, String> FirstNameFemale;
	@FXML
	private TableColumn<Athlete, String> LastNameFemale;
	@FXML
	private TableColumn<Athlete, String> genderFemale;
	@FXML
	private TableColumn<Athlete, String> totalTimeFemale;
	
	
	public void initialize(){
			try {
				//set cell value / property stuff to post data to the tableView
				numberMale.setCellValueFactory(new PropertyValueFactory<>("participantID"));
				FirstNameMale.setCellValueFactory(new PropertyValueFactory<>("firstName"));
				LastNameMale.setCellValueFactory(new PropertyValueFactory<>("lastName"));
				genderMale.setCellValueFactory(new PropertyValueFactory<>("gender"));
				totalTimeMale.setCellValueFactory(new PropertyValueFactory<>("totalTime"));
				
				numberFemale.setCellValueFactory(new PropertyValueFactory<>("participantID"));
				FirstNameFemale.setCellValueFactory(new PropertyValueFactory<>("firstName"));
				LastNameFemale.setCellValueFactory(new PropertyValueFactory<>("lastName"));
				genderFemale.setCellValueFactory(new PropertyValueFactory<>("gender"));
				totalTimeFemale.setCellValueFactory(new PropertyValueFactory<>("totalTime"));
				
				//create male priority queue
				Queue<Athlete> maleQueue = fillQueue("male");
				//create female priority queue
				Queue<Athlete> femaleQueue = fillQueue("female");
				//display male queue in table view
				displayQueue(maleQueue, "male");
				//display female queue in table view
				displayQueue(femaleQueue, "female");
			}
			catch (Exception ex){
				ex.printStackTrace();
			}
	}
	
	private Queue<Athlete> fillQueue(String gender){
		try{
			System.out.println("Now printing " + gender + " athletes.");
			Queue<Athlete> athleteQueue = new LinkedList<>();
			//create loop to turn tuples to athletes, one at a time while adding them to priority queue
			JDBCTriathalon jdbcObject = new JDBCTriathalon();
			ResultSet resultSet = jdbcObject.pullTuple(gender);
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
				athleteQueue.add(newAthlete);
				System.out.println(newAthlete);
			}
			return athleteQueue;
		}
		catch (Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	private void displayQueue(Queue<Athlete> athleteQueue, String gender) throws IOException {
		//sends all the athletes to the GUI table
		ObservableList<Athlete> athletes = FXCollections.observableArrayList(athleteQueue);
		if (gender.matches("male")) {
			tblvwMale.setItems(athletes);
		}
		if (gender.matches("female")) {
			tblvwFemale.setItems(athletes);
		}
	}
}