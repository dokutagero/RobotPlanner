/**
 * Created by juarugui on 05/01/16.
 */
public class CleanPredicate extends Predicate {

    private Office office;

    public CleanPredicate(Office office) {
        this.office = office;
    }

    //@Override
    boolean checkPredicate() {
        return false;
    }


    @Override
    public String toString() {
        return "Clean{" +
                office.getOfficeNumber() +
                '}';
    }
}
