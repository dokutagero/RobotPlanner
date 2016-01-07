/**
 * Created by juarugui on 31/12/15.
 */
public class Office {

    public boolean clean;
    public boolean empty;
    public int officeNumber;
    public Box box;

    Office(int officeNumber){
        this.clean = true;
        this.empty = false;
        this.officeNumber = officeNumber;
        this.box = null;
        //Probably we can access this by index though
    }

    public boolean getClean(){
        return this.clean;
    }

    public void setClean(boolean clean){
        this.clean = clean;
    }

    public boolean getEmpty(){
        return this.empty;
    }

    public void setEmpty(boolean empty, Box box){
        this.box = box;
        this.empty = empty;
    }

    public int getOfficeNumber() {
        return officeNumber;
    }

    public Box getBox() {
        return box;
    }
}
