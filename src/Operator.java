import java.util.List;

/**
 * Created by juarugui on 04/01/16.
 */
public abstract class Operator extends StackElement {

    public abstract void add();
    public abstract void delete();
    public abstract List<Predicate> listPreconditions();
}
