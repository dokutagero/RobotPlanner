import java.util.ArrayList;
import java.util.List;

/**
 * Created by juarugui on 04/01/16.
 */
public class MoveOperator extends Operator {

    private Office office1;
    private Office office2;
    private Robot robot;

    public MoveOperator(){

    }

    public MoveOperator(Robot robot, Office office1, Office office2) {
        this.office1 = office1;
        this.office2 = office2;
        this.robot = robot;
    }


    @Override
    public void add() {
        //Move(o1,o2)
        //Moves robot to office2
        robot.setLocation(office2);
    }

    @Override
    public void delete() {

    }

    public List<Predicate> listPreconditions() {
        ArrayList<Predicate> preconditions = new ArrayList<Predicate>();
        Predicate robot_location = new RobotLocationPredicate(this.office1, this.robot);
        //Predicate adjacent = new BoxLocationPredicate(box, office1);
        //adjacent missing
        preconditions.add(robot_location);
        return preconditions;
    }

    public List<Predicate> getAddEffects(Robot robot, Office office1, Office office2){

        // List so we could add new add efects if needed for having another behaviour.
        ArrayList<Predicate> addEfects = new ArrayList<Predicate>();
        RobotLocationPredicate robotLocationPredicate = new RobotLocationPredicate(office2, robot);
        addEfects.add(robotLocationPredicate);
        return addEfects;
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
