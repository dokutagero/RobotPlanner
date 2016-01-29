import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * Created by juarugui on 31/12/15.
 */
public class RobotPlanner {

    public static void main(String[] args){

        String filename = new String("settings6");

        //Parse initial files and obtain the board configuration.
        PARser parser = new PARser();
        BoardParameters boardParameters = parser.parse(filename+".txt");


        GoalStack goalStack = new GoalStack();
        goalStack.populateGoalStack(boardParameters);
        System.out.println(goalStack.toString());

        // Log the output
        String outputFileName = String.format("%s"+"_out.txt", filename);
        File outputFile = new File(outputFileName);
        try {
            PrintStream printStream = new PrintStream(outputFile);
            //System.setOut(printStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }




        // Apply planner
        Planner planner = new Planner(boardParameters, goalStack);
        planner.applyPlanner();
    }
}
