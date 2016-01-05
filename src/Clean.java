/**
 * Created by juarugui on 05/01/16.
 */
public class Clean extends Predicate {

    public boolean clean;

    public Clean(Office office) {
        this.clean = office.getClean();
    }

    @Override
    boolean checkPredicate() {
        return false;
    }
}
