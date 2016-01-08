import java.util.ArrayList;
import java.util.List;

/**
 * Created by juarugui on 05/01/16.
 */
public class Planner {

    BoardParameters boardParameters;
    GoalStack goalStack;
    List<StackElement> plan;
    // We will store in this list the subgoal stack as well as the global goal stack.
    List<List<StackElement>> listGoalStacks;



    public Planner(BoardParameters boardParameters, GoalStack goalStack) {
        this.boardParameters = boardParameters;
        this.goalStack = goalStack;
        this.plan = new ArrayList<StackElement>();
        this.listGoalStacks = new ArrayList<List<StackElement>>();
    }


    public void applyPlanner(){

        List<StackElement> subgoalStack = new ArrayList<StackElement>();
        for (StackElement stackElement : goalStack.getStack()){
            subgoalStack.add(stackElement);
        }


        // Add the final global stack to the list of goals that will contain each subgoal.
        this.listGoalStacks.add(subgoalStack);
        StackElement currentStackElement;
        // Apply
        List<StackElement> stack = goalStack.getStack();
        while(!stack.isEmpty()){

            // Pop each element from the main stack at each iteration.
            currentStackElement = goalStack.popFromStack();

            if (currentStackElement instanceof Predicate){

                // If predicate is already accomplished, remove it from the stack.
                if(((Predicate) currentStackElement).checkPredicate()){
                    // Get rid of the predicate.
                    currentStackElement = null;
                }
                else{
                    // If the predicate is unsatisfied, find operator that accomplishes it.
                    OperatorFinder operatorFinder = new OperatorFinder();
                    Operator operator = operatorFinder.findOperator(boardParameters,((Predicate)currentStackElement));
                    List<Predicate> preconditions = operator.listPreconditions();

                    // This list contains the preconditions for each subgoal given by the new operator.
                    subgoalStack = new ArrayList<StackElement>();
                    // We add the new operator to the main stack of goals
                    goalStack.addToStack(operator);
                    // Add the preconditions to the new subgoal
                    for (Predicate precondition : preconditions){
                        goalStack.addToStack(precondition);
                        subgoalStack.add(precondition);
                    }

                    // Add the new subgoal list to the list of goals.
                    listGoalStacks.add(subgoalStack);


                }
            }else if (currentStackElement instanceof Operator){

                // First check the subgoal stack
                List<StackElement> currentSubGoals = listGoalStacks.get(listGoalStacks.size()-1);
                List<StackElement> unaccomplishedSubGoals = new ArrayList<StackElement>();

                // Check if some preconditions have been modified by another subgoals.

                for(StackElement subgoal : currentSubGoals ){
                    if ( !((Predicate) subgoal).checkPredicate()){
                        unaccomplishedSubGoals.add(subgoal);
                    }
                }

                // If no unaccomplished preconditions, apply given operator.
                if (unaccomplishedSubGoals.isEmpty()){
                    ((Operator) currentStackElement).add();
                    // Add the operator to the plan.
                    plan.add(currentStackElement);
                    currentStackElement = null;
                    // Remove last set of preconditions from this already accomplished subgoal.
                    listGoalStacks.remove(listGoalStacks.size()-1);
                }
                else{
                    // Put back the operator and put back the preconditions from the subgoal list
                    goalStack.addToStack(currentStackElement);

                    for(StackElement subgoal : listGoalStacks.get(listGoalStacks.size()-1)){
                        goalStack.addToStack(subgoal);
                    }
                }

            }


            System.out.println("************");
            System.out.println("CURRENT STACK");
            System.out.println("************");

            System.out.println(goalStack);

            System.out.println("************");
            System.out.println("LIST OF SUBGOALS");
            System.out.println("************");
            System.out.println(listGoalStacks);

            System.out.println("************");
            System.out.println("PLAN");
            System.out.println("************");

            System.out.println(plan);

        }

    }


}
