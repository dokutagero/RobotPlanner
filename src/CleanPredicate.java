/**
 * Created by juarugui on 05/01/16.
 */
public class CleanPredicate extends Predicate {

    private Office office;

    public CleanPredicate(Office office) {
        this.office = office;
    }

    @Override
    boolean checkPredicate() {
        return office.getClean();
    }


    @Override
    public String toString() {
        return "Clean{" +
                office.getOfficeNumber() +
                '}';
    }

    @Override
    public boolean checkElement() {
        return this.checkPredicate();
    }

    // Look for operator with goal in its effects
    public void applyElement() {
        // check for operators that satisfy this predicate.

    }

    public Office getOffice() {
        return office;
    }
}
