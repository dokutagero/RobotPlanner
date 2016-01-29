/**
 * Created by juarugui on 31/12/15.
 */
public class Box {

    public Office office;
    public String boxName;
    public int lastPosition;

    Box(String boxName){
        this.boxName = boxName;
    }

    public String getBoxName(){
        return this.boxName;
    }

    public void setLocation(Office office){
        this.office = office;
        this.lastPosition = office.getOfficeNumber();
    }

    public Office getOffice() {
        return office;
    }

    public int getLastPosition() {
        return lastPosition;
    }

    public void setLastPosition(int lastPosition) {
        this.lastPosition = lastPosition;
    }
}
