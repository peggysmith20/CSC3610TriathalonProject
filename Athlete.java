/* Created by carl on 9/27/16.
 */
public class Athlete {
    //attributes
    private String firstName;
    private String lastName;
    private String gender;
    private String participantID;

    //operators
    public Athlete(String firstName, String lastName, String gender, String participantID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.participantID = participantID;
    }

    public void setName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setParticipantID(String participantID) {
        this.participantID = participantID;
    }

    public String getName() {
        return(this.firstName + this.lastName);
    }

    public String getGender() {
        return gender;
    }

    public String getParticipantID() {
        return participantID;
    }

    @Override
    public String toString() {
        return "Athlete{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", participantID='" + participantID + '\'' +
                '}';
    }
}
