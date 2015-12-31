import java.util.ArrayList;

/**
 * Created by juarugui on 31/12/15.
 */
public class BoardParameters {

    public int robotLocation;
    public ArrayList<Box> boxes;
    public int numOffices;
    public ArrayList<Office> offices;


    BoardParameters(){
        this.boxes = new ArrayList<Box>();
        this.offices = new ArrayList<Office>();
    }

    public int getRobotLocation(){
        return this.robotLocation;
    }

    public void setRobotLocation(int robotLocation){
        this.robotLocation = robotLocation;
    }


    public ArrayList<Box> getBoxes() {
        return this.boxes;
    }

    public void addBox(Box box){
        this.boxes.add(box);
    }

    public void setNumOffices(int numOffices){
        this.numOffices = numOffices;
    }

    public ArrayList<Office> getOffices(){
        return this.offices;
    }

    public Office getOffice(int index){
        return this.offices.get(index);
    }

    public void addOffice(Office office){
        this.offices.add(office);
    }
}
