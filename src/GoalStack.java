import com.sun.tools.javac.util.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by juarugui on 05/01/16.
 */
public class GoalStack {

    public List<StackElement> stack;

    public GoalStack() {
        stack = new ArrayList<StackElement>();
    }

    public void pushToStack(StackElement element){
        stack.add(element);
    }

    public void addToStack(StackElement element){
        stack.add(0,element);
    }

    public StackElement popFromStack(){

        StackElement lastStackElement;
        lastStackElement = stack.get(0);
        stack.remove(0);
        return lastStackElement;
    }

    public List<StackElement> getStack() {
        return stack;
    }

    public void populateGoalStack(BoardParameters boardParameters) {

        CleanPredicate cleanPredicate;
        EmptyPredicate emptyPredicate;
        BoxLocationPredicate boxLocationPredicate;
        List<Office> offices = boardParameters.getOffices();
        RobotLocationPredicate robotLocationPredicate;


        //Populate with clean Predicates
        for (Office office : offices) {
            cleanPredicate = new CleanPredicate(office);
            this.pushToStack(cleanPredicate);
        }


        //Populate with Box location predicates
        List <BoxLocationTuple> goalBoxLocation = boardParameters.getGoalBoxLocation();

        for (BoxLocationTuple boxLocationTuple : goalBoxLocation){
            Box box = boardParameters.getBoxByName(boxLocationTuple.getBoxName());
            Office office = boardParameters.getOffice(boxLocationTuple.getBoxLocation()-1);
            boxLocationPredicate = new BoxLocationPredicate(box,office);
            this.pushToStack(boxLocationPredicate);
        }

        //Populate with empty office Predicates
        for (Office office : offices) {
            if (boardParameters.getGoalEmptyOffices().contains(office.getOfficeNumber())) {
                emptyPredicate = new EmptyPredicate(office);
                this.pushToStack(emptyPredicate);
            }
        }


        //Populate with goal robot location. (change this man...)
        int robotLocation = boardParameters.getGoalRobotLocation();
        for (Office office : offices) {
            if (office.getOfficeNumber()==robotLocation){
                robotLocationPredicate = new RobotLocationPredicate(office, boardParameters.getRobot());
                this.pushToStack(robotLocationPredicate);
            }
        }


    }



    public String toString(){
        String toStringValue = new String();
        //String leftAlignFormat = "| %-15s | %-4d |%n";
        for (StackElement element : this.stack){
            toStringValue = toStringValue + element.toString() + "\n";
        }
        return toStringValue;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
