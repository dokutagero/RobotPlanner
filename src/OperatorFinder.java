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
                                                {5,7,9},{6,8}};;


    public Operator findOperator(BoardParameters boardParameters, Predicate predicate) {


        // CHECK ROBOT-LOCATION PREDICATES
        if (predicate instanceof RobotLocationPredicate) {
            // If robot location predicate we create a move operator
            MoveOperator operator = new MoveOperator();
            //////
            //int[] adjacents = new int[];
            Office nextOffice = ((RobotLocationPredicate) predicate).getOffice();

            // If offices are not adjacent, find adjacent.
            if(!checkAdjacents(((RobotLocationPredicate) predicate).getRobot().getOffice(),
                    ((RobotLocationPredicate) predicate).getOffice())) {


                List<Integer> adjacents = new ArrayList<Integer>();
                adjacents = Arrays.asList(adjacentOffices[(((RobotLocationPredicate) predicate).getRobot().getOffice().getOfficeNumber() - 1)]);
                int nextMove;

                if (((RobotLocationPredicate) predicate).getRobot().getOffice().getOfficeNumber() <
                        ((RobotLocationPredicate) predicate).getOffice().getOfficeNumber()) {

                    int indexOfMax = 0;

                    for (int i = 1; i < adjacents.size(); i++) {
                        if (adjacents.get(i) > adjacents.get(indexOfMax)) {
                            indexOfMax = i;
                        }
                    }
                    nextMove = adjacents.get(indexOfMax);
                    nextOffice = boardParameters.getOffice(nextMove-1);
                } else {
                    int indexOfMin = 0;
                    for (int i = 1; i < adjacents.size(); i++) {
                        if (adjacents.get(i) < adjacents.get(indexOfMin)) {
                            indexOfMin = i;
                        }
                    }
                    nextMove = adjacents.get(indexOfMin);
                    nextOffice = boardParameters.getOffice(nextMove-1);
                }
            }

//            MoveOperator moveOperatorAdjacent = new MoveOperator(boardParameters.getRobot(),
//                    boardParameters.getRobot().getLocation(),
//                    nextOffice);

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
            // Find office to be pushed (initially to the first adjacent office)

            // Get the best office to push the box
            Integer bestAdjacentToPushTo = getBestAdjacentToPushTo(((EmptyPredicate) predicate).getOffice(), boardParameters);
            //Office officeToBePushed = boardParameters.getOffice(adjacentOffices[(((EmptyPredicate) predicate).getOffice().getOfficeNumber() - 1)][bestAdjacentToPushTo]);
            Office officeToBePushed = boardParameters.getOffice(bestAdjacentToPushTo);
            //System.out.println(((EmptyPredicate) predicate).getOffice().getOfficeNumber()-1);
            //System.out.println(adjacentOffices[4][0]);
            //System.out.println(adjacentOffices[((EmptyPredicate) predicate).getOffice().getOfficeNumber() - 1][0]);

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
        }else{
//            // CHECK ADJACENT PREDICATE
//            //int[] adjacents = new int[];
//            List<Integer> adjacents = new ArrayList<Integer>();
//            adjacents = Arrays.asList(adjacentOffices[(((AdjacentPredicate) predicate).getOffice1().getOfficeNumber()-1)]);
//            int nextMove;
//
//            if (((AdjacentPredicate) predicate).getOffice1().getOfficeNumber() <
//                    ((AdjacentPredicate) predicate).getOffice2().getOfficeNumber()){
//
//                int indexOfMax=0;
//
//                for(int i=1; i<adjacents.size(); i++){
//                    if (adjacents.get(i) > adjacents.get(indexOfMax)){
//                        indexOfMax = i;
//                    }
//                }
//                nextMove = adjacents.get(indexOfMax);
//            }else{
//                int indexOfMin=0;
//                for(int i=1; i<adjacents.size(); i++){
//                    if(adjacents.get(i) < adjacents.get(indexOfMin) ){
//                        indexOfMin = i;
//                    }
//                }
//                nextMove = adjacents.get(indexOfMin);
//            }
//
//            MoveOperator moveOperatorAdjacent = new MoveOperator(boardParameters.getRobot(),
//                    boardParameters.getRobot().getLocation(),
//                    (boardParameters.getOffice(nextMove-1)));
//
//            return moveOperatorAdjacent;
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

        // Now we get the closest office to the final position that is empty & clean
        int distanceLinear;
        int distanceMap;
        List<Integer> distances = new ArrayList<>();
        List<Integer> officeIndices = new ArrayList<>();
        // Search through all the adjacent offices.
        for (int i=0;i<adjacents.size();i++){
            // Compute the distances between adjacents and final box location
            distanceLinear = Math.abs(boardParameters.getOffice(adjacents.get(i)-1).getOfficeNumber()-boxFinalOffice);
            //
            if (distanceLinear>2) {
                distanceMap = (int) Math.ceil((double) distanceLinear / 3);
            }
            else{
                distanceMap = distanceLinear;
            }
            // Only add those adjacents that are empty
            if(boardParameters.getOffice(adjacents.get(i)-1).getEmpty()) {
                distances.add(distanceMap);
                officeIndices.add(adjacents.get(i)-1);
            }
        }

        bestAdjacentToPush = officeIndices.get(distances.indexOf(Collections.min(distances)));

        return bestAdjacentToPush;

    }
}

