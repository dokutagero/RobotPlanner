import java.util.ArrayList;
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

    public void popFromStack(){

        if (!stack.isEmpty()){
            stack.remove(0);
        }
    }

    public List<StackElement> getStack() {
        return stack;
    }

    public void populateGoalStack(BoardParameters boardParameters){

        CleanPredicate cleanPredicate;
        //Populate with clean Predicates
        for(Office office : boardParameters.getOffices()){
            cleanPredicate = new CleanPredicate(office);
            this.pushToStack(cleanPredicate);
        }

        //Populate with BoxLocations

    }
}
