package running;

/*
* Created By Peggy Smith
* Each class should hold the time (use a double so you can hold fractions of a second). 
* These classes should have static data members of 60 minutes.  This is the maximum time of any one event.
*/

public class Running {

	private double endTime;

	private double MAX_TIME;

	public Running() {

	}

	public Running(double endTime) {
		this.endTime = endTime;

	}

	public void setEndTime(double endTime) {
		this.endTime = endTime;

	}

	public double getEndTime() {

		return endTime;
	}

	public boolean exceedsMaxTime(double endTime) {
		if (endTime > 60)
			
			return true;

		else

			return false;
	}

}