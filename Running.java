//created by PEggy Smith on 28th Sept 2016
//edited by Carl Goshert on 30th Sept 2016
public class Running
{
	

	private long endTime;

	
	
	private static final long MAX_TIME = 3600;

	
	
	Running(long endTime) {
		this.endTime = endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	
	public long getEndTime() {
		
		return this.endTime;
	}

	
	
	public boolean exceedsMaxTime(long endTime) {
        if(endTime > MAX_TIME){
            return true;
        }else{
            return false;
        }
	}

}
