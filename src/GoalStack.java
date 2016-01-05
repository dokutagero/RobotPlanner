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
}
