/**
 * Created by juarugui on 05/01/16.
 */
public class AdjacentPredicate extends Predicate {

    private Office office1;
    private Office office2;

    public AdjacentPredicate(Office office1, Office office2) {
        this.office1 = office1;
        this.office2 = office2;
    }

    //@Override
    boolean checkPredicate() {
        return false;
    }

    @Override
    public String toString() {
        return "Adjacent{" +
                office1.getOfficeNumber() + "," +
                office2.getOfficeNumber() +
                '}';
    }

    @Override
    public boolean checkElement() {
        return this.checkPredicate();
    }

    @Override
    void applyElement() {

    }
}
