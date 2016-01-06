/**
 * Created by juarugui on 05/01/16.
 */
public class RobotLocationPredicate extends Predicate {

    private Office office;
    private Robot robot;

    RobotLocationPredicate(Office office, Robot robot) {
        this.office = office;
        this.robot = robot;

    }


    @Override
    boolean checkPredicate() {
        return (robot.getLocation()==office.getOfficeNumber());
    }

    @Override
    public boolean checkElement() {
        return this.checkPredicate();
    }

    @Override
    void applyElement() {

    }


    @Override
    public String toString() {
        return "Robot-location{" +
                office.getOfficeNumber() +
                '}';
    }
}
