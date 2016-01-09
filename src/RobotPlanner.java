/**
 * Created by juarugui on 31/12/15.
 */
public class RobotPlanner {

    public static void main(String[] args){
        String filename = new String("settings.txt");

        //Parse initial files and obtain the board configuration.
        PARser parser = new PARser();
        BoardParameters boardParameters = parser.parse(filename);


        GoalStack goalStack = new GoalStack();
        goalStack.populateGoalStack(boardParameters);
        System.out.println(goalStack.toString());

        // Apply planner
        Planner planner = new Planner(boardParameters, goalStack);
        planner.applyPlanner();
    }
}
