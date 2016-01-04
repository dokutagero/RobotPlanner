/**
 * Created by juarugui on 04/01/16.
 */
public abstract class Operator {

    public String operatorType;

    abstract boolean checkPreconditions();
    abstract void addList();
    abstract void deleteList();

}
