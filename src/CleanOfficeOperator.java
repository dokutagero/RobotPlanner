import java.util.ArrayList;
import java.util.List;

/**
 * Created by juarugui on 04/01/16.
 */
public class CleanOfficeOperator extends Operator {

    private Office office;
    private Robot robot;

    public CleanOfficeOperator(){

    }

    public CleanOfficeOperator(Office office, Robot robot) {
        this.office = office;
        this.robot = robot;

    }


    @Override
    public void add() {
        // Clean office
        office.setClean(true);
    }

    @Override
    public void delete() {

    }

    public List<Predicate> listPreconditions() {
        ArrayList<Predicate> preconditions = new ArrayList<Predicate>();
        Predicate robot_location = new RobotLocationPredicate(office, robot);
        Predicate dirty = new DirtyPredicate(office);
        Predicate empty = new EmptyPredicate(office);
        //adjacent missing
        preconditions.add(robot_location);
        preconditions.add(empty);
        preconditions.add(dirty);
        return preconditions;
    }



    public List<Predicate> getAddEffects(Office office){

        // List so we could add new add efects if needed for having another behaviour.
        ArrayList<Predicate> addEfects = new ArrayList<Predicate>();
        //RobotLocationPredicate robotLocationPredicate = new RobotLocationPredicate(office2, this.robot);
        CleanPredicate cleanPredicate = new CleanPredicate(office);
        addEfects.add(cleanPredicate);
        return addEfects;
    }

    @Override
    public boolean checkElement() {

        return false;//this.checkPreconditions();
    }

    @Override
    void applyElement() {
        this.add();
        //this.delete()
    }


    @Override
    public String toString() {
        return "Clean-office{"+
                "o" + office.getOfficeNumber() +
                '}';
    }
}
