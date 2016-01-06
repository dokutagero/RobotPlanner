/**
 * Created by juarugui on 04/01/16.
 */
public class PushOperator extends Operator {

    private Box box;
    private Office office1;
    private Office office2;

    public PushOperator(Box box, Office office1, Office office2) {
        this.box = box;
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


    public void push(Office office){

        if (checkPreconditions()){
        }

    }


    @Override
    public String toString() {
        return "Push{" +
                box.getBoxName() +
                ",o" + office1.getOfficeNumber() +
                ",o" + office2.getOfficeNumber() +
                '}';
    }

    @Override
    public boolean checkElement() {
        return this.checkPreconditions();
    }

    @Override
    void applyElement() {

    }
}
