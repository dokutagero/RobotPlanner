/**
 * Created by juarugui on 05/01/16.
 */
public abstract class StackElement {

    abstract public String toString();

    //checkElement will check precondition for Operators
    //and will check predicates if element is a predicate.
    abstract boolean checkElement();
}
