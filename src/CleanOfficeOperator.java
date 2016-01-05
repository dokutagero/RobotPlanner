/**
 * Created by juarugui on 04/01/16.
 */
public class CleanOfficeOperator extends Operator {

    private Office office;
    public CleanOfficeOperator(Office office) {
        this.office=office;

    }

    @Override
    boolean checkPreconditions() {
        return false;
    }

    @Override
    void addList() {

    }

    @Override
    void deleteList() {

    }

    @Override
    public String toString() {
        return "Clean-office{"+
                "o" + office.getOfficeNumber() +
                '}';
    }
}
