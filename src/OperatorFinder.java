import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by juarugui on 06/01/16.
 */
public class OperatorFinder {

    private Integer[][] adjacentOffices = new Integer[][]{{2,4},{1,3,5},{2,6},{1,5,7},
                                                {2,4,6,8},{3,5,9},{4,8},
                                                {5,7,9},{6,8}};

    Integer[] xcoordinates = new Integer[]{1,1,1,2,2,2,3,3,3};
    Integer[] ycoordinates = new Integer[]{1,2,3,1,2,3,1,2,3};


    public Operator findOperator(BoardParameters boardParameters, Predicate predicate) {


        // CHECK ROBOT-LOCATION PREDICATES
        if (predicate instanceof RobotLocationPredicate) {
            // If robot location predicate we create a move operator
            MoveOperator operator = new MoveOperator();
            //////
            Office nextOffice = ((RobotLocationPredicate) predicate).getOffice();

            // If offices are not adjacent, find adjacent.
            if(!checkAdjacents(((RobotLocationPredicate) predicate).getRobot().getOffice(),
                    ((RobotLocationPredicate) predicate).getOffice())) {

                Integer nextAdjacent = getNextAdjacentToMove(((RobotLocationPredicate) predicate).getRobot().getOffice(), nextOffice);
                nextOffice = boardParameters.getOffice(nextAdjacent-1);

            }

            MoveOperator moveOperatorAdjacent = new MoveOperator(boardParameters.getRobot(),
                    boardParameters.getRobot().getLocation(),
                    nextOffice);

            /////
            List<Predicate> predicates = operator.getAddEffects(((RobotLocationPredicate) predicate).getRobot(),
                    ((RobotLocationPredicate) predicate).getRobot().getLocation(),
                    //((RobotLocationPredicate) predicate).getOffice());
                    nextOffice);
            // Check if any of the add efects match our unsatisfied goal

            for (Predicate operatorAddEffect : predicates) {
                if (predicate.getClass().equals(operatorAddEffect.getClass())) {
                    if (nextOffice ==
                            ((RobotLocationPredicate) operatorAddEffect).getOffice()) {

                        MoveOperator moveOperator = new MoveOperator(((RobotLocationPredicate) predicate).getRobot(),
                                ((RobotLocationPredicate) predicate).getRobot().getLocation(),
                                nextOffice);

                        return moveOperator;
                    }
                }
            }

            // Check CLEAN PREDICATES
        } else if (predicate instanceof CleanPredicate) {

            // Check clean office operator
            CleanOfficeOperator cleanOfficeOperatorChecker = new CleanOfficeOperator();
            // Obtain list of add efects for CleanOfficeOperator with the office from unsatisfied goal
            List<Predicate> predicates = cleanOfficeOperatorChecker.getAddEffects(((CleanPredicate) predicate).getOffice());

            for (Predicate operatorAddEffect : predicates) {
                if (predicate.getClass().equals(operatorAddEffect.getClass())) {
                    if (((CleanPredicate) predicate).getOffice() ==
                            ((CleanPredicate) operatorAddEffect).getOffice()) {

                        // If the precondition is accomplished by the CleanOfficeOperator, create it and return it.
                        CleanOfficeOperator cleanOfficeOperator = new CleanOfficeOperator(((CleanPredicate) predicate).getOffice(),
                                                                                            boardParameters.getRobot());

                        return cleanOfficeOperator;
                    }
                }
            }

            // CHECK EMPTY PREDICATES
        } else if (predicate instanceof EmptyPredicate) {


            // Check Push operator
            PushOperator pushOperatorChecker = new PushOperator();
            Office nonEmptyOffice = ((EmptyPredicate) predicate).getOffice();
            // Box in office to be pushed
            Box boxInOffice = ((EmptyPredicate) predicate).getOffice().getBox();

            // ADD HEURISTICS TO SELECTING WHERE TO BE PUSHED

            // Get the best office to push the box
            Integer bestAdjacentToPushTo = getBestAdjacentToPushTo(((EmptyPredicate) predicate).getOffice(), boardParameters);
            //Office officeToBePushed = boardParameters.getOffice(adjacentOffices[(((EmptyPredicate) predicate).getOffice().getOfficeNumber() - 1)][bestAdjacentToPushTo]);
            Office officeToBePushed = boardParameters.getOffice(bestAdjacentToPushTo);


            // We check the effects of the Push operator.
            List<Predicate> predicates = pushOperatorChecker.getAddEffects(boardParameters.getRobot(), boxInOffice,
                    ((EmptyPredicate) predicate).getOffice(),
                    officeToBePushed);
            for (Predicate operatorPushEffect : predicates) {
                if (predicate.getClass().equals(operatorPushEffect.getClass())) {
                    if (((EmptyPredicate) predicate).getOffice() == ((EmptyPredicate) operatorPushEffect).getOffice()) {
                        PushOperator pushOperator = new PushOperator(boardParameters.getRobot(), boxInOffice,
                                                                    ((EmptyPredicate) predicate).getOffice(),
                                                                    officeToBePushed);

                        return pushOperator;
                    }

                }
            }
        }else if (predicate instanceof BoxLocationPredicate){
//            // CHECK BOX-LOCATION PREDICATE
            PushOperator pushOperatorChecker = new PushOperator();
            //Box boxInOffice = ((BoxLocationPredicate) predicate).getOffice().getBox();
            Box boxInOffice = ((BoxLocationPredicate) predicate).getBox();


            Integer bestAdjacentToPushTo = getBestAdjacentToPushTo(((BoxLocationPredicate) predicate).getBox().getOffice(), boardParameters);
            Office officeToBePushed = boardParameters.getOffice(bestAdjacentToPushTo);

            if(!officeToBePushed.getEmpty()){
                boxInOffice = officeToBePushed.getBox();
                bestAdjacentToPushTo = getBestAdjacentToPushTo(officeToBePushed, boardParameters);
                officeToBePushed = boardParameters.getOffice(bestAdjacentToPushTo);

            }

            List<Predicate> predicates = pushOperatorChecker.getAddEffects(boardParameters.getRobot(), boxInOffice,
                    ((BoxLocationPredicate) predicate).getOffice(),
                    officeToBePushed);
            for (Predicate operatorPushEffect : predicates) {
                if (predicate.getClass().equals(operatorPushEffect.getClass())) {
                    if (((BoxLocationPredicate) predicate).getOffice() == ((BoxLocationPredicate) operatorPushEffect).getOffice()) {


                        PushOperator pushOperator = new PushOperator(boardParameters.getRobot(), boxInOffice,
                                ((BoxLocationPredicate) predicate).getOffice(),
                                officeToBePushed);

                        return pushOperator;
                    }

                }
            }
        }
        else{

        }

        MoveOperator moveOperator = new MoveOperator();
        return moveOperator;
    }

