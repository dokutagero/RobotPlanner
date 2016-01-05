/**
 * Created by juarugui on 05/01/16.
 */
public class DirtyPredicate extends Predicate {

    public boolean dirty;

    public DirtyPredicate(Office office) {
        this.dirty = office.getClean();
    }

    @Override
    boolean checkPredicate() {
        return false;
    }
}
