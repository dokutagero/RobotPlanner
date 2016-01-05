/**
 * Created by juarugui on 05/01/16.
 */
public class EmptyPredicate extends Predicate {

    boolean empty;

    public EmptyPredicate(Office office) {
        this.empty = office.getEmpty();
    }


    @Override
    boolean checkPredicate() {
        return false;
    }
}
