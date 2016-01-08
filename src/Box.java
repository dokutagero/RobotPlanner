/**
 * Created by juarugui on 31/12/15.
 */
public class Box {

    public Office office;
    public String boxName;

    Box(String boxName){
        this.boxName = boxName;
    }

    public String getBoxName(){
        return this.boxName;
    }

    public void setLocation(Office office){
        this.office = office;
    }

    public Office getOffice() {
        return office;
    }
}
