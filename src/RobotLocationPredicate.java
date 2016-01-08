/**
 * Created by juarugui on 05/01/16.
 */
public class RobotLocationPredicate extends Predicate {

    private Office office;
    private Robot robot;
    private String type;

    RobotLocationPredicate(Office office, Robot robot) {
        this.office = office;
        this.robot = robot;
        this.type = "ROBOT-LOCATION";

    }


    @Override
    boolean checkPredicate() {
        return (this.robot.getLocation()==this.office);
    }
//
//    @Override
//    String getType() {
//        return this.type;
//    }

    @Override
    public boolean checkElement() {
        return this.checkPredicate();
    }

    @Override
    void applyElement() {

    }

    public Office getOffice() {
        return office;
    }

    public Robot getRobot() {
        return robot;
    }

    @Override
    public String toString() {
        return "Robot-location{" +
                office.getOfficeNumber() +
                '}';
    }
}
