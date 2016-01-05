/**
 * Created by juarugui on 05/01/16.
 */
public class RobotLocationPredicate extends Predicate {

    private Office office;

    RobotLocationPredicate(Office office) {
        this.office = office;
    }

    //@Override
    boolean checkPredicate(Office office) {
        //return (office.getOfficeNumber()==this.robotLocation);
        return false;
    }

    @Override
    public String toString() {
        return "Robot-location{" +
                office.getOfficeNumber() +
                '}';
    }
}
