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
    void add() {
        // Push(box,o1,o2)
        // Set location of box to office2
        // Set office1 empty
        // Robot location office2
        box.setLocation(office2);
        office1.setEmpty(true);
        robot.setLocation(office2);
    }

    @Override
    void delete() {

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


    @Override
    public String toString() {
        return "Push{" +
                box.getBoxName() +
                ",o" + office1.getOfficeNumber() +
                ",o" + office2.getOfficeNumber() +
                '}';
    }

    @Override
    public boolean checkElement() {
        return false;//this.checkPreconditions();
    }

    @Override
    void applyElement() {

    }
}
