/**
 * Created by juarugui on 04/01/16.
 */
public abstract class Operator extends StackElement {

    public String operatorType;

    abstract boolean checkPreconditions();
    abstract void add();
    abstract void delete();

}
