//Ryan Simpson
//edited by Carl Goshert on 30th Sept 2016

public class Swimming {
	
	private long endTime;
	
	private static final long MAX_TIME = 3600;
	
	public Swimming(long endTime){
		this.endTime = endTime;
	}

	public long getEndTime() {
		return this.endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	
	public boolean exceedsMaxTime(long endTime){
        if (endTime > MAX_TIME){
            return true;
        }else{
            return false;
        }
	}
}
