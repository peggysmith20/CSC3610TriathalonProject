/* Created by carl on 9/27/16.
 */
public class Athlete {
    //attributes
    private String firstName;
    private String lastName;
    private String gender;
    private String participantID;
    private Long totalTime;

    //operators
    public Athlete(String firstName, String lastName, String gender, String participantID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.participantID = participantID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setParticipantID(String participantID) {
        this.participantID = participantID;
    }

    public String getGender() {
        return gender;
    }

    public String getParticipantID() {
        return participantID;
    }
    
    public Long getTotalTime() {
        return totalTime;
    }
    
    public void setTotalTime(Long totalTime) {
        this.totalTime = totalTime;
    }
    
    @Override
    public String toString() {
        return "Athlete{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", participantID='" + participantID + '\'' +
                ", totalTime=" + totalTime +
                '}';
    }
}
