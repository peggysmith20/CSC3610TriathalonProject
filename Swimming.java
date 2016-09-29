//Ryan Simpson

public class Swimming {
	
	private double endTime;
	
	private double MAX_TIME;
	
	public Swimming(double endTime){
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
