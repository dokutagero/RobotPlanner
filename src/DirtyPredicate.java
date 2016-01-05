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
        return false;
    }

    @Override
    public String toString() {
        return "Dirty{" +
                office.getOfficeNumber() +
                '}';
    }
}
