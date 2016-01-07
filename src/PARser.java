import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by juarugui on 31/12/15.
 */
public class PARser {

    public BoardParameters parse(String filename){

        File file = new File(filename);
        BoardParameters boardParameters = new BoardParameters();

        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scanner.hasNextLine()){
            Scanner lineScanner = new Scanner(scanner.nextLine()).useDelimiter("=");

            String element = lineScanner.next();

            switch (element) {
                case "Boxes" : {
                    Pattern p = Pattern.compile("([A-Z])");
                    Matcher m = p.matcher(lineScanner.next());
                    while (m.find()){
                        //boardParameters.setRobotLocation(Integer.parseInt(m.group(1)));
                        Box box = new Box(m.group(1));
                        boardParameters.addBox(box);
                    }
                    break;
                }

                case "Offices" : {
                    Pattern p = Pattern.compile("o([0-9])");
                    Matcher m = p.matcher(lineScanner.next());
                    //boardParameters.setNumOffices(m.groupCount());
                    while (m.find()){
                        System.out.println(m.group(1));
                        Office office = new Office(Integer.parseInt(m.group(1)));
                        boardParameters.addOffice(office);
                    }
                    break;
                }
                case "InitialState":{

                    // Parse Robot location
                    Pattern p = Pattern.compile("Robot-location[(]o([0-9]+)[)]");
                    String text = lineScanner.next();
                    Matcher m = p.matcher(text);
                    while (m.find()){
                        //Robot robot = new Robot(Integer.parseInt(m.group(1)));
                        Robot robot = new Robot(boardParameters.getOffice(Integer.parseInt(m.group(1))-1));
                        boardParameters.setRobot(robot);
                    }
                    // Parse dirty offices
                    p = Pattern.compile("Dirty[(]o([0-9]+)[)]");
                    m = p.matcher(text);
                    while (m.find()){
                        boardParameters.getOffice(Integer.parseInt(m.group(1))-1).setClean(false);
                    }

                    // Parse Box location
                    p = Pattern.compile("Box-location[(]([A-Z]),o([0-9]+)[)]");
                    m = p.matcher(text);
                    while (m.find()){
                        boardParameters.getBoxByName(m.group(1)).setLocation(
                                boardParameters.getOffice(Integer.parseInt(m.group(2))-1));
                        boardParameters.getOffice(Integer.parseInt(m.group(2))-1).setEmpty(false,
                                boardParameters.getBoxByName(m.group(1)));
                    }

                    // Parse empty office
                    p = Pattern.compile("Empty[(]o([0-9]+)[)]");
                    m = p.matcher(text);
                    while (m.find()){
                        boardParameters.getOffice(Integer.parseInt(m.group(1))-1).setEmpty(true, null);
                    }

                    break;
                }

                case "GoalState":{

                    // Parse goal Robot location
                    Pattern p = Pattern.compile("Robot-location[(]o([0-9]+)[)]");
                    String text = lineScanner.next();
                    Matcher m = p.matcher(text);
                    m.find();
                    boardParameters.setGoalRobotLocation(Integer.parseInt(m.group(1)));

                    // Parse empty goal empty offices
                    p = Pattern.compile("Empty[(]o([0-9]+)[)]");
                    m = p.matcher(text);
                    while (m.find()){
                        boardParameters.addGoalEmptyOffices(Integer.parseInt(m.group(1)));
                    }

                    // Parse goal Box-location
                    p = Pattern.compile("Box-location[(]([A-Z]),o([0-9])[)]");
                    m = p.matcher(text);
                    while(m.find()){
                        BoxLocationTuple boxLocationTuple = new BoxLocationTuple(m.group(1),Integer.parseInt(m.group(2)));
                        boardParameters.addGoalBoxLocation(boxLocationTuple);
                    }
                    break;

                }
            }
        }
        return boardParameters;
    }
}
