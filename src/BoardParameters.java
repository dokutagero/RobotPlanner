import java.util.ArrayList;
import java.util.List;

/**
 * Created by juarugui on 31/12/15.
 */
public class BoardParameters {

    public List<Box> boxes;
    public List<Office> offices;
    public Robot robot;

    //Goal State attributes
    public int   goalRobotLocation;
    public List<Integer> goalEmptyOffices;
    public List<BoxLocationTuple> goalBoxLocation;




    BoardParameters(){
        this.boxes = new ArrayList<Box>();
        this.offices = new ArrayList<Office>();
        this.goalEmptyOffices = new ArrayList<Integer>();
        this.goalBoxLocation = new ArrayList<BoxLocationTuple>();
    }


    public List<Box> getBoxes() {

        return this.boxes;
    }

    public Box getBoxByName(String name){
        for (Box box : this.boxes){
            if (box.getBoxName().equals(name)){
                return box;
            }
        }
        return null;
    }
    public void addBox(Box box){
        this.boxes.add(box);
    }

    public List<Office> getOffices(){
        return this.offices;
    }

    public Office getOffice(int index){
        return this.offices.get(index);
    }

    public void addOffice(Office office){
        this.offices.add(office);
    }

    public int getGoalRobotLocation() {
        return goalRobotLocation;
    }

    public void setGoalRobotLocation(int goalRobotLocation) {
        this.goalRobotLocation = goalRobotLocation;
    }

    public List<Integer> getGoalEmptyOffices() {
        return goalEmptyOffices;
    }

    public void addGoalEmptyOffices(int goalEmptyOffice) {
        this.goalEmptyOffices.add(goalEmptyOffice);
    }

    public List<BoxLocationTuple> getGoalBoxLocation() {
        return goalBoxLocation;
    }

    public void addGoalBoxLocation(BoxLocationTuple goalBoxLocation) {
        this.goalBoxLocation.add(goalBoxLocation);
    }

    public void setRobot(Robot robot) {
        this.robot = robot;
    }
}
