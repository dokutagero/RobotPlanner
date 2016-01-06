/**
 * Created by juarugui on 04/01/16.
 */
public class MoveOperator extends Operator {

    private Office office1;
    private Office office2;

    public MoveOperator(Office office1, Office office2) {
        this.office1 = office1;
        this.office2 = office2;
    }

    @Override
    boolean checkPreconditions() {
        return false;
    }

    @Override
    void add() {

    }

    @Override
    void delete() {

    }


    @Override
    public boolean checkElement() {
        return this.checkPreconditions();
    }

    @Override
    void applyElement() {
        this.add();
    }

    @Override
    public String toString() {
        return "Move{" +
                "o" + office1.getOfficeNumber() + ',' +
                "o" + office2.getOfficeNumber() +
                '}';
    }
}
