/**
 * Created by juarugui on 05/01/16.
 */
public class EmptyPredicate extends Predicate {

    private Office office;

    public EmptyPredicate(Office office) {
        this.office = office;
    }

    //@Override
    boolean checkPredicate() {
        return office.getEmpty();
    }

    @Override
    public String toString() {
        return "Empty{" +
                office.getOfficeNumber() +
                '}';
    }

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
}
