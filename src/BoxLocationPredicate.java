/**
 * Created by juarugui on 05/01/16.
 */
public class BoxLocationPredicate extends Predicate {

    private Box box;
    private Office office;

    public BoxLocationPredicate(Box box, Office office) {
        this.box = box;
        this.office = office;
    }

    // @Override
    boolean checkPredicate() {
        return (box.getOffice() == office);
    }

    @Override
    public String toString() {
        return "BoxLocation{" +
                box.getBoxName() + "," +
                office.getOfficeNumber() +
                '}';
    }

}
