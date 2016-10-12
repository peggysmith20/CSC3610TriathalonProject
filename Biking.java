//created by Ryan Simpson on 28th Sept 2016
//edited by Carl Goshert on 30th Sept 2016
public class Biking {

	private long endTime;
	
	private static final long MAX_TIME = 3600;
	
	public Biking(long endTime){
		this.endTime = endTime;
	}

	public long getEndTime() {
		return this.endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	
	public boolean exceedsMaxTime(long endTime){
		if (endTime > 60){
            return true;
        }else{
            return false;
        }
	}
}
