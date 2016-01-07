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
            Office officeToBePushed = boardParameters.getOffice(adjacentOffices[(((EmptyPredicate) predicate).getOffice().getOfficeNumber() - 1)][0]-1);
            System.out.println(((EmptyPredicate) predicate).getOffice().getOfficeNumber()-1);
            System.out.println(adjacentOffices[4][0]);
            System.out.println(adjacentOffices[((EmptyPredicate) predicate).getOffice().getOfficeNumber() - 1][0]);
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
//        int [] offices = this.adjacentOffices[this.office2.getOfficeNumber()-1];
//        for(int i=0;i<offices.length;i++){
//            System.out.println((offices[i]));
//            System.out.println((this.office1.getOfficeNumber()));
//            if(offices[i] == this.office1.getOfficeNumber()){
//                return true;
    }

    public Integer getBestAdjacentToPushTo(Office officeFrom){

    }
}

