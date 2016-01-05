import java.util.List;

/**
 * Created by juarugui on 05/01/16.
 */
public class Planner {

    List<StackElement> plan;
    public void applyPlanner(GoalStack goalStack){

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
                    
                }
            }

        }

    }
}
