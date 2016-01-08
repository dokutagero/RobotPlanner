/**
 * Created by juarugui on 05/01/16.
 */
public class DirtyPredicate extends Predicate {

    Office office;

    public DirtyPredicate(Office office) {
        this.office = office;
    }

    //@Override
    boolean checkPredicate() {
        return !office.getClean();
    }

    @Override
    public String toString() {
        return "Dirty{" +
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
}
