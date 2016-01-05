/**
 * Created by juarugui on 05/01/16.
 */
public class RobotLocationPredicate extends Predicate {

    public int robotLocation;

    public RobotLocationPredicate(Robot robot) {
        this.robotLocation = robot.getLocation();
    }

    @Override
    boolean checkPredicate() {
        return false;
    }
}
