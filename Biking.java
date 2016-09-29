

public class Biking {

	private double endTime;
	
	private double MAX_TIME;
	
	public Biking(double endTime){
		this.endTime = endTime;
	}

	public double getEndTime() {
		return endTime;
	}

	public void setEndTime(double endTime) {
		this.endTime = endTime;
	}
	
	public boolean exceedsMaxTime(double endTime){
		if (endTime > 60) return true;
		
		else return false;
	}
}
