import java.util.ArrayList;
import java.util.List;

/**
 * Created by juarugui on 04/01/16.
 */
public class MoveOperator extends Operator {

    private Office office1;
    private Office office2;
    private Robot robot;

    public MoveOperator(Robot robot, Office office1, Office office2) {
        this.office1 = office1;
        this.office2 = office2;
        this.robot = robot;
    }


    @Override
    void add() {
        //Move(o1,o2)
        //Moves robot to office2
        robot.setLocation(office2);
    }

    @Override
    void delete() {

    }

    public List<Predicate> listPreconditions() {
        ArrayList<Predicate> preconditions = new ArrayList<Predicate>();
        Predicate robot_location = new RobotLocationPredicate(office1, robot);
        //Predicate adjacent = new BoxLocationPredicate(box, office1);
        //adjacent missing
        preconditions.add(robot_location);
        return preconditions;
    }


    @Override
    public boolean checkElement() {
        return false;// this.checkPreconditions();
    }

    @Override
    void applyElement() {
        this.add();
    }

    @Override
    public String toString() {
        return "Move{" +
                "o" + office1.getOfficeNumber() + ',' +
                "o" + office2.getOfficeNumber() +
                '}';
    }
}
