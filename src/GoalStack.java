
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by juarugui on 05/01/16.
 */
public class GoalStack {

    private Integer[][] adjacentOffices = new Integer[][]{{2,4},{1,3,5},{2,6},{1,5,7},
            {2,4,6,8},{3,5,9},{4,8},
            {5,7,9},{6,8}};

    Integer[] xcoordinates = new Integer[]{1,1,1,2,2,2,3,3,3};
    Integer[] ycoordinates = new Integer[]{1,2,3,1,2,3,1,2,3};

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
        ArrayList<CleanPredicate> unorderedCleanPredicates = new ArrayList<CleanPredicate>();
        ArrayList<CleanPredicate> orderedCleanPredicates = new ArrayList<CleanPredicate>();


        //Populate with clean Predicates
        for (Office office : offices) {
            cleanPredicate = new CleanPredicate(office);
            //this.pushToStack(cleanPredicate);
            unorderedCleanPredicates.add(cleanPredicate);
        }

        // Compute distances and sort
        orderedCleanPredicates = sortCleanPredicates(unorderedCleanPredicates, boardParameters);

        for (CleanPredicate orderedCleanPredicate : orderedCleanPredicates) {
            this.pushToStack(orderedCleanPredicate);
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

    public ArrayList sortCleanPredicates(ArrayList<CleanPredicate> unorderedCleanPredicates, BoardParameters boardParameters){

        int rowOrigin, rowDestination;
        int columnOrigin, columnDestination;
        Integer distance;
        List<Integer> distances = new ArrayList<>();
        ArrayList<CleanPredicate> orderedCleanPredicates = new ArrayList<>();
        Integer mindistance;

        //rowDestination = getXcoordinates()[boardParameters.getOffice(boxFinalOffice-1).getOfficeNumber()-1];
        //columnDestination = getYcoordinates()[boardParameters.getOffice(boxFinalOffice-1).getOfficeNumber()-1];
        rowDestination = getXcoordinates()[boardParameters.getRobot().getOffice().getOfficeNumber()-1];
        columnDestination = getYcoordinates()[boardParameters.getRobot().getOffice().getOfficeNumber()-1];


        for (CleanPredicate cleanPredicate : unorderedCleanPredicates){



            rowOrigin = getXcoordinates()[cleanPredicate.getOffice().getOfficeNumber()-1];
            columnOrigin = getYcoordinates()[cleanPredicate.getOffice().getOfficeNumber()-1];


            distance = Math.abs(rowOrigin-rowDestination) + Math.abs(columnOrigin-columnDestination);
            // If adjacent is empty is candidate to be office to be pushed
            //if(boardParameters.getOffice(adjacents.get(i)-1).getEmpty()){
            distances.add(distance);
            //}
        }

        for (int i=0; i<distances.size();i++) {
            mindistance = Collections.min(distances);
            orderedCleanPredicates.add(unorderedCleanPredicates.get(distances.indexOf(Collections.min(distances))));
            distances.set(distances.indexOf(Collections.min(distances)),10);
        }

        return orderedCleanPredicates;

    }

    public Integer[] getXcoordinates() {
        return xcoordinates;
    }

    public Integer[] getYcoordinates() {
        return ycoordinates;
    }
}



