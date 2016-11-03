//created by PEggy Smith on 28th Sept 2016
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
	
	public boolean exceedsMaxTime() {
        if(this.endTime > MAX_TIME){
            return true;
        }else{
            return false;
        }
	}
}