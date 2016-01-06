import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * Created by juarugui on 05/01/16.
 */
public class AdjacentPredicate extends Predicate {

    private Office office1;
    private Office office2;
    private int[][] adjacentOffices;

    public AdjacentPredicate(Office office1, Office office2) {
        this.office1 = office1;
        this.office2 = office2;
        // Adjacent offices
        this.adjacentOffices = new int[][]{{2,4},{1,3,5},{2,6},{1,5,7},
                                            {2,4,6,8},{3,5,9},{4,8},
                                            {5,7,9},{6,8}};

    }

    //@Override
    boolean checkPredicate() {
        int [] offices = this.adjacentOffices[this.office2.getOfficeNumber()];
        return (Arrays.asList(offices).contains(this.office1.getOfficeNumber()));
    }

    @Override
    public String toString() {
        return "Adjacent{" +
                office1.getOfficeNumber() + "," +
                office2.getOfficeNumber() +
                '}';
    }

    @Override
    public boolean checkElement() {
        return this.checkPredicate();
    }

    @Override
    void applyElement() {

    }
}
