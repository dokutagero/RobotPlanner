import java.util.ArrayList;
import java.util.List;

/**
 * Created by juarugui on 06/01/16.
 */
public class OperatorFinder {


    public Operator findOperator(BoardParameters boardParameters, Predicate predicate) {


        // CHECK ROBOT-LOCATION PREDICATES
        if (predicate instanceof RobotLocationPredicate) {
            // If robot location predicate we create a move operator
            MoveOperator operator = new MoveOperator();
            List<Predicate> predicates = operator.getAddEffects(((RobotLocationPredicate) predicate).getRobot(),
                    ((RobotLocationPredicate) predicate).getRobot().getLocation(),
                    ((RobotLocationPredicate) predicate).getOffice());
            // Check if any of the add efects match our unsatisfied goal

            for (Predicate operatorAddEffect : predicates) {
                if (predicate.getClass().equals(operatorAddEffect.getClass())) {
                    if (((RobotLocationPredicate) predicate).getOffice() ==
                            ((RobotLocationPredicate) operatorAddEffect).getOffice()) {

                        MoveOperator moveOperator = new MoveOperator(((RobotLocationPredicate) predicate).getRobot(),
                                ((RobotLocationPredicate) predicate).getRobot().getLocation(),
                                ((RobotLocationPredicate) predicate).getOffice());

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
            List<Box> boxes = boardParameters.getBoxes();
            Box boxInOffice;
            for (Box box : boxes){
                if (box.getOffice() == nonEmptyOffice){
                    boxInOffice = box;
                }
            }

            // HEURISTICS FOR PUSHING BOX

            // Obtain list of add effects for PushOperator from unsatisfied goal
           // List<Predicate> predicates = pushOperatorChecker.getAddEffects(boardParameters.getRobot(),boxInOffice, )
//            // Check clean office operator
//            CleanOfficeOperator cleanOfficeOperatorChecker = new CleanOfficeOperator();
//            // Obtain list of add efects for CleanOfficeOperator with the office from unsatisfied goal
//            List<Predicate> predicates = cleanOfficeOperatorChecker.getAddEffects(((CleanPredicate) predicate).getOffice());
//
//            for (Predicate operatorAddEffect : predicates) {
//                if (predicate.getClass().equals(operatorAddEffect.getClass())) {
//                    if (((CleanPredicate) predicate).getOffice() ==
//                            ((CleanPredicate) operatorAddEffect).getOffice()) {
//
//                        CleanOfficeOperator cleanOfficeOperator = new CleanOfficeOperator(((EmptyPredicate) predicate).getOffice(),
//                                                                                            boardParameters.getRobot());
//
//                        return cleanOfficeOperator;
//                    }
//                }
//            }
//
//
//        }

        }
        MoveOperator moveOperator = new MoveOperator();
        return moveOperator;
    }
}
