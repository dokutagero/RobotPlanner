/**
 * Created by juarugui on 31/12/15.
 */
public class Office {

    public boolean clean;
    public boolean empty;

    Office(){
        this.clean = true;
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
