/**
 * Created by juarugui on 31/12/15.
 */
public class Office {

    public boolean clean;
    public boolean empty;
    public int officeNumber;

    Office(int officeNumber){
        this.clean = true;
        this.empty = false;
        this.officeNumber = officeNumber; //Probably we can access this by index though
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

    public void setEmpty(boolean empty){
        this.empty = empty;
    }
}
