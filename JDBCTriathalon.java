import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import java.sql.*;

public class JDBCTriathalon {
	private Statement statement;
	
	public JDBCTriathalon() throws SQLException, ClassNotFoundException  {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/triathalon", "cscgroup", "csc3610");
		this.statement = connection.createStatement();
	}
	
	//to be used in a loop to pull all tuples
	public ResultSet pullTuple() throws SQLException, ClassNotFoundException {
		ResultSet resultSet = statement.executeQuery("select * from participants");
		//firstName, lastName, gender, id, runTime, swimTime, bikeTime
		return resultSet;
	}
	
	//overloaded method for use when finding key specific tuple/tuple set
	public ResultSet pullTuple(String key) throws SQLException, ClassNotFoundException {
		//check whether key is id or gender
		if (key.matches(".*[0-9].*")){
			ResultSet resultSet = statement.executeQuery("select * from participants where id = '" + key + "'");
			//firstName, lastName, gender, id, runTime, swimTime, bikeTime
			return resultSet;
		} else {
			ResultSet resultSet = statement.executeQuery("select * from participants where gender = '" + key + "'");
			//firstName, lastName, gender, id, runTime, swimTime, bikeTime
			return resultSet;
		}
	}
	
	//additional overloaded method for use when finding single tuple based on gender and last name
	public ResultSet pullTuple(String genderKey, String lastNameKey) throws SQLException, ClassNotFoundException {
		ResultSet resultSet = statement.executeQuery("select * from participants where lastName = '" + lastNameKey + "' and gender = '" + genderKey + "'");
		return resultSet;
	}

	//used to store a tuple in the db
	public void pushTuple(String fname, String lname, String gender, String id, Long run, Long swim, Long bike) throws SQLException, ClassNotFoundException, MySQLIntegrityConstraintViolationException {
		this.statement.executeUpdate("insert into `participants` (`firstName`, `lastName`, `gender`, `id`, `runTime`, `swimTime`, `bikeTime`) values ('" + fname + "', '" + lname + "', '" + gender + "', '" + id + "', " + run + ", " + swim + ", " + bike + ")");
	}
}