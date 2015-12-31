/**
 * Created by juarugui on 31/12/15.
 */
public class RobotPlanner {

    public static void main(String[] args){
        String filename = new String("settings.txt");

        PARser parser = new PARser();
        BoardParameters boardParameters = parser.parse(filename);

        System.out.println("Office location: " + boardParameters.getRobotLocation());
    }
}