    public boolean checkAdjacents(Office office1, Office office2){

        List<Integer> adjacents = new ArrayList<Integer>();
        //adjacents = Arrays.asList(adjacentOffices[(((AdjacentPredicate) predicate).getOffice1().getOfficeNumber()-1)]);
        adjacents = Arrays.asList(adjacentOffices[office1.getOfficeNumber()-1]);
        return adjacents.contains(Integer.valueOf(office2.getOfficeNumber()));

    }

    public Integer getBestAdjacentToPushTo(Office officeFrom, BoardParameters boardParameters){

        Integer officePosition = officeFrom.getOfficeNumber();
        Integer bestAdjacentToPush = 0;
        // Adjacent to box to be pushed in office
        List<Integer> adjacents = new ArrayList<Integer>();
        adjacents = Arrays.asList(adjacentOffices[officePosition-1]);

        Box boxInOffice = officeFrom.getBox();
        String boxName = boxInOffice.getBoxName();
        Integer boxFinalOffice = 0;

        List<BoxLocationTuple> boxFinalLocationTuples = boardParameters.getGoalBoxLocation();
        for(BoxLocationTuple boxLocationTuple : boxFinalLocationTuples){
            if (boxName.equals(boxLocationTuple.getBoxName())){
                boxFinalOffice = boxLocationTuple.getBoxLocation();
            }
        }

        List<Integer> officeIndices = new ArrayList<>();


        int rowOrigin, rowDestination;
        int columnOrigin, columnDestination;
        Integer distance;
        List<Integer> distances = new ArrayList<>();

        rowDestination = getXcoordinates()[boardParameters.getOffice(boxFinalOffice-1).getOfficeNumber()-1];
        columnDestination = getYcoordinates()[boardParameters.getOffice(boxFinalOffice-1).getOfficeNumber()-1];


        for (int i=0;i<adjacents.size();i++){



            rowOrigin = getXcoordinates()[adjacents.get(i)-1];
            columnOrigin = getYcoordinates()[adjacents.get(i)-1];


            distance = Math.abs(rowOrigin-rowDestination) + Math.abs(columnOrigin-columnDestination);
            // If adjacent is empty is candidate to be office to be pushed
            if(boardParameters.getOffice(adjacents.get(i)-1).getEmpty()){
                distances.add(distance);
                officeIndices.add(adjacents.get(i));
            }
        }

        bestAdjacentToPush =  officeIndices.get(distances.indexOf(Collections.min(distances)))-1;
        if(bestAdjacentToPush == (boxInOffice.getLastPosition()-1)){
            officeIndices.remove(distances.indexOf(Collections.min(distances)));
            distances.remove(distances.indexOf(Collections.min(distances)));

            bestAdjacentToPush = officeIndices.get(distances.indexOf(Collections.min(distances)))-1;
        }

        if(checkAdjacents(boxInOffice.getOffice(), boardParameters.getOffice(boxFinalOffice-1))){
            bestAdjacentToPush = boxFinalOffice-1;
        }



        return bestAdjacentToPush;

    }

    public Integer getNextAdjacentToMove(Office office1, Office office2){


        List<Integer> adjacents = new ArrayList<>();
        adjacents = Arrays.asList(adjacentOffices[office1.getOfficeNumber()-1]);

        //int distanceLinear;
        //int distanceMap;
        int rowOrigin, rowDestination;
        int columnOrigin, columnDestination;
        Integer distance;
        List<Integer> distances = new ArrayList<>();

        rowDestination = getXcoordinates()[office2.getOfficeNumber()-1];
        columnDestination = getYcoordinates()[office2.getOfficeNumber()-1];


        for (int i=0;i<adjacents.size();i++){



            rowOrigin = getXcoordinates()[adjacents.get(i)-1];
            columnOrigin = getYcoordinates()[adjacents.get(i)-1];


            distance = Math.abs(rowOrigin-rowDestination) + Math.abs(columnOrigin-columnDestination);
            distances.add(distance);
        }

        //return (distances.indexOf(Collections.min(distances))+1);
        return (adjacents.get(distances.indexOf(Collections.min(distances))));

    }

    public Integer[] getXcoordinates() {
        return xcoordinates;
    }

    public Integer[] getYcoordinates() {
        return ycoordinates;
    }
}

