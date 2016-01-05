/**
 * Created by juarugui on 04/01/16.
 */
public class CleanOfficeOperator extends Operator {

    private Office office;
    private Robot robot;

    public CleanOfficeOperator(Office office, Robot robot) {
        this.office = office;
        this.robot = robot;

    }

    @Override
    boolean checkPreconditions() {
        return (office.getEmpty() && office.getClean() &&
                (robot.getLocation()==office.getOfficeNumber()));
    }

    @Override
    void addList() {

    }

    @Override
    void deleteList() {

    }


    @Override
    public boolean checkElement() {
        return this.checkPreconditions();
    }


    @Override
    public String toString() {
        return "Clean-office{"+
                "o" + office.getOfficeNumber() +
                '}';
    }
}
