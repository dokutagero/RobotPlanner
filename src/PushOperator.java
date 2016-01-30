import java.util.ArrayList;
import java.util.List;

/**
 * Created by juarugui on 04/01/16.
 */
public class PushOperator extends Operator {

    private Box box;
    private Office office1;
    private Office office2;
    private Robot robot;

    public PushOperator(){

    }


    public PushOperator(Robot robot,Box box, Office office1, Office office2) {
        this.box = box;
        this.office1 = office1;
        this.office2 = office2;
        this.robot = robot;
    }


    /*@Override
    boolean checkPreconditions() {
        return false;
    }*/

    @Override
    public void add() {
        // Push(box,o1,o2)
        // Set location of box to office2
        // Set office1 empty
        // Robot location office2
        int lastPos = box.getLastPosition();
        box.setLocation(office2);
        box.setLastPosition(lastPos);
        office1.setEmpty(true, null);
        robot.setLocation(office2);
        // Could add this statement to delete function
        office2.setEmpty(false, box);
    }

    @Override
    public void delete() {

    }

    @Override
    public List<Predicate> listPreconditions() {
        ArrayList<Predicate> preconditions = new ArrayList<Predicate>();
        Predicate robot_location = new RobotLocationPredicate(office1, robot);
        Predicate box_location = new BoxLocationPredicate(box, office1);
        Predicate empty = new EmptyPredicate(office2);
        //adjacent missing
        preconditions.add(robot_location);
        preconditions.add(box_location);
        preconditions.add(empty);
        return preconditions;
    }

    public List<Predicate> getAddEffects(Robot robot, Box box, Office office1, Office office2){
        // List so we could add new add efects if needed for having another behaviour.
        ArrayList<Predicate> addEfects = new ArrayList<Predicate>();
        // Add efect of moving the robot to office2
        RobotLocationPredicate robotLocationPredicate = new RobotLocationPredicate(office2, this.robot);
        addEfects.add(robotLocationPredicate);
        // Add efect of changing location of box to office2
        BoxLocationPredicate boxLocationPredicate = new BoxLocationPredicate(box,office2);
        addEfects.add(boxLocationPredicate);
        // Add efect of empty office 1
        EmptyPredicate emptyPredicate = new EmptyPredicate(office1);
        addEfects.add(emptyPredicate);

        return addEfects;
    }

    @Override
    public String toString() {
        return "PUSH{" +
                box.getBoxName() +
                ",o" + office1.getOfficeNumber() +
                ",o" + office2.getOfficeNumber() +
                '}';
    }


}
