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
    }


    public void applyPlanner(){

        StackElement currentStackElement;
        // Apply
        List<StackElement> stack = goalStack.getStack();
        while(!stack.isEmpty()){

            currentStackElement = stack.get(0);

            // Check will call preconditions on operators and check predicate
            // on predicate.
            if (currentStackElement.checkElement()){
                // If predicate is true, pop from the stack.
                if (currentStackElement instanceof Predicate){
                    goalStack.popFromStack();
                }else{
                // If preconditions of operator are true, add to plan
                    plan.add(currentStackElement);
                    // apply add and delete
                    currentStackElement.applyElement();

                }
            }else{
                    // If goal predicate not satisfied: find operator and
                    // add it to the stack as well as operator preconditions
                    if (currentStackElement instanceof Predicate){
                        currentStackElement.applyElement();
                    }else{
                    // If operator does not
                    }

            }

        }

        goalStack.toString();
    }

    public void checkPreconditions(){


    }
}
