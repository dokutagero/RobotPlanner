/**
 * Created by juarugui on 31/12/15.
 */
public class Robot {

    Office office;

    public Robot(Office office) {
        this.office = office;
    }

    public Office getLocation() {
        return office;
    }

    public void setLocation(Office office) {
        this.office = office;
    }

    public Office getOffice() {
        return office;
    }
}
