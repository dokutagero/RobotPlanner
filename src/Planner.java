import java.util.ArrayList;
import java.util.List;

/**
 * Created by juarugui on 05/01/16.
 */
public class Planner {

    BoardParameters boardParameters;
    GoalStack goalStack;
    List<StackElement> plan;



    public Planner(BoardParameters boardParameters, GoalStack goalStack) {
        this.boardParameters = boardParameters;
        this.goalStack = goalStack;
        this.plan = new ArrayList<StackElement>();
    }


    public void applyPlanner(){

        StackElement currentStackElement;
        // Apply
        List<StackElement> stack = goalStack.getStack();
        while(!stack.isEmpty()){

            currentStackElement = stack.get(0);

            if (currentStackElement instanceof Predicate){
            //if (Predicate.class.isAssignableFrom(currentStackElement.getClass())){

                // If predicate is already accomplished, pop it from the stack.
                if(((Predicate) currentStackElement).checkPredicate()){
                    goalStack.popFromStack();
                }
                else{
                    // Find operator
                    OperatorFinder operatorFinder = new OperatorFinder();
                    Operator operator = operatorFinder.findOperator(boardParameters,((Predicate)currentStackElement));
                    List<Predicate> preconditions = operator.listPreconditions();
                    goalStack.addToStack(operator);
                    for (Predicate precondition : preconditions){
                        goalStack.addToStack(precondition);
                    }

                }
            }else if (currentStackElement instanceof Operator){
                // If it is an operator, add to the plan and add the efects of the operator to the current state.
                ((Operator) currentStackElement).add();
                plan.add(currentStackElement);

                // Remove applied operator from stack
                goalStack.popFromStack();
            }


            // Check will call preconditions on operators and check predicate
            // on predicate.
//            if (currentStackElement.checkElement()){
//                // If predicate is true, pop from the stack.
//                if (currentStackElement instanceof Predicate){
//                    goalStack.popFromStack();
//                }else{
//                // If preconditions of operator are true, add to plan
//                    plan.add(currentStackElement);
//                    // apply add and delete
//                    currentStackElement.applyElement();
//
//                }
//            }else{
//                    // If goal predicate not satisfied: find operator and
//                    // add it to the stack as well as operator preconditions
//                    if (currentStackElement instanceof Predicate){
//                        currentStackElement.applyElement();
//                    }else{
//                    // If operator does not
//                    }
//
//            }

            System.out.println(goalStack);
            System.out.println(plan);

        }

    }

    public void checkPreconditions(){


    }
}
